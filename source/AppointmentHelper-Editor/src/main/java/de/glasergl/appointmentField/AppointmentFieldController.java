package de.glasergl.appointmentField;

import java.awt.Color;
import java.awt.FlowLayout;
import java.io.File;
import java.util.Optional;

import javax.swing.JPanel;
import javax.swing.event.ChangeListener;

import de.glasergl.appointment.Appointment;
import de.glasergl.appointment.InvalidAppointmentException;
import de.glasergl.fileInteraction.AppointmentAlreadyAddedException;
import de.glasergl.fileInteraction.AppointmentFileInteracter;
import de.glasergl.simpleDate.SimpleDate;
import de.glasergl.simpleDate.SimpleDates;
import de.glasergl.standard.swing.eventListener.emptyImplementation.MyDocumentListener;
import de.glasergl.standard.swing.myComponent.button.MyJButton;

/**
 * Class which controls an AppointmentField and its interaction with the
 * Appointment-File.
 *
 * @author Gabriel Glaser
 * @version 13.3.2022
 */
public class AppointmentFieldController extends JPanel {

    private static final int HORIZONTAL_GAP_OF_SUPCOMPONENTS = 20;
    private static final int VERTICAL_GAP_OF_SUPCOMPONENTS = 10;
    private static final Color UNSAVED_BACKGROUND = new Color(245, 240, 86);

    private final AllAppointmentFields parent;

    private final AppointmentField appointmentField;
    private final MyJButton cancelButton = new MyJButton("Abbrechen");
    private final MyJButton deleteButton = new MyJButton("Löschen");

    private Optional<Appointment> currentlyStoredAppointment = Optional.empty();
    private Color defaultBackground = Color.WHITE;

    /**
     * Initializes the controller for an AppointmentField and adding the given
     * Appointment as initial input of the AppointmentField.
     *
     * @param parent         - which contains all AppointmentFieldController
     *                       (including this)
     * @param initialDisplay - the Appointment which is initially shown (it's
     *                       assumed this Appointment is already stored)
     */
    public AppointmentFieldController(final AllAppointmentFields parent, final Appointment initialDisplay) {
	super();
	this.parent = parent;
	this.currentlyStoredAppointment = Optional.of(initialDisplay);
	this.appointmentField = new AppointmentField(initialDisplay);
	setup();
    }

    /**
     * Initializes the controller for an AppointmentField and letting the input be
     * the default values for an Appointment.
     *
     * @param parent - which contains all AppointmentFieldController (including
     *               this)
     */
    public AppointmentFieldController(final AllAppointmentFields parent) {
	super();
	this.parent = parent;
	this.appointmentField = new AppointmentField();
	setBackground(UNSAVED_BACKGROUND);
	setup();
    }

    /**
     * Saves the content of the controlled Appointment-Field to the default
     * Appointment-File.
     *
     * @throws InvalidAppointmentException      if the current content of the
     *                                          AppointmentField isn't valid.
     * @throws AppointmentAlreadyAddedException if the Appointment represented by
     *                                          the current state already exists.
     */
    public void save() throws InvalidAppointmentException, AppointmentAlreadyAddedException {
	save(AppointmentFileInteracter.getDefaultAppointmentFile());
    }

    /**
     * Saves the content of the controlled Appointment-Field to the given
     * Appointment-File.
     *
     * @param appointmentFile in which the current input should be stored.
     * @throws InvalidAppointmentException      if the current content of the
     *                                          Appointment-Field isn't valid.
     * @throws AppointmentAlreadyAddedException if the appointment represented by
     *                                          the current state already exists.
     */
    public void save(final File appointmentFile) throws InvalidAppointmentException, AppointmentAlreadyAddedException {
	final Appointment currentContent = appointmentField.getAppointment();
	if (currentlyStoredAppointment.isPresent()) {
	    AppointmentFileInteracter.remove(currentlyStoredAppointment.get(), appointmentFile);
	}
	AppointmentFileInteracter.add(currentContent, appointmentFile);
	currentlyStoredAppointment = Optional.of(currentContent);
	setBackground(defaultBackground);
    }

