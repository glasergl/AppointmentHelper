package de.glasergl.appointment.helper.editor.ui.appointmentField;

import java.awt.FlowLayout;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.glasergl.appointment.helper.util.entity.Appointment;
import de.glasergl.appointment.helper.util.entity.SimpleDate;
import de.glasergl.appointment.helper.util.swing.CustomizedSwing;

/**
 * Visual Input-Field for an Appointment.
 *
 * @author glasergl
 */
public class AppointmentField extends JPanel {
	private static final int NAME_WIDTH = 16;
	private static final int DISTANCE_BETWEEN_SUB_COMPONENTS = 10;
	private static final int TOP_BOTTOM_MARGIN = 5;
	private static final boolean STANDARD_IS_BIRTHDAY = true;

	private final JTextField dateField = new JTextField(8);
	private final JTextField nameField = CustomizedSwing.getDefaultJTextField();
	private final JCheckBox isBirthdayField = new JCheckBox("ist Geburtstag", STANDARD_IS_BIRTHDAY);

	/**
	 * @param initialDisplay - Appointment which is initially displayed by this.
	 */
	public AppointmentField(final Appointment initialDisplay) {
		this();
		setDate(initialDisplay.getDate());
		setAppointmentName(initialDisplay.getName());
		setIsBirthday(initialDisplay.isBirthday());
	}

	/**
	 * Depicts standard values: Date of today, empty name and isBirthday set to
	 * true.
	 */
	public AppointmentField() {
		super();
		setupInputComponents();
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
	 * @throws IllegalStateException if the current name-field is empty.
	 */
	public Appointment getAppointment() throws IllegalStateException {
		if (!representsValidAppointment()) {
			throw new IllegalStateException();
		} else {
			return new Appointment(getDate(), getAppointmentName(), isBirthday());
		}
	}

	/**
	 * Replaces the current input with the content of the given Appointment.
	 *
	 * @param newAppointment
	 */
	public void setAppointment(final Appointment newAppointment) {
		setDate(newAppointment.getDate());
		setAppointmentName(newAppointment.getName());
		setIsBirthday(newAppointment.isBirthday());
	}

	private void setupInputComponents() {
		setLayout(new FlowLayout(FlowLayout.CENTER, DISTANCE_BETWEEN_SUB_COMPONENTS, TOP_BOTTOM_MARGIN));
		nameField.setColumns(NAME_WIDTH);
		add(dateField);
		add(nameField);
		add(isBirthdayField);
	}

	public JTextField getNameField() {
		return nameField;
	}

	public JCheckBox getIsBirthdayField() {
		return isBirthdayField;
	}

	public SimpleDate getDate() {
		final String dateText = dateField.getText();
		final String[] dayMonth = dateText.split("\\.");
		return new SimpleDate(Integer.parseInt(dayMonth[0]), Integer.parseInt(dayMonth[1]));
	}

	public String getAppointmentName() {
		return nameField.getText();
	}

	public boolean isBirthday() {
		return isBirthdayField.isSelected();
	}

	public void setDate(final SimpleDate newDate) {
		dateField.setText(newDate.toString());
	}

	public void setAppointmentName(final String newName) {
		nameField.setText(newName);
	}

	public void setIsBirthday(final boolean newIsBirthday) {
		isBirthdayField.setSelected(newIsBirthday);
	}
}
