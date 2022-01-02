package ui.appointmentInput;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Optional;
import javax.swing.JPanel;
import appointment.Appointment;
import date.SimpleDate;
import fileInteraction.AppointmentFileInteracter;
import myComponent.button.MyTextButton;

public class AppointmentFieldController extends JPanel {

    private static final int DISTANCE_BETWEEN = 20;
    private static final int VERTICAL_MARGIN = 20;

    private final AppointmentFieldPanel parent;

    private final AppointmentFieldState stateDisplayer = new AppointmentFieldState();
    private final AppointmentField appointmentInputField;
    private final MyTextButton cancelButton = new MyTextButton("Cancel");
    private final MyTextButton deleteButton = new MyTextButton("Delete");

    private Optional<Appointment> currentlyStoredAppointment = Optional.empty();

    public AppointmentFieldController(final AppointmentFieldPanel parent, final Appointment initialDisplay) {
	super();
	this.parent = parent;
	this.currentlyStoredAppointment = Optional.of(initialDisplay);
	this.appointmentInputField = new AppointmentField(initialDisplay);
	setup();
    }

    public AppointmentFieldController(final AppointmentFieldPanel parent) {
	super();
	this.parent = parent;
	this.currentlyStoredAppointment = Optional.empty();
	this.appointmentInputField = new AppointmentField();
	stateDisplayer.toUnsaved();
	setup();
    }

    public void save() {
	if (!appointmentInputField.representsValidAppointment()) {
	    stateDisplayer.toError();
	} else {
	    if (currentlyStoredAppointment.isPresent()) {
		AppointmentFileInteracter.remove(currentlyStoredAppointment.get());
	    }
	    final Appointment currentContent = appointmentInputField.getAppointment();
	    AppointmentFileInteracter.add(currentContent);
	    currentlyStoredAppointment = Optional.of(currentContent);
	    stateDisplayer.toNothing();
	}
    }

    private void setup() {
	setLayout(new FlowLayout(FlowLayout.LEFT, DISTANCE_BETWEEN, VERTICAL_MARGIN));
	setupButtons();
	add(stateDisplayer);
	add(appointmentInputField);
	add(cancelButton);
	add(deleteButton);
    }

    private void setupButtons() {
	cancelButton.addActionListener((click) -> {
	    if (currentlyStoredAppointment.isPresent()) {
		appointmentInputField.setAppointment(currentlyStoredAppointment.get());
	    }
	});
	deleteButton.addActionListener((click) -> {
	    if (currentlyStoredAppointment.isPresent()) {
		AppointmentFileInteracter.remove(currentlyStoredAppointment.get());
	    }
	    parent.removeAppointmentInputField(this);
	});
    }

    @Override
    public void setBackground(final Color newBackground) {
	super.setBackground(newBackground);
	if (stateDisplayer != null && appointmentInputField != null) {
	    stateDisplayer.setBackground(newBackground);
	    appointmentInputField.setBackground(newBackground);
	}
    }

    public AppointmentFieldState.State getState() {
	return stateDisplayer.getState();
    }

    public SimpleDate getDate() {
	return appointmentInputField.getDate();
    }

    public String getName() {
	return appointmentInputField.getName();
    }

    public String getDescription() {
	return appointmentInputField.getDescription();
    }

    public boolean isBirthday() {
	return appointmentInputField.isBirthday();
    }

    public void setDate(final SimpleDate newSimpleDate) {
	appointmentInputField.setDate(newSimpleDate);
    }

    public void setName(final String newName) {
	appointmentInputField.setName(newName);
    }

    public void setDescription(final String newDescription) {
	appointmentInputField.setDescription(newDescription);
    }

    public void setIsBirthday(final boolean newIsBirthday) {
	appointmentInputField.setIsBirthday(newIsBirthday);
    }

}
