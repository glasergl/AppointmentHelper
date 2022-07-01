package de.glasergl.ui.appointmentField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import javax.swing.JPanel;

import de.glasergl.appointment.Appointment;
import de.glasergl.appointment.InvalidAppointmentException;
import de.glasergl.configuration.AppointmentsConfigurationHandler;
import de.glasergl.configuration.ConfigurationHandler;
import de.glasergl.standard.swing.general.SwingFunctions;
import de.glasergl.standard.swing.settings.Colors;

/**
 * Class which controls a list of appointment field controllers.
 * 
 * Their input is retrievable by getInput() and they can be stored to the
 * configuration with storeAll().
 * 
 * @author Gabriel Glaser
 * @version 01.07.2022
 */
public final class AppointmentFieldControllerList extends JPanel {

    private static final int MAX_NUMBER_OF_SHOWN_APPOINTMENTS = 10;
    private static final Color DEFAULT_BACKGROUND = Color.WHITE;
    private static final Color BACKGROUND_OF_APPOINTMENT_FIELD_0 = Colors.getBlue(1);
    private static final Color BACKGROUND_OF_APPOINTMENT_FIELD_1 = Colors.getBlue(0);
    private static final int SPACE_BETWEEN_APPOINTMENTS = 1;

    private final ConfigurationHandler configurationHandler;
    private final AppointmentsConfigurationHandler appointmentConfigurationHandler;
    private final List<AppointmentFieldController> appointmentFields;
    private final Stack<AppointmentFieldController> deletedAppointmentFields;

    private boolean matchesConfiguration = true;

    public AppointmentFieldControllerList(final ConfigurationHandler configurationHandler) {
	super();
	this.configurationHandler = configurationHandler;
	this.appointmentConfigurationHandler = configurationHandler.getAppointmentsHandler();
	this.appointmentFields = new LinkedList<>();
	this.deletedAppointmentFields = new Stack<>();
	final AppointmentsConfigurationHandler appointmentsConfigurationHandler = configurationHandler.getAppointmentsHandler();
	setup(appointmentsConfigurationHandler.getAppointments());
	setAlternateBackgroundsForAll();
    }

    /**
     * Adds an empty appointment field to the list for another input.
     */
    public void addEmptyAppointmentField() {
	final AppointmentFieldController appointmentFieldController = new AppointmentFieldController(this, appointmentFields.size());
	appointmentFields.add(appointmentFieldController);
	add(appointmentFieldController);
	setAlternateBackgroundsForAll();
	changeHappened();
	SwingFunctions.updateJComponent(this);
    }

    /**
     * Updates the configuration and stores the current state of the configuration
     * to its respective file. Does nothing if called repeatedly without changes in
     * between. (Need to call changeHappened() before)
     * 
     * @throws InvalidAppointmentException If at least one appointment field doesn't
     *                                     contain a valid appointment as input.
     */
    public void storeAll() throws InvalidAppointmentException {
	if (!matchesConfiguration) {
	    appointmentConfigurationHandler.updateAppointments(getInput());
	    configurationHandler.storeConfiguration();
	    for (final AppointmentFieldController appointmentField : appointmentFields) {
		appointmentField.setDefaultBackground();
	    }
	    matchesConfiguration = true;
	}
    }

    /**
     * @return All appointments which are currently put in.
     * @throws InvalidAppointmentException If at least one appointment field doesn't
     *                                     contain a valid appointment as input.
     */
    public List<Appointment> getInput() throws InvalidAppointmentException {
	final List<Appointment> appointments = new ArrayList<>(appointmentFields.size());
	for (final AppointmentFieldController appointmentField : appointmentFields) {
	    appointments.add(appointmentField.getAppointment());
	}
	return appointments;
    }

    /**
     * Calculates whether every appointment field represents a valid appointment.
     * Therefore, the getInput() can be called and the current state of input can be
     * stored to the configuration.
     * 
     * @return true, if every appointment field represents a valid appointment.
     */
    public boolean allRepresentValidAppointments() {
	for (final AppointmentFieldController appointmentField : appointmentFields) {
	    if (!appointmentField.representsValidAppointment()) {
		return false;
	    }
	}
	return true;
    }

    /**
     * Deletes the given appointmentField. Will be called by a child
     * AppointmentFieldController if its delete-button is clicked.
     * 
     * @param appointmentField
     */
    public void delete(final int indexToDelete) {
	final AppointmentFieldController appointmentField = appointmentFields.get(indexToDelete);
	if (indexToDelete < appointmentConfigurationHandler.getSize()) {
	    appointmentConfigurationHandler.delete(indexToDelete);
	}
	for (int i = indexToDelete + 1; i < appointmentFields.size(); i++) {
	    appointmentFields.get(i).setIndex(i - 1);
	}
	appointmentFields.remove(indexToDelete);
	appointmentField.setIndex(-1);
	remove(appointmentField);
	deletedAppointmentFields.push(appointmentField);
	setAlternateBackgroundsForAll();
	changeHappened();
	SwingFunctions.updateJComponent(this);
    }

