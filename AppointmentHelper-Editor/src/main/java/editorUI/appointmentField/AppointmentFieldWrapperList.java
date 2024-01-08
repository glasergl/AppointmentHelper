package editorUI.appointmentField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import appointment.Appointment;
import appointment.InvalidAppointmentException;
import standardGlaserGl.swing.general.ScrollJScrollPaneToBottom;
import fileConfiguration.AppointmentsConfigurationHandler;

/**
 * Class which controls a list of appointment field controllers.
 * 
 * Their input is retrievable by getInput() and they can be stored to the
 * configuration with storeAll().
 * 
 * @author Gabriel Glaser
 */
public final class AppointmentFieldWrapperList extends JPanel {
	private static final int MAX_NUMBER_OF_SHOWN_APPOINTMENTS = 9;
	private static final Color DEFAULT_BACKGROUND = Color.WHITE;
	private static final Color BACKGROUND_OF_APPOINTMENT_FIELD_0 = new Color(235, 235, 235);
	private static final Color BACKGROUND_OF_APPOINTMENT_FIELD_1 = new Color(220, 220, 220);
	private static final int VERTICAL_SPACE_BETWEEN_APPOINTMENTS = 4;
	private static final int HORIZONTAL_MARGIN = 35;
	private static final int MARGIN_AT_BOTTOM = 35;

	private final AppointmentsConfigurationHandler handler;
	private final List<AppointmentFieldWrapper> appointmentFields;

	private boolean matchesConfiguration = true;
	private Optional<JScrollPane> scrollWrapper = Optional.empty();

	public AppointmentFieldWrapperList(final AppointmentsConfigurationHandler handler) {
		super();
		this.appointmentFields = new LinkedList<>();
		this.handler = handler;
		setup(handler.getAppointments());
		setAlternateBackgroundsForAll();
	}

	/**
	 * Adds an empty appointment field to the list for another input.
	 */
	public void addEmptyAppointmentField() {
		final AppointmentFieldWrapper appointmentFieldController = new AppointmentFieldWrapper(this,
				appointmentFields.size());
		appointmentFields.add(appointmentFieldController);
		add(appointmentFieldController);
		setAlternateBackgroundsForAll();
		changeHappened();
		if (scrollWrapper.isPresent()) {
			final JScrollPane scrollPane = scrollWrapper.get();
			new ScrollJScrollPaneToBottom(scrollPane);
		}
		revalidate();
		repaint();
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
			final List<Appointment> appointments = getInput();
			handler.updateAppointments(appointments);
			for (final AppointmentFieldWrapper appointmentField : appointmentFields) {
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
		for (final AppointmentFieldWrapper appointmentField : appointmentFields) {
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
		for (final AppointmentFieldWrapper appointmentField : appointmentFields) {
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
		final AppointmentFieldWrapper appointmentField = appointmentFields.get(indexToDelete);
		if (indexToDelete < handler.getSize()) {
			handler.delete(indexToDelete);
		}
		for (int i = indexToDelete + 1; i < appointmentFields.size(); i++) {
			appointmentFields.get(i).setIndex(i - 1);
		}
		appointmentFields.remove(indexToDelete);
		remove(appointmentField);
		setAlternateBackgroundsForAll();
		changeHappened();
		revalidate();
		repaint();
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
		return handler.getSize();
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
	 * Returns the appointment field at the given index to be able to manually
	 * change the input.
	 * 
	 * @param i
	 * @return The appointment field at the given index.
	 * @throws IllegalArgumentException If the index doesn't map to an appointment
	 *                                  field in the list.
	 */
	public AppointmentFieldWrapper getAppointmentField(final int i) throws IllegalArgumentException {
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
		final Dimension preferredSize = getPreferredSize();
		final AppointmentFieldWrapper dummy = new AppointmentFieldWrapper(this, 0);
		final Dimension preferredSizeOfDummy = dummy.getPreferredSize();
		return new Dimension(preferredSize.width,
				MAX_NUMBER_OF_SHOWN_APPOINTMENTS * (preferredSizeOfDummy.height + VERTICAL_SPACE_BETWEEN_APPOINTMENTS)
						+ MARGIN_AT_BOTTOM);
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
		final AppointmentFieldWrapper dummy = new AppointmentFieldWrapper(this, 0);
		final Dimension preferredSizeOfDummy = dummy.getPreferredSize();
		return new Dimension(preferredSizeOfDummy.width + 2 * HORIZONTAL_MARGIN,
				appointmentFields.size() * (preferredSizeOfDummy.height + VERTICAL_SPACE_BETWEEN_APPOINTMENTS)
						+ MARGIN_AT_BOTTOM);
	}

	/**
	 * Sets the given scrollWrapper which vertical scrollbar will be updated when
	 * this list of appointment fields is changed.
	 * 
	 * @param scrollWrapper
	 */
	public void setParentScrollWrapper(final JScrollPane scrollWrapper) {
		this.scrollWrapper = Optional.of(scrollWrapper);
	}

	private void setup(final List<Appointment> initialAppointments) {
		setLayout(new FlowLayout(FlowLayout.CENTER, HORIZONTAL_MARGIN, VERTICAL_SPACE_BETWEEN_APPOINTMENTS));
		setBackground(DEFAULT_BACKGROUND);
		for (int i = 0; i < initialAppointments.size(); i++) {
			final AppointmentFieldWrapper appointmentFieldController = new AppointmentFieldWrapper(this,
					initialAppointments.get(i), i);
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
			final AppointmentFieldWrapper appointmentField = appointmentFields.get(i);
			appointmentField.setDefaultBackground(
					i % 2 == 0 ? BACKGROUND_OF_APPOINTMENT_FIELD_0 : BACKGROUND_OF_APPOINTMENT_FIELD_1);
		}
	}

	public List<AppointmentFieldWrapper> getAllAppointmentFieldWrappers() {
		return appointmentFields;
	}
}
