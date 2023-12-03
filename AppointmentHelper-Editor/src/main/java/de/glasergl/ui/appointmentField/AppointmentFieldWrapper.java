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
 * Class which wraps a single AppointmentField and provides more functionality
 * controlling the input field.
 *
 * @author Gabriel Glaser
 */
public final class AppointmentFieldWrapper extends JPanel {
	private static final int HORIZONTAL_GAP_OF_SUPCOMPONENTS = 20;
	private static final Color UNSTORED_BACKGROUND = new Color(170, 227, 159);
	private static final Color ILLEGAL_STATE_BACKGROUND = new Color(255, 115, 122);

	private final AppointmentFieldWrapperList appointmentFieldList;
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
	private AppointmentFieldWrapper(final AppointmentFieldWrapperList appointmentFieldList,
			final Optional<Appointment> initialDisplay, final int initialIndex) {
		super();
		this.appointmentFieldList = appointmentFieldList;
		this.appointmentField = initialDisplay.isPresent() ? new AppointmentField(initialDisplay.get())
				: new AppointmentField();
		this.currentIndex = initialIndex;
		if (!initialDisplay.isPresent()) {
			setBackground(UNSTORED_BACKGROUND);
		}
		setup();
	}

	public AppointmentFieldWrapper(final AppointmentFieldWrapperList parent, final Appointment initialDisplay,
			final int initialIndex) {
		this(parent, Optional.of(initialDisplay), initialIndex);
	}

	public AppointmentFieldWrapper(final AppointmentFieldWrapperList parent, final int initialIndex) {
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
		setLayout(new FlowLayout(FlowLayout.CENTER, HORIZONTAL_GAP_OF_SUPCOMPONENTS, 0));
		setBorder(new LeftRightRoundBorder(20, 15));
		addListenersForInputChanges();
		deleteButton.setText("Löschen");
		deleteButton.addActionListener(click -> {
			delete();
		});
		add(appointmentField);
		add(deleteButton);
	}

	private void addListenersForInputChanges() {
		final MyDocumentListener documentListenerForChange = () -> {
			final String appointmentName = getAppointmentName();
			if (appointmentName.length() > 0) {
				setBackground(UNSTORED_BACKGROUND);
			}
			appointmentFieldList.changeHappened();
		};
		final ChangeListener changeListener = change -> {
			setBackground(UNSTORED_BACKGROUND);
			appointmentFieldList.changeHappened();
		};
		final MyDocumentListener documentListenerForIllegalState = () -> {
			final String appointmentName = getAppointmentName();
			if (appointmentName.length() == 0) {
				setBackground(ILLEGAL_STATE_BACKGROUND);
			}
		};
		appointmentField.getNameField().getDocument().addDocumentListener(documentListenerForChange);
		appointmentField.getNameField().getDocument().addDocumentListener(documentListenerForIllegalState);
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

	public String getAppointmentName() {
		return appointmentField.getAppointmentName();
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

	public void setAppointmentName(final String newName) {
		appointmentField.setAppointmentName(newName);
	}

	public void setIsBirthday(final boolean newIsBirthday) {
		appointmentField.setIsBirthday(newIsBirthday);
	}
}
