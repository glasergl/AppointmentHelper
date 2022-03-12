package ui.appointmentField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Scrollable;
import appointment.Appointment;
import appointment.InvalidAppointmentException;
import fileInteraction.AppointmentAlreadyAddedException;
import fileInteraction.AppointmentFileInteracter;
import standardSwing.general.SwingFunctions;
import standardSwing.settings.Colors;

/**
 * Depiction of all AppointmentInputFields.
 * 
 * @author Gabriel Glaser
 * @version 10.2.2022
 */
public class AllAppointmentFields extends JPanel implements Scrollable {

    private static final int MAX_NUMBER_OF_SHOWN_APPOINTMENTS = 8;
    private static final Color BACKGROUND1 = Colors.getGray(1);
    private static final Color BACKGROUND2 = Colors.getGray(0);

    private final List<AppointmentFieldController> appointmentFields = new ArrayList<>();
    private final Stack<AppointmentFieldController> allLastDeleted = new Stack<>();

    public AllAppointmentFields(final List<Appointment> initialAppointments) {
	super();
	setup(initialAppointments);
    }

    public AllAppointmentFields() {
	this(new ArrayList<>());
    }

    public void addEmptyAppointmentField() {
	final AppointmentFieldController empty = new AppointmentFieldController(this);
	addAppointmentField(empty);
    }

    /**
     * Restores the last deleted AppointmentField.
     * 
     * Can be called till every AppointmentField is restored.
     */
    public void restoreLastDeletedAppointmentField() {
	if (allLastDeleted.size() > 0) {
	    final AppointmentFieldController lastDeleted = allLastDeleted.pop();
	    addAppointmentField(lastDeleted);
	    if (lastDeleted.isSaved()) {
		AppointmentFileInteracter.add(lastDeleted.getStoredAppointment());
	    }
	}
    }

    /**
     * Adds the given AppointmentField to this.
     * 
     * @param toAdd
     */
    private void addAppointmentField(final AppointmentFieldController toAdd) {
	appointmentFields.add(toAdd);
	add(toAdd);
	setSwitchingBackgroundsForAll();
	SwingFunctions.updateJComponent(this);
    }

    /**
     * Removes the given AppointmentField and adds it to allLastDeleted.
     * 
     * It can be restored by calling restoreLastDeleted().
     * 
     * @param toRemove
     */
    public void removeAppointmentField(final AppointmentFieldController toRemove) {
	appointmentFields.remove(toRemove);
	remove(toRemove);
	setSwitchingBackgroundsForAll();
	SwingFunctions.updateJComponent(this);
	allLastDeleted.push(toRemove);
    }

    /**
     * Tests if the given Appointment is equal to an input of an
     * AppointmentFieldController of this.
     * 
     * @param appointmentToTest
     * @return True, if the given Appointment is the input of an
     *         AppointmentFieldController, else false.
     */
    public boolean containsAppointmentAsInput(final Appointment appointmentToTest) {
	try {
	    for (final AppointmentFieldController appointmentFieldController : appointmentFields) {
		if (appointmentFieldController.representsValidAppointment() && appointmentToTest.equals(appointmentFieldController.getInputAppointment())) {
		    return true;
		}
	    }
	    return false;
	} catch (final InvalidAppointmentException e) {
	    e.printStackTrace();
	    throw new RuntimeException();
	}
    }

    /**
     * Saves all depicted Appointments. If there are one or more invalid
     * Appointments, a JOptionPane depicts the error to the user and nothing
     * happens.
     */
    public void saveAll() {
	try {
	    if (allRepresentCorrectAppointment()) {
		for (final AppointmentFieldController appointmentInputField : appointmentFields) {
		    appointmentInputField.save();
		}
	    } else {
		final String errorTitle = "Ungültiger Termin";
		final String errorMessage = "Mindestens ein Termin ist ungültig.\nDer Name jedes Termins muss mindestens ein Zeichen enthalten.";
		JOptionPane.showMessageDialog(null, errorMessage, errorTitle, JOptionPane.ERROR_MESSAGE);
	    }
	} catch (final AppointmentAlreadyAddedException e) {
	    final String errorTitle = "Doppelter Termin";
	    final String errorMessage = "Mindestens zwei Termine enthalten den gleichen Inhalt. Bitte änderen Sie einen und speichern erneut.";
	    JOptionPane.showMessageDialog(null, errorMessage, errorTitle, JOptionPane.ERROR_MESSAGE);
	} catch (final InvalidAppointmentException e) {
	    e.printStackTrace();
	    throw new RuntimeException("Atleast one Appointment isn't valid, but should've been.");
	}
    }

    public boolean allRepresentCorrectAppointment() {
	for (final AppointmentFieldController appointmentInputField : appointmentFields) {
	    if (!appointmentInputField.representsValidAppointment()) {
		return false;
	    }
	}
	return true;
    }

    public boolean isSaved() {
	for (final AppointmentFieldController appointmentField : appointmentFields) {
	    if (!appointmentField.isSaved()) {
		return false;
	    }
	}
	return true;
    }

    public void cancelAll() {
	for (final AppointmentFieldController appointmentField : appointmentFields) {
	    appointmentField.cancel();
	}
    }

    private void setup(final List<Appointment> initialAppointments) {
	setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
	setupAllAppointments(initialAppointments);
	setSwitchingBackgroundsForAll();
    }

    private void setupAllAppointments(final List<Appointment> initialAppointments) {
	for (final Appointment toAdd : initialAppointments) {
	    final AppointmentFieldController appointment = new AppointmentFieldController(this, toAdd);
	    appointmentFields.add(appointment);
	    add(appointment);
	}
    }

    private void setSwitchingBackgroundsForAll() {
	for (int i = 0; i < appointmentFields.size(); i++) {
	    final AppointmentFieldController current = appointmentFields.get(i);
	    current.setDefaultBackground(i % 2 == 0 ? BACKGROUND1 : BACKGROUND2);
	}
    }

    @Override
    public Dimension getPreferredSize() {
	final AppointmentFieldController toCalculatePreferredSize = new AppointmentFieldController(this);
	final Dimension preferredSize = toCalculatePreferredSize.getPreferredSize();
	final int preferredWidth = preferredSize.width;
	final int preferredHeight = getComponents().length * preferredSize.height;
	return new Dimension(preferredWidth, preferredHeight);
    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
	final AppointmentFieldController toCalculatePreferredSize = new AppointmentFieldController(this);
	final Dimension preferredSize = toCalculatePreferredSize.getPreferredSize();
	return new Dimension(preferredSize.width, preferredSize.height * MAX_NUMBER_OF_SHOWN_APPOINTMENTS);
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
	return 10;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
	return 10;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
	return false;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
	return false;
    }

    public List<AppointmentFieldController> getAppointmentFields() {
	return appointmentFields;
    }

}
