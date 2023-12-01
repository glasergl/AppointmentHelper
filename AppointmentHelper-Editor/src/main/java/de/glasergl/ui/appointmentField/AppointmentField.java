package de.glasergl.ui.appointmentField;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Optional;

import javax.swing.JPanel;
import javax.swing.JTextField;

import de.glasergl.appointment.Appointment;
import de.glasergl.appointment.InvalidAppointmentException;
import de.glasergl.simpleDate.SimpleDate;
import de.glasergl.standard.swing.eventListener.emptyImplementation.MyDocumentListener;
import de.glasergl.standard.swing.myComponent.MyCheckBox;
import de.glasergl.swing.DefaultJComponentFactory;
import de.glasergl.ui.appointmentField.dateField.SimpleDateField;

/**
 * Visual Input-Field for an Appointment.
 *
 * Furthermore, if the TextField for the name is empty, it gets colored red to
 * indicate it being in an illegal state.
 *
 * @author Gabriel Glaser
 */
public class AppointmentField extends JPanel {

	private static final int NAME_WIDTH = 16;
	private static final int DISTANCE_BETWEEN_SUB_COMPONENTS = 10;
	private static final boolean STANDARD_IS_BIRTHDAY = true;

	private final SimpleDateField dateField = new SimpleDateField();
	private final JTextField nameField = DefaultJComponentFactory.getDefaultJTextField();
	private final MyCheckBox isBirthdayField = new MyCheckBox("ist Geburtstag", STANDARD_IS_BIRTHDAY);

	/**
	 * @param initialDisplay - Appointment which is initially displayed by this.
	 */
	public AppointmentField(final Appointment initialDisplay) {
		super();
		dateField.setDate(initialDisplay.getDate());
		nameField.setText(initialDisplay.getName());
		isBirthdayField.setSelected(initialDisplay.isBirthday());
		setup();
	}

	/**
	 * Depicts standard values: Date of today, empty name, empty description and
	 * isBirthday set to true.
	 *
	 * Calling getAppointment() right after calling this constructor will lead to an
	 * InvalidAppointmentException.
	 */
	public AppointmentField() {
		super();
		setup();
	}

	/**
	 * @return Calculates whether the current content represents a valid Appointment
	 *         (name length bigger than zero).
	 */
	public boolean representsValidAppointment() {
		final String currentName = nameField.getText();
		return currentName.length() > 0;
	}

	/**
	 * @return The Appointment represented by the current input.
	 * @throws InvalidAppointmentException if the current name-field is empty.
	 */
	public Appointment getAppointment() throws InvalidAppointmentException {
		if (!representsValidAppointment()) {
			throw new InvalidAppointmentException();
		} else {
			return new Appointment(getDate(), getName(), isBirthday());
		}
	}

	/**
	 * Replaces the current input with the content of the given Appointment.
	 *
	 * @param newAppointment
	 */
	public void setAppointment(final Appointment newAppointment) {
		setDate(newAppointment.getDate());
		setName(newAppointment.getName());
		setIsBirthday(newAppointment.isBirthday());
	}

	private void setup() {
		setLayout(new FlowLayout(FlowLayout.LEFT, DISTANCE_BETWEEN_SUB_COMPONENTS, 0));
		nameField.setColumns(NAME_WIDTH);
		nameField.getDocument().addDocumentListener(new MyDocumentListener() {
			private Optional<Color> oldBackground = Optional.of(nameField.getBackground());

			@Override
			public void update() {
				final String currentText = nameField.getText();
				if (currentText.length() == 0) {
					oldBackground = Optional.of(nameField.getBackground());
					nameField.setBackground(new Color(255, 115, 122));
				} else {
					nameField.setBackground(oldBackground.get());
				}
			}
		});
		add(dateField);
		add(nameField);
		add(isBirthdayField);
	}

	@Override
	public void setBackground(final Color newBackground) {
		super.setBackground(newBackground);
		if (dateField != null) {
			dateField.setBackground(newBackground);
			dateField.setDefaultBackgroundWhileHovered();
		}
	}

	public SimpleDateField getDateField() {
		return dateField;
	}

	public JTextField getNameField() {
		return nameField;
	}

	public MyCheckBox getIsBirthdayField() {
		return isBirthdayField;
	}

	public SimpleDate getDate() {
		return dateField.getDate();
	}

	@Override
	public String getName() {
		return nameField.getText();
	}

	public boolean isBirthday() {
		return isBirthdayField.isSelected();
	}

	public void setDate(final SimpleDate newDate) {
		dateField.setDate(newDate);
	}

	@Override
	public void setName(final String newName) {
		nameField.setText(newName);
	}

	public void setIsBirthday(final boolean newIsBirthday) {
		isBirthdayField.setSelected(newIsBirthday);
	}

}
