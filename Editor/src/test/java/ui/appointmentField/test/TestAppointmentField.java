package ui.appointmentField.test;

import static org.junit.Assert.*;
import org.junit.Test;
import appointment.Appointment;
import appointment.InvalidAppointmentException;
import date.SimpleDate;
import ui.appointmentField.AppointmentField;

/**
 * @author Gabriel Glaser
 * @version 08.02.2022
 */
public class TestAppointmentField {

    static SimpleDate testDate = new SimpleDate(10, 5);
    static String testName = "Fred";
    static String testDescription = "ball";
    static boolean testIsBirthday = false;
    static Appointment testAppointment = new Appointment(testDate, testName, testDescription, testIsBirthday);

    @Test
    public void testRepresentsValidAppointmentWithInitialAppointment() {
	AppointmentField appointmentField = new AppointmentField(testAppointment);
	assertTrue(appointmentField.representsValidAppointment());
	appointmentField.setName("");
	assertFalse(appointmentField.representsValidAppointment());
    }

    @Test
    public void testRepresentsValidAppointmentWithoutInitialAppointment() {
	AppointmentField appointmentField = new AppointmentField();
	assertFalse(appointmentField.representsValidAppointment());
    }

    @Test
    public void testGetAppointmentWithInitialAppointment() {
	try {
	    AppointmentField appointmentField = new AppointmentField(testAppointment);
	    Appointment result = appointmentField.getAppointment();
	    assertEquals(testAppointment, result);
	} catch (InvalidAppointmentException e) {
	    e.printStackTrace();
	    fail("Couldn't retrieve Appointment from AppointmentField, but should have been able to");
	}
    }

    @Test
    public void testGetAppointmentWithoutInitialAppointment() {
	try {
	    AppointmentField appointmentField = new AppointmentField();
	    appointmentField.setName("TestName");
	    appointmentField.getAppointment();
	} catch (InvalidAppointmentException e) {
	    e.printStackTrace();
	    fail("Couldn't retrieve Appointment from AppointmentField, but should have been able to");
	}
    }

    @Test(expected = InvalidAppointmentException.class)
    public void testErrorWithGetAppointmentWithInitialAppointment() throws InvalidAppointmentException {
	AppointmentField appointmentField = new AppointmentField(testAppointment);
	appointmentField.setName("");
	appointmentField.getAppointment();
    }

    @Test(expected = InvalidAppointmentException.class)
    public void testErrorWithGetAppointmentWithoutInitialAppointment() throws InvalidAppointmentException {
	AppointmentField appointmentField = new AppointmentField();
	appointmentField.getAppointment();
    }

}
