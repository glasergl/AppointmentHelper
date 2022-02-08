package ui.appointmentField;

import java.awt.Color;
import java.awt.FlowLayout;
import java.io.File;
import java.util.Optional;
import java.util.Stack;
import javax.swing.JPanel;
import javax.swing.event.ChangeListener;
import appointment.Appointment;
import appointment.InvalidAppointmentException;
import date.SimpleDate;
import standardSwing.eventListener.emptyImplementation.MyDocumentListener;
import fileInteraction.AppointmentFileInteracter;
import standardSwing.myComponent.button.MyTextButton;
import standardSwing.settings.Colors;

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
    private static final Color DEFAULT_BACKGROUND = Colors.getGray(2);
    private static final Color UNSAVED_BACKGROUND = new Color(240, 237, 72);

    private final AppointmentFieldPanel parent;

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
	setup();
    }

    /**
     * Saves the content of the controlled Appointment-Field.
     * 
     * @throws InvalidAppointmentException if the current content of the
     *                                     Appointment-Field isn't valid.
     */
    public void save() throws InvalidAppointmentException {
	save(AppointmentFileInteracter.getDefaultAppointmentFile());
    }

    /**
     * Saves the content of the controlled Appointment-Field.
     * 
     * @param appointmentFile in which the current input should be stored.
     * @throws InvalidAppointmentException if the current content of the
     *                                     Appointment-Field isn't valid.
     */
    public void save(final File appointmentFile) throws InvalidAppointmentException {
	final Appointment currentContent = appointmentField.getAppointment();
	if (currentlyStoredAppointment.isPresent()) {
	    AppointmentFileInteracter.remove(currentlyStoredAppointment.get());
	}
	AppointmentFileInteracter.add(currentContent, appointmentFile);
	currentlyStoredAppointment = Optional.of(currentContent);
    }

    /**
     * Sets the content to the last stored version of this Controller, if any exist.
     */
    public void cancel() {
	if (currentlyStoredAppointment.isPresent()) {
	    appointmentField.setAppointment(currentlyStoredAppointment.get());
	}
    }

    /**
     * Removes the Appointment controlled by this and adds it to a Stack of
     * Appointments which makes it possible to restore it later.
     */
    public void delete() {
	if (currentlyStoredAppointment.isPresent()) {
	    AppointmentFileInteracter.remove(currentlyStoredAppointment.get());
	    DELETED.add(this);
	}
	parent.removeAppointmentField(this);
    }

    /**
     * Calculates whether the current input is equivalent to the latest stored
     * version.
     * 
     * Will return false, if the current input isn't a valid Appointment or it
     * hasn't been stored, yet.
     * 
     * @return True, if the current Input is equivalent to the latest stored
     *         version.
     */
    public boolean isSaved() {
	try {
	    final Appointment currentInput = appointmentField.getAppointment();
	    if (currentlyStoredAppointment.isPresent()) {
		return currentInput.equals(currentlyStoredAppointment.get());
	    } else {
		return false;
	    }
	} catch (final InvalidAppointmentException e) {
	    return false;
	}
    }

    public boolean representsValidAppointment() {
	return appointmentField.representsValidAppointment();
    }

    /**
     * Updates the background of this. The background indicates the state of the
     * controlled Appointment.
     * 
     * Standard background if the current input is stored. Yellow background if the
     * current input doesn't match the stored version or there is no stored version,
     * yet. Red if the current input isn't a valid Appointment.
     */
    public void updateBackground() {
	try {
	    final Appointment currentInput = appointmentField.getAppointment();
	    if (currentlyStoredAppointment.isPresent() && currentInput.equals(currentlyStoredAppointment.get())) {
		setBackground(DEFAULT_BACKGROUND);
	    } else {
		setBackground(UNSAVED_BACKGROUND);
	    }
	} catch (final InvalidAppointmentException e) {
	    setBackground(Colors.ofError());
	}
    }

    private void setup() {
	setLayout(new FlowLayout(FlowLayout.LEFT, MARGIN_OF_SUB_COMPONENTS, VERTICAL_MARGIN));
	setBackground(DEFAULT_BACKGROUND);
	addListenersForSaveState();
	setupButtons();
	add(appointmentField);
	add(cancelButton);
	add(deleteButton);
    }

    private void addListenersForSaveState() {
	final MyDocumentListener toUpdateSavedStateDocumentListener = new MyDocumentListener() {
	    public void update() {
		updateBackground();
	    }
	};
	final ChangeListener toUpdateSaveStateChangeListener = change -> {
	    updateBackground();
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

    public String getName() {
	return appointmentField.getName();
    }

    public String getDescription() {
	return appointmentField.getDescription();
    }

    public boolean isBirthday() {
	return appointmentField.isBirthday();
    }

    public void setAppointment(final Appointment newAppointment) {
	appointmentField.setAppointment(newAppointment);
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
