package ui.appointmentField;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import appointment.Appointment;
import date.SimpleDate;
import myComponent.MyCheckBox;
import myComponent.textField.MyTextField;
import settings.Colors;
import ui.dateField.MySimpleDateField;

/**
 * Visual Input-Field for an Appointment.
 * 
 * @author Gabriel Glaser
 * @version 3.1.2022
 */
public class AppointmentField extends JPanel {

    private static final Color DATE_FIELD_COLOR_WHILE_HOVERED = Colors.getGray(3);
    private static final int NAME_WIDTH = 16;
    private static final int DESCRIPTION_WIDTH = 30;
    private static final int DISTANCE_BETWEEN_SUB_COMPONENTS = 10;

    private final MySimpleDateField dateField;
    private final MyTextField nameField;
    private final MyTextField descriptionField;
    private final MyCheckBox isBirthdayField;

    public AppointmentField(final Appointment initialDisplay) {
	super();
	this.dateField = new MySimpleDateField(initialDisplay.getDate());
	this.nameField = new MyTextField("", initialDisplay.getName());
	this.descriptionField = new MyTextField("", initialDisplay.getDescription());
	this.isBirthdayField = new MyCheckBox("ist Geburtstag", initialDisplay.isBirthday());
	setup();
    }

    public AppointmentField() {
	super();
	this.dateField = new MySimpleDateField();
	this.nameField = new MyTextField();
	this.descriptionField = new MyTextField();
	this.isBirthdayField = new MyCheckBox("ist Geburtstag", true);
	setup();
    }

    private void setup() {
	setLayout(new FlowLayout(FlowLayout.LEFT, DISTANCE_BETWEEN_SUB_COMPONENTS, 0));
	dateField.setBackgroundWhileMouseHovered(DATE_FIELD_COLOR_WHILE_HOVERED);
	nameField.setColumns(NAME_WIDTH);
	descriptionField.setColumns(DESCRIPTION_WIDTH);
	add(dateField);
	add(nameField);
	add(descriptionField);
	add(isBirthdayField);
    }

    public Appointment getAppointment() {
	if (!representsValidAppointment()) {
	    throw new IllegalStateException("Current input doesn't represent a valid Appointment");
	}
	return new Appointment(getDate(), getName(), getDescription(), isBirthday());
    }

    public void setAppointment(final Appointment newAppointment) {
	setDate(newAppointment.getDate());
	setName(newAppointment.getName());
	setDescription(newAppointment.getDescription());
	setIsBirthday(newAppointment.isBirthday());
    }

    public boolean representsValidAppointment() {
	final String currentName = nameField.getText();
	return currentName.length() > 0;
    }

    @Override
    public void setBackground(final Color newBackground) {
	super.setBackground(newBackground);
	if (dateField != null) {
	    dateField.setBackground(newBackground);
	}
    }

    public MySimpleDateField getDateField() {
	return dateField;
    }

    public MyTextField getNameField() {
	return nameField;
    }

    public MyTextField getDescriptionField() {
	return descriptionField;
    }

    public MyCheckBox getIsBirthdayField() {
	return isBirthdayField;
    }

    public SimpleDate getDate() {
	return dateField.getDate();
    }

    public String getName() {
	return nameField.getText();
    }

    public String getDescription() {
	return descriptionField.getText();
    }

    public boolean isBirthday() {
	return isBirthdayField.isSelected();
    }

    public void setDate(final SimpleDate newDate) {
	dateField.setDate(newDate);
    }

    public void setName(final String newName) {
	nameField.setText(newName);
    }

    public void setDescription(final String newDescription) {
	descriptionField.setText(newDescription);
    }

    public void setIsBirthday(final boolean newIsBirthday) {
	isBirthdayField.setSelected(newIsBirthday);
    }

}
