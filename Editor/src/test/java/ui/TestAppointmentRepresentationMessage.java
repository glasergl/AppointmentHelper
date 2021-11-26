package ui;

import static org.junit.Assert.*;

import org.junit.Test;

import appointments.Appointment;
import appointments.SimpleDate;

public class TestAppointmentRepresentationMessage {

    static AllAppointments allAppointments = new AllAppointments();
    static Appointment testAppointment = new Appointment(new SimpleDate(10, 10), "Frodo", false);

    @Test
    public void testInitialMessage() {
	AppointmentRepresentation testWithoutInitialAppointment = new AppointmentRepresentation(allAppointments);
	assertEquals("*", testWithoutInitialAppointment.getMessage());
	AppointmentRepresentation testWithInitialAppointment = new AppointmentRepresentation(allAppointments, testAppointment);
	assertEquals("", testWithInitialAppointment.getMessage());
    }

    @Test
    public void testErrorMessageOnEmptyAppointmentRepresentation() {
	AppointmentRepresentation testWithoutInitialAppointment = new AppointmentRepresentation(allAppointments);
	testWithoutInitialAppointment.save();
	assertEquals("!", testWithoutInitialAppointment.getMessage());
    }

    @Test
    public void testErrorMessageAppointmentRepresentationWithInitialAppointment() {
	AppointmentRepresentation testWithInitialAppointment = new AppointmentRepresentation(allAppointments, testAppointment);
	testWithInitialAppointment.setName("");
	testWithInitialAppointment.save();
	assertEquals("!", testWithInitialAppointment.getMessage());
    }

    @Test
    public void testUnsavedMessageDate() {
	AppointmentRepresentation testWithInitialAppointment = new AppointmentRepresentation(allAppointments, testAppointment);
	testWithInitialAppointment.setDate(new SimpleDate(15, 10));
	assertEquals("*", testWithInitialAppointment.getMessage());
	testWithInitialAppointment.setDate(new SimpleDate(10, 10));
	assertEquals("", testWithInitialAppointment.getMessage());
    }

    @Test
    public void testUnsavedMessageName() {
	AppointmentRepresentation testWithInitialAppointment = new AppointmentRepresentation(allAppointments, testAppointment);
	testWithInitialAppointment.setName("Sam");
	assertEquals("*", testWithInitialAppointment.getMessage());
	testWithInitialAppointment.setName("Frodo");
	assertEquals("", testWithInitialAppointment.getMessage());
    }

    @Test
    public void testUnsavedMessageDescription() {
	AppointmentRepresentation testWithInitialAppointment = new AppointmentRepresentation(allAppointments, testAppointment);
	testWithInitialAppointment.setDescription("Hello There");
	assertEquals("*", testWithInitialAppointment.getMessage());
	testWithInitialAppointment.setDescription("");
	assertEquals("", testWithInitialAppointment.getMessage());
    }

    @Test
    public void testUnsavedMessageIsABirthday() {
	AppointmentRepresentation testWithInitialAppointment = new AppointmentRepresentation(allAppointments, testAppointment);
	testWithInitialAppointment.setIsABirthday(true);
	assertEquals("*", testWithInitialAppointment.getMessage());
	testWithInitialAppointment.setIsABirthday(false);
	assertEquals("", testWithInitialAppointment.getMessage());
    }

}
