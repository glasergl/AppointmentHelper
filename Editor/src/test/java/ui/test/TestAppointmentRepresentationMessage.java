package ui.test;

import static org.junit.Assert.*;
import org.junit.Test;
import appointment.Appointment;
import date.SimpleDate;
import ui.appointmentField.AppointmentFieldController;
import ui.appointmentField.AppointmentFieldPanel;
import ui.appointmentField.AppointmentFieldState;

/**
 * @author Gabriel Glaser
 * @version 19.01.2022
 */
public class TestAppointmentRepresentationMessage {

    static AppointmentFieldPanel allAppointments = new AppointmentFieldPanel();
    static Appointment testAppointment = new Appointment(new SimpleDate(10, 10), "Frodo", false);

    static AppointmentFieldState.State nothing = AppointmentFieldState.State.NOTHING;
    static AppointmentFieldState.State unsaved = AppointmentFieldState.State.UNSAVED;
    static AppointmentFieldState.State error = AppointmentFieldState.State.ERROR;

    @Test
    public void testInitialMessage() {
	AppointmentFieldController testWithoutInitialAppointment = new AppointmentFieldController(allAppointments);
	assertEquals(unsaved, testWithoutInitialAppointment.getState());
	AppointmentFieldController testWithInitialAppointment = new AppointmentFieldController(allAppointments, testAppointment);
	assertEquals(nothing, testWithInitialAppointment.getState());
    }

    @Test
    public void testErrorMessageOnEmptyAppointmentRepresentation() {
	AppointmentFieldController testWithoutInitialAppointment = new AppointmentFieldController(allAppointments);
	testWithoutInitialAppointment.save();
	assertEquals(error, testWithoutInitialAppointment.getState());
    }

    @Test
    public void testErrorMessageAppointmentRepresentationWithInitialAppointment() {
	AppointmentFieldController testWithInitialAppointment = new AppointmentFieldController(allAppointments, testAppointment);
	testWithInitialAppointment.setName("");
	testWithInitialAppointment.save();
	assertEquals(error, testWithInitialAppointment.getState());
    }

    @Test
    public void testUnsavedMessageDate() {
	AppointmentFieldController testWithInitialAppointment = new AppointmentFieldController(allAppointments, testAppointment);
	testWithInitialAppointment.setDate(new SimpleDate(15, 10));
	assertEquals(unsaved, testWithInitialAppointment.getState());
	testWithInitialAppointment.setDate(new SimpleDate(10, 10));
	assertEquals(nothing, testWithInitialAppointment.getState());
    }

    @Test
    public void testUnsavedMessageName() {
	AppointmentFieldController testWithInitialAppointment = new AppointmentFieldController(allAppointments, testAppointment);
	testWithInitialAppointment.setName("Sam");
	assertEquals(unsaved, testWithInitialAppointment.getState());
	testWithInitialAppointment.setName("Frodo");
	assertEquals(nothing, testWithInitialAppointment.getState());
    }

    @Test
    public void testUnsavedMessageDescription() {
	AppointmentFieldController testWithInitialAppointment = new AppointmentFieldController(allAppointments, testAppointment);
	testWithInitialAppointment.setDescription("Hello There");
	assertEquals(unsaved, testWithInitialAppointment.getState());
	testWithInitialAppointment.setDescription("");
	assertEquals(nothing, testWithInitialAppointment.getState());
    }

    @Test
    public void testUnsavedMessageIsABirthday() {
	AppointmentFieldController testWithInitialAppointment = new AppointmentFieldController(allAppointments, testAppointment);
	testWithInitialAppointment.setIsBirthday(true);
	assertEquals(unsaved, testWithInitialAppointment.getState());
	testWithInitialAppointment.setIsBirthday(false);
	assertEquals(nothing, testWithInitialAppointment.getState());
    }

}
