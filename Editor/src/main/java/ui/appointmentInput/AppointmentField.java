package ui.appointmentInput;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import appointment.Appointment;
import date.SimpleDate;
import myComponent.MyCheckBox;
import myComponent.textField.MyTextField;
import settings.Colors;
import ui.dateInput.MySimpleDateField;

/**
 * InputField for an Appointment.
 * 
 * @author Gabriel Glaser
 * @version 1.1.2022
 */
public class AppointmentField extends JPanel {

    private static final int NAME_WIDTH = 16;
    private static final int DESCRITPION_WIDTH = 30;
    private static final int DISTANCE_BETWEEN = 10;

    private final MySimpleDateField dateInputField;
    private final MyTextField nameInputField;
    private final MyTextField descriptionInputField;
    private final MyCheckBox isBirthdayInputField;

    public AppointmentField(final Appointment initialDisplay) {
	super();
	this.dateInputField = new MySimpleDateField(initialDisplay.getDate());
	this.nameInputField = new MyTextField("", initialDisplay.getName());
	this.descriptionInputField = new MyTextField("", initialDisplay.getDescription());
	this.isBirthdayInputField = new MyCheckBox("ist Geburtstag", initialDisplay.isBirthday());
	setup();
    }

    public AppointmentField() {
	super();
	this.dateInputField = new MySimpleDateField();
	this.nameInputField = new MyTextField();
	this.descriptionInputField = new MyTextField();
	this.isBirthdayInputField = new MyCheckBox("ist Geburtstag", true);
	setup();
    }

    private void setup() {
	setLayout(new FlowLayout(FlowLayout.LEFT, DISTANCE_BETWEEN, 0));
	nameInputField.setColumns(NAME_WIDTH);
	descriptionInputField.setColumns(DESCRITPION_WIDTH);
	add(dateInputField);
	add(nameInputField);
	add(descriptionInputField);
	add(isBirthdayInputField);
    }

    public Appointment getAppointment() {
	if (!representsValidAppointment()) {
	    throw new IllegalStateException("Input-Field doesn't represent a valid Appointment");
	}
	return new Appointment(getDate(), getName(), getDescription(), isBirthday());
    }

    public void setAppointment(final Appointment appointmentToSet) {
	setDate(appointmentToSet.getDate());
	setName(appointmentToSet.getName());
	setDescription(appointmentToSet.getDescription());
	setIsBirthday(appointmentToSet.isBirthday());
    }

    public boolean representsValidAppointment() {
	final String currentName = nameInputField.getText();
	return currentName.length() > 0;
    }

    @Override
    public void setBackground(final Color newBackground) {
	super.setBackground(newBackground);
	if (dateInputField != null) {
	    dateInputField.setBackground(newBackground);
	    dateInputField.setBackgroundWhileMouseHovered(Colors.getGray(3));
	}
    }

    public SimpleDate getDate() {
	return dateInputField.getDate();
    }

    public String getName() {
	return nameInputField.getText();
    }

    public String getDescription() {
	return descriptionInputField.getText();
    }

    public boolean isBirthday() {
	return isBirthdayInputField.isSelected();
    }

    public void setDate(final SimpleDate newSimpleDate) {
	dateInputField.setDate(newSimpleDate);
    }

    public void setName(final String newName) {
	nameInputField.setText(newName);
    }

    public void setDescription(final String newDescription) {
	descriptionInputField.setText(newDescription);
    }

    public void setIsBirthday(final boolean newIsBirthday) {
	isBirthdayInputField.setSelected(newIsBirthday);
    }

}