    /**
     * Restores the last deleted appointmentField. Does nothing if there is no
     * deleted appointment.
     */
    public void restoreLastDeleted() {
	if (!deletedAppointmentFields.isEmpty()) {
	    final AppointmentFieldController restoredAppointmentField = deletedAppointmentFields.pop();
	    appointmentFields.add(restoredAppointmentField);
	    add(restoredAppointmentField);
	    restoredAppointmentField.setIndex(appointmentFields.size() - 1);
	    setAlternateBackgroundsForAll();
	    changeHappened();
	    SwingFunctions.updateJComponent(this);
	}
    }

    /**
     * @return Whether the current state of this matches the state in the
     *         configuration.
     */
    public boolean matchesConfiguration() {
	return matchesConfiguration;
    }

    /**
     * Should be called, if anything changed to the input in the appointment fields.
     * If this isn't called once after a change, the storeAll() method won't do
     * anything.
     * 
     * @param matchesConfiguration
     */
    public void changeHappened() {
	matchesConfiguration = false;
    }

    /**
     * Method for testing the inner state of this instance.
     * 
     * @return The number of appointments of the configuration.
     */
    public int getNumberOfAppointmentsInConfiguration() {
	final AppointmentsConfigurationHandler appointmentsConfigurationHandler = configurationHandler.getAppointmentsHandler();
	return appointmentsConfigurationHandler.getSize();
    }

    /**
     * Method for testing the inner state of this instance.
     * 
     * @return The number of currently shown appointments of this List.
     */
    public int getNumberOfShownAppointments() {
	return appointmentFields.size();
    }

    /**
     * Method for testing the inner state of this instance.
     * 
     * @return The number of deleted appointments which could be restored by
     *         restoreLastDeleted().
     */
    public int getNumberOfDeletedAppointments() {
	return deletedAppointmentFields.size();
    }

    /**
     * Returns the appointment field at the given index to be able to manually
     * change the input.
     * 
     * @param i
     * @return The appointment field at the given index.
     * @throws IllegalArgumentException If the index doesn't map to an appointment
     *                                  field in the list.
     */
    public AppointmentFieldController getAppointmentField(final int i) throws IllegalArgumentException {
	if (i < 0 || i >= appointmentFields.size()) {
	    throw new IllegalArgumentException(i + " out of bound for size " + appointmentFields.size());
	}
	return appointmentFields.get(i);
    }

    /**
     * Calculates the preferred view size which should be seen in the GUI.
     * 
     * Will take a dummy AppointmentFieldController and looking at its preferred
     * size.
     * 
     * @return The preferred size of this depending on the preferred width, height
     *         of an AppointmentFieldController.
     */
    public Dimension getPreferredViewSize() {
	final AppointmentFieldController dummy = new AppointmentFieldController(this, 0);
	final Dimension preferredSizeOfDummy = dummy.getPreferredSize();
	return new Dimension(preferredSizeOfDummy.width, (int) (MAX_NUMBER_OF_SHOWN_APPOINTMENTS - 0.5) * preferredSizeOfDummy.height);
    }

    /**
     * Calculates the preferred size of the whole panel by taking a dummy and
     * looking at its preferred size. The resulting size should fit all and an extra
     * appointment field.
     * 
     * @return The preferred size of this depending on the preferred width, height
     *         of an AppointmentFieldController.
     */
    @Override
    public Dimension getPreferredSize() {
	final AppointmentFieldController dummy = new AppointmentFieldController(this, 0);
	final Dimension preferredSizeOfDummy = dummy.getPreferredSize();
	return new Dimension(preferredSizeOfDummy.width, (appointmentFields.size() + 1) * preferredSizeOfDummy.height);
    }

    private void setup(final List<Appointment> initialAppointments) {
	setLayout(new FlowLayout(FlowLayout.CENTER, 0, SPACE_BETWEEN_APPOINTMENTS));
	setBackground(DEFAULT_BACKGROUND);
	for (int i = 0; i < initialAppointments.size(); i++) {
	    final AppointmentFieldController appointmentFieldController = new AppointmentFieldController(this, initialAppointments.get(i), i);
	    appointmentFields.add(appointmentFieldController);
	    add(appointmentFieldController);
	}
    }

    /**
     * Changes the background of each appointment field according to its position in
     * the list. Results in alternate backgrounds.
     */
    private void setAlternateBackgroundsForAll() {
	for (int i = 0; i < appointmentFields.size(); i++) {
	    final AppointmentFieldController appointmentField = appointmentFields.get(i);
	    appointmentField.setDefaultBackground(i % 2 == 0 ? BACKGROUND_OF_APPOINTMENT_FIELD_0 : BACKGROUND_OF_APPOINTMENT_FIELD_1);
	}
    }
}
