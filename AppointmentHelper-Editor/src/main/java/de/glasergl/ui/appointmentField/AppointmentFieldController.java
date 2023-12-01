package de.glasergl.ui.appointmentField;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ChangeListener;

import de.glasergl.appointment.Appointment;
import de.glasergl.appointment.InvalidAppointmentException;
import de.glasergl.simpleDate.SimpleDate;
import de.glasergl.standard.swing.border.LeftRightRoundBorder;
import de.glasergl.standard.swing.eventListener.emptyImplementation.MyDocumentListener;
import de.glasergl.swing.DefaultJComponentFactory;

/**
 * Class which controls a single AppointmentField.
 *
 * @author Gabriel Glaser
 * @version 30.6.2022
 */
public final class AppointmentFieldController extends JPanel {

	private static final int HORIZONTAL_GAP_OF_SUPCOMPONENTS = 20;
	private static final int VERTICAL_GAP_OF_SUPCOMPONENTS = 10;
	private static final Color UNSTORED_BACKGROUND = new Color(170, 227, 159);

	private final AppointmentFieldControllerList appointmentFieldList;
	private final AppointmentField appointmentField;
	private final JButton deleteButton = DefaultJComponentFactory.getDefaultJButton();

	private int currentIndex;
	private Color defaultBackground = Color.WHITE;

	/**
	 * Initializes the controller for an AppointmentField and adding the given
	 * Appointment as initial input of the AppointmentField.
	 *
	 * @param storageModel   - which represents the logic of operating with
	 *                       appointments.
	 * @param initialDisplay - the Appointment which is initially shown (it's
	 *                       assumed this Appointment is already stored)
	 * @param initialIndex   - the index in which this is initially in the list.
	 */
	private AppointmentFieldController(final AppointmentFieldControllerList appointmentFieldList,
			final Optional<Appointment> initialDisplay, final int initialIndex) {
		super();
		this.appointmentFieldList = appointmentFieldList;
		this.appointmentField = initialDisplay.isPresent() ? new AppointmentField(initialDisplay.get())
				: new AppointmentField();
		this.currentIndex = initialIndex;
		if (!initialDisplay.isPresent()) {
			setBackground(UNSTORED_BACKGROUND);
		}
		setBorder(new LeftRightRoundBorder(20, 15));
		setup();
	}

	public AppointmentFieldController(final AppointmentFieldControllerList parent, final Appointment initialDisplay,
			final int initialIndex) {
		this(parent, Optional.of(initialDisplay), initialIndex);
	}

	public AppointmentFieldController(final AppointmentFieldControllerList parent, final int initialIndex) {
		this(parent, Optional.empty(), initialIndex);
	}

	public void delete() {
		appointmentFieldList.delete(currentIndex);
	}

	/**
	 * @return The index of this is in the list of appointments.
	 */
	public int getCurrentIndex() {
		return currentIndex;
	}

	/**
	 * Updates the index if the list of appointments is updated. Therefore, this is
	 * at another position in the list of appointments. If the index is updated to
	 * -1, that means this got deleted.
	 * 
	 * @param index
	 */
	public void setIndex(final int index) {
		if (index < -1) {
			throw new IllegalArgumentException(index + " is not a valid index.");
		}
		this.currentIndex = index;
	}

	/**
	 * @return Whether the controlled AppointmentField's current content represents
	 *         a correct Appointment.
	 */
	public boolean representsValidAppointment() {
		return appointmentField.representsValidAppointment();
	}

	public void setDefaultBackground(final Color newDefaultBackground) {
		this.defaultBackground = newDefaultBackground;
		if (!getBackground().equals(UNSTORED_BACKGROUND)) {
			setDefaultBackground();
		}
	}

	public void setDefaultBackground() {
		setBackground(defaultBackground);
	}

	private void setup() {
		setLayout(new FlowLayout(FlowLayout.LEFT, HORIZONTAL_GAP_OF_SUPCOMPONENTS, VERTICAL_GAP_OF_SUPCOMPONENTS));
		addListenersForInputChanges();
		deleteButton.setText("Löschen");
		deleteButton.addActionListener(click -> {
			delete();
		});
		add(appointmentField);
		add(deleteButton);
	}

	private void addListenersForInputChanges() {
		final MyDocumentListener documentListener = () -> {
			setBackground(UNSTORED_BACKGROUND);
			appointmentFieldList.changeHappened();
		};
		final ChangeListener changeListener = change -> {
			setBackground(UNSTORED_BACKGROUND);
			appointmentFieldList.changeHappened();
		};
		appointmentField.getNameField().getDocument().addDocumentListener(documentListener);
		appointmentField.getDescriptionField().getDocument().addDocumentListener(documentListener);
		appointmentField.getDateField().addChangeListener(changeListener);
		appointmentField.getIsBirthdayField().addChangeListener(changeListener);
	}

	@Override
	public void setBackground(final Color newBackground) {
		super.setBackground(newBackground);
		if (appointmentField != null) {
			appointmentField.setBackground(newBackground);
		}
	}

	public Appointment getAppointment() throws InvalidAppointmentException {
		return appointmentField.getAppointment();
	}

	public SimpleDate getDate() {
		return appointmentField.getDate();
	}

	@Override
	public String getName() {
		return appointmentField.getName();
	}

	public String getDescription() {
		return appointmentField.getDescription();
	}

	public boolean isBirthday() {
		return appointmentField.isBirthday();
	}

	public void setInputAppointment(final Appointment newAppointment) {
		appointmentField.setAppointment(newAppointment);
	}

	public void setDate(final SimpleDate newDate) {
		appointmentField.setDate(newDate);
	}

	@Override
	public void setName(final String newName) {
		appointmentField.setName(newName);
	}

	public void setDescription(final String newDescription) {
		appointmentField.setDescription(newDescription);
	}

	public void setIsBirthday(final boolean newIsBirthday) {
		appointmentField.setIsBirthday(newIsBirthday);
	}

}
