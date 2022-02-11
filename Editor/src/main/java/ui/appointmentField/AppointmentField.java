package ui.appointmentField;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import appointment.Appointment;
import appointment.InvalidAppointmentException;
import date.SimpleDate;
import standardSwing.entity.CelestialDirection;
import standardSwing.eventListener.SiblingPopUpDisplayerOnHover;
import standardSwing.eventListener.emptyImplementation.MyDocumentListener;
import standardSwing.myComponent.MyCheckBox;
import standardSwing.myComponent.MyLabel;
import standardSwing.myComponent.textField.MyTextField;
import standardSwing.myComponent.textField.MyTextFieldAttributes;
import standardSwing.settings.Colors;
import ui.dateField.MySimpleDateField;

/**
 * Visual Input-Field for an Appointment.
 * 
 * Furthermore, if the textfield for the name is empty, it gets colored red to
 * indicate it being in an illegal state.
 * 
 * @author Gabriel Glaser
 * @version 11.2.2022
 */
public class AppointmentField extends JPanel {

    private static final int NAME_WIDTH = 16;
    private static final int DESCRIPTION_WIDTH = 30;
    private static final int DISTANCE_BETWEEN_SUB_COMPONENTS = 10;
    private static final boolean STANDARD_IS_BIRTHDAY = true;

    private final MySimpleDateField dateField = new MySimpleDateField();
    private final MyTextField nameField = new MyTextField();
    private final MyTextField descriptionField = new MyTextField();
    private final MyCheckBox isBirthdayField = new MyCheckBox("ist Geburtstag", STANDARD_IS_BIRTHDAY);

    /**
     * @param initialDisplay - Appointment which is initially displayed by this.
     */
    public AppointmentField(final Appointment initialDisplay) {
	super();
	dateField.setDate(initialDisplay.getDate());
	nameField.setText(initialDisplay.getName());
	descriptionField.setText(initialDisplay.getDescription());
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
	    return new Appointment(getDate(), getName(), getDescription(), isBirthday());
	}
    }

    public void setAppointment(final Appointment newAppointment) {
	setDate(newAppointment.getDate());
	setName(newAppointment.getName());
	setDescription(newAppointment.getDescription());
	setIsBirthday(newAppointment.isBirthday());
    }

    private void setup() {
	setLayout(new FlowLayout(FlowLayout.LEFT, DISTANCE_BETWEEN_SUB_COMPONENTS, 0));
	nameField.setColumns(NAME_WIDTH);
	nameField.getBaseImplementation().getDocument().addDocumentListener(new MyDocumentListener() {
	    @Override
	    public void update() {
		final String currentText = nameField.getText();
		if (currentText.length() == 0) {
		    nameField.setBackground(Colors.ofError());
//		    final MyLabel myLabel = new MyLabel("Name muss mindestens einen Buchstaben enthalten!");
//		    new SiblingPopUpDisplayerOnHover(myLabel, nameField, CelestialDirection.SOUTH);
		} else {
		    nameField.setBackground(MyTextFieldAttributes.getBackgroundColor());
		}
	    }
	});
	descriptionField.setColumns(DESCRIPTION_WIDTH);
	add(dateField);
	add(nameField);
	add(descriptionField);
	add(isBirthdayField);
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
