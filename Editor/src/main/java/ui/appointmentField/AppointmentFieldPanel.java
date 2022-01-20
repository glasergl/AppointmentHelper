package ui.appointmentField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Scrollable;
import appointment.Appointment;
import fileInteraction.AppointmentFileInteracter;
import general.SwingFunctions;
import settings.Colors;

/**
 * Depiction of all AppointmentInputFields.
 * 
 * @author Gabriel Glaser
 * @version 2.1.2022
 */
public class AppointmentFieldPanel extends JPanel implements Scrollable {

    private static final int MAX_NUMBER_OF_SHOWN_APPOINTMENTS = 6;
    private static final Color BACKGROUND1 = Colors.getGray(1);
    private static final Color BACKGROUND2 = Colors.getGray(0);

    private final List<AppointmentFieldController> appointmentFields = new ArrayList<>();

    public AppointmentFieldPanel(final List<Appointment> initialAppointments) {
	super();
	setup(initialAppointments);
    }

    public AppointmentFieldPanel() {
	this(new ArrayList<>());
    }

    private void setup(final List<Appointment> initialAppointments) {
	setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
	setupAllAppointments(initialAppointments);
	setSwitchingBackgroundsForAll();
    }

    private void setupAllAppointments(final List<Appointment> toDepict) {
	for (final Appointment toAdd : toDepict) {
	    final AppointmentFieldController appointment = new AppointmentFieldController(this, toAdd);
	    appointmentFields.add(appointment);
	    add(appointment);
	}
    }

    public void addEmptyAppointmentField() {
	final AppointmentFieldController empty = new AppointmentFieldController(this);
	appointmentFields.add(empty);
	add(empty);
	setSwitchingBackgroundsForAll();
    }

    public void addAppointmentField(final Appointment appointmentToDisplay) {
	AppointmentFileInteracter.add(appointmentToDisplay);
	final AppointmentFieldController controller = new AppointmentFieldController(this, appointmentToDisplay);
	appointmentFields.add(controller);
	add(controller);
	setSwitchingBackgroundsForAll();
	revalidate();
	repaint();
    }

    public void removeAppointmentField(final AppointmentFieldController toRemove) {
	appointmentFields.remove(toRemove);
	remove(toRemove);
	setSwitchingBackgroundsForAll();
	SwingFunctions.updateJComponent(this);
    }

    public void saveAll() {
	for (final AppointmentFieldController appointmentInputField : appointmentFields) {
	    appointmentInputField.save();
	}
    }

    private void setSwitchingBackgroundsForAll() {
	for (int i = 0; i < appointmentFields.size(); i++) {
	    final AppointmentFieldController current = appointmentFields.get(i);
	    current.setBackground(i % 2 == 0 ? BACKGROUND1 : BACKGROUND2);
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

}
