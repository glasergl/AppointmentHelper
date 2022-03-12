package ui.appointmentField.test.appointmentFieldController;

import static org.junit.Assert.*;
import org.junit.Test;
import appointment.Appointment;
import appointment.InvalidAppointmentException;
import date.SimpleDate;
import date.SimpleDates;
import ui.appointmentField.AllAppointmentFields;
import ui.appointmentField.AppointmentFieldController;

/**
 * @author Gabriel Glaser
 * @version 08.02.2022
 */
public class TestCancel {

    static AllAppointmentFields allAppointments = new AllAppointmentFields();

    static SimpleDate testDate = new SimpleDate(10, 10);
    static String testName = "Tim";
    static String testDescription = "Hello There";
    static boolean testIsBirthday = false;
    static Appointment testAppointment = new Appointment(testDate, testName, testDescription, testIsBirthday);

    @Test
    public void testCancelWithInitialAppointment() {
	try {
	    AppointmentFieldController appointmentFieldController = new AppointmentFieldController(allAppointments, testAppointment);
	    appointmentFieldController.setDate(new SimpleDate(21, 2));
	    appointmentFieldController.setName("Luca");
	    appointmentFieldController.setDescription("I need a cupcake");
	    appointmentFieldController.setIsBirthday(!testIsBirthday);
	    assertNotEquals(testAppointment, appointmentFieldController.getInputAppointment());
	    appointmentFieldController.cancel();
	    assertEquals(testAppointment, appointmentFieldController.getInputAppointment());
	} catch (InvalidAppointmentException e) {
	    e.printStackTrace();
	    fail("Couldn't get Appointment but should've been able to");
	}
    }

    @Test
    public void testCancelWithoutInitialAppointment() {
	AppointmentFieldController appointmentFieldController = new AppointmentFieldController(allAppointments);
	appointmentFieldController.setDate(new SimpleDate(21, 2));
	appointmentFieldController.setName("Luca");
	appointmentFieldController.setDescription("I need a cupcake");
	appointmentFieldController.setIsBirthday(!testIsBirthday);
	appointmentFieldController.cancel();
	assertEquals(SimpleDates.getToday(), appointmentFieldController.getDate());
	assertEquals("", appointmentFieldController.getName());
	assertEquals("", appointmentFieldController.getDescription());
	assertEquals(true, appointmentFieldController.isBirthday());
    }

}