    /**
     * Sets the content to the last stored version of this Controller, if it exists.
     * If the input has never been stored, yet, default values are set.
     */
    public void cancel() {
	if (currentlyStoredAppointment.isPresent()) {
	    appointmentField.setAppointment(currentlyStoredAppointment.get());
	} else {
	    appointmentField.setDate(SimpleDates.getToday());
	    appointmentField.setName("");
	    appointmentField.setDescription("");
	    appointmentField.setIsBirthday(true);
	}
    }

    /**
     * Removes the Appointment controlled by this and adds it to a Stack of
     * Appointments which makes it possible to restore it later.
     */
    public void delete() {
	delete(AppointmentFileInteracter.getDefaultAppointmentFile());
    }

    /**
     * Removes the Appointment controlled by this and adds it to a Stack of
     * Appointments which makes it possible to restore it later.
     *
     * @param appointmentFile from which this should be deleted.
     */
    public void delete(final File appointmentFile) {
	appointmentField.getDateField().setDateInputVisible(false);
	if (currentlyStoredAppointment.isPresent()) {
	    AppointmentFileInteracter.remove(currentlyStoredAppointment.get(), appointmentFile);
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
	if (currentlyStoredAppointment.isPresent() && appointmentField.representsValidAppointment()) {
	    final Appointment currentInput = appointmentField.getAppointment();
	    return currentInput.equals(currentlyStoredAppointment.get());
	} else {
	    return false;
	}
    }

    /**
     * @return Whether the controlled AppointmentField's current content represents
     *         a correct Appointment.
     */
    public boolean representsValidAppointment() {
	return appointmentField.representsValidAppointment();
    }

    /**
     * Updates the background of this. The background indicates the state of the
     * controlled Appointment.
     *
     * Default background if the current input is stored. Yellow background if the
     * current input doesn't match the stored version or there is no stored version.
     */
    public void updateBackground() {
	if (isSaved()) {
	    setBackground(defaultBackground);
	} else {
	    setBackground(UNSAVED_BACKGROUND);
	}
    }

    /**
     * Sets the default background which is only depicted, if the displayed
     * Appointment is saved.
     *
     * Else UNSAVED_BACKGROUND is shown and the given newDefaultBackground will be
     * visible as soon as the controlled Appointment is saved again.
     *
     * @param newDefaultBackground
     */
    public void setDefaultBackground(final Color newDefaultBackground) {
	defaultBackground = newDefaultBackground;
	final Color currentBackground = getBackground();
	if (currentBackground != UNSAVED_BACKGROUND) {
	    setBackground(defaultBackground);
	}
    }

    public boolean hasDefaultBackground() {
	return getBackground().equals(defaultBackground);
    }

    public boolean hasUnsavedBackground() {
	return getBackground().equals(UNSAVED_BACKGROUND);
    }

    private void setup() {
	setLayout(new FlowLayout(FlowLayout.LEFT, HORIZONTAL_GAP_OF_SUPCOMPONENTS, VERTICAL_GAP_OF_SUPCOMPONENTS));
	addListenersForBackgroundChanges();
	setupButtons();
	add(appointmentField);
	add(cancelButton);
	add(deleteButton);
    }

    private void addListenersForBackgroundChanges() {
	final MyDocumentListener toUpdateBackgroundDocumentListener = new MyDocumentListener() {
	    @Override
	    public void update() {
		updateBackground();
	    }
	};
	final ChangeListener toUpdateBackgroundChangeListener = change -> {
	    updateBackground();
	};
	appointmentField.getDateField().addChangeListener(toUpdateBackgroundChangeListener);
	appointmentField.getNameField().addDocumentListener(toUpdateBackgroundDocumentListener);
	appointmentField.getDescriptionField().addDocumentListener(toUpdateBackgroundDocumentListener);
	appointmentField.getIsBirthdayField().addChangeListener(toUpdateBackgroundChangeListener);
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

    public Appointment getStoredAppointment() {
	return currentlyStoredAppointment.get();
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
