package ui.appointmentField;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Optional;
import java.util.Stack;

import javax.swing.JPanel;
import javax.swing.event.ChangeListener;
import appointment.Appointment;
import date.SimpleDate;
import eventListener.emptyImplementation.MyDocumentListener;
import fileInteraction.AppointmentFileInteracter;
import myComponent.button.MyTextButton;

/**
 * Class which controls an AppointmentField and its interaction with the
 * Appointment-File.
 * 
 * @author Gabriel Glaser
 * @version 3.1.2022
 */
public class AppointmentFieldController extends JPanel {

    private static final int MARGIN_OF_SUB_COMPONENTS = 20;
    private static final int VERTICAL_MARGIN = 20;
    private static final Stack<AppointmentFieldController> DELETED = new Stack<>();

    private final AppointmentFieldPanel parent;

    private final AppointmentFieldState stateDisplay = new AppointmentFieldState();
    private final AppointmentField appointmentField;
    private final MyTextButton cancelButton = new MyTextButton("Abbrechen");
    private final MyTextButton deleteButton = new MyTextButton("Löschen");

    private Optional<Appointment> currentlyStoredAppointment = Optional.empty();

    public AppointmentFieldController(final AppointmentFieldPanel parent, final Appointment initialDisplay) {
	super();
	this.parent = parent;
	this.currentlyStoredAppointment = Optional.of(initialDisplay);
	this.appointmentField = new AppointmentField(initialDisplay);
	setup();
    }

    public AppointmentFieldController(final AppointmentFieldPanel parent) {
	super();
	this.parent = parent;
	this.currentlyStoredAppointment = Optional.empty();
	this.appointmentField = new AppointmentField();
	stateDisplay.toUnsaved();
	setup();
    }

    public void save() {
	if (!appointmentField.representsValidAppointment()) {
	    stateDisplay.toError();
	} else {
	    if (currentlyStoredAppointment.isPresent()) {
		AppointmentFileInteracter.remove(currentlyStoredAppointment.get());
	    }
	    final Appointment currentContent = appointmentField.getAppointment();
	    AppointmentFileInteracter.add(currentContent);
	    currentlyStoredAppointment = Optional.of(currentContent);
	    stateDisplay.toNothing();
	}
    }

    public void cancel() {
	if (currentlyStoredAppointment.isPresent()) {
	    appointmentField.setAppointment(currentlyStoredAppointment.get());
	}
    }

    public void delete() {
	if (currentlyStoredAppointment.isPresent()) {
	    AppointmentFileInteracter.remove(currentlyStoredAppointment.get());
	    DELETED.add(this);
	}
	parent.removeAppointmentField(this);
    }

    public boolean isSaved() {
	if (!appointmentField.representsValidAppointment()) {
	    return false;
	} else if (currentlyStoredAppointment.isPresent()) {
	    final Appointment currentInput = appointmentField.getAppointment();
	    return currentInput.equals(currentlyStoredAppointment.get());
	} else {
	    return false;
	}
    }

    public void updateSavedState() {
	if (!appointmentField.representsValidAppointment()) {
	    stateDisplay.toError();
	} else {
	    final Appointment currentInput = appointmentField.getAppointment();
	    if (currentlyStoredAppointment.isPresent() && currentInput.equals(currentlyStoredAppointment.get())) {
		stateDisplay.toNothing();
	    } else {
		stateDisplay.toUnsaved();
	    }
	}
    }

    private void setup() {
	setLayout(new FlowLayout(FlowLayout.LEFT, MARGIN_OF_SUB_COMPONENTS, VERTICAL_MARGIN));
	addListenersForSaveState();
	setupButtons();
	add(stateDisplay);
	add(appointmentField);
	add(cancelButton);
	add(deleteButton);
    }

    private void addListenersForSaveState() {
	final MyDocumentListener toUpdateSavedStateDocumentListener = new MyDocumentListener() {
	    public void update() {
		updateSavedState();
	    }
	};
	final ChangeListener toUpdateSaveStateChangeListener = change -> {
	    updateSavedState();
	};
	appointmentField.getDateField().addChangeListener(toUpdateSaveStateChangeListener);
	appointmentField.getNameField().getBaseImplementation().getDocument().addDocumentListener(toUpdateSavedStateDocumentListener);
	appointmentField.getDescriptionField().getBaseImplementation().getDocument().addDocumentListener(toUpdateSavedStateDocumentListener);
	appointmentField.getIsBirthdayField().addChangeListener(toUpdateSaveStateChangeListener);
    }

    private void setupButtons() {
	cancelButton.addActionListener((click) -> {
	    cancel();
	});
	deleteButton.addActionListener((click) -> {
	    delete();
	});
    }

    @Override
    public void setBackground(final Color newBackground) {
	super.setBackground(newBackground);
	if (stateDisplay != null && appointmentField != null) {
	    stateDisplay.setBackground(newBackground);
	    appointmentField.setBackground(newBackground);
	}
    }

    public AppointmentFieldState.State getState() {
	return stateDisplay.getState();
    }

    public SimpleDate getDate() {
	return appointmentField.getDate();
    }

    public String getName() {
	return appointmentField.getName();
    }

    public String getDescription() {
	return appointmentField.getDescription();
    }

    public boolean isBirthday() {
	return appointmentField.isBirthday();
    }

    public void setDate(final SimpleDate newDate) {
	appointmentField.setDate(newDate);
    }

    public void setName(final String newName) {
	appointmentField.setName(newName);
    }

    public void setDescription(final String newDescription) {
	appointmentField.setDescription(newDescription);
    }

    public void setIsBirthday(final boolean newIsBirthday) {
	appointmentField.setIsBirthday(newIsBirthday);
    }

    public static void restoreLastDeleted() {
	if (DELETED.size() > 0) {
	    final AppointmentFieldController lastDeleted = DELETED.pop();
	    lastDeleted.parent.addAppointmentField(lastDeleted.currentlyStoredAppointment.get());
	}
    }

}
