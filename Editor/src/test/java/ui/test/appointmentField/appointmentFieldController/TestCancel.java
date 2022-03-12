package ui.test.appointmentField.appointmentFieldController;

import static org.junit.Assert.*;
import org.junit.Test;
import appointment.InvalidAppointmentException;
import date.SimpleDate;
import date.SimpleDates;
import ui.appointmentField.AllAppointmentFields;
import ui.appointmentField.AppointmentFieldController;
import ui.test.appointmentField.Tests;

/**
 * @author Gabriel Glaser
 * @version 08.02.2022
 */
public class TestCancel {

    static AllAppointmentFields allAppointmentFieldsStub = new AllAppointmentFields();

    @Test
    public void testCancelWithInitialAppointment() {
	try {
	    AppointmentFieldController appointmentFieldController = new AppointmentFieldController(allAppointmentFieldsStub, Tests.testAppointment1);
	    appointmentFieldController.setDate(new SimpleDate(21, 2));
	    appointmentFieldController.setName("Luca");
	    appointmentFieldController.setDescription("I need a cupcake");
	    appointmentFieldController.setIsBirthday(!Tests.testAppointment1.isBirthday());
	    assertNotEquals(Tests.testAppointment1, appointmentFieldController.getInputAppointment());
	    appointmentFieldController.cancel();
	    assertEquals(Tests.testAppointment1, appointmentFieldController.getInputAppointment());
	} catch (InvalidAppointmentException e) {
	    e.printStackTrace();
	    fail("Couldn't get Appointment but should've been able to");
	}
    }

    @Test
    public void testCancelWithoutInitialAppointment() {
	AppointmentFieldController appointmentFieldController = new AppointmentFieldController(allAppointmentFieldsStub);
	appointmentFieldController.setDate(new SimpleDate(21, 2));
	appointmentFieldController.setName("Luca");
	appointmentFieldController.setDescription("I need a cupcake");
	appointmentFieldController.setIsBirthday(!Tests.testAppointment1.isBirthday());
	appointmentFieldController.cancel();
	assertEquals(SimpleDates.getToday(), appointmentFieldController.getDate());
	assertEquals("", appointmentFieldController.getName());
	assertEquals("", appointmentFieldController.getDescription());
	assertEquals(true, appointmentFieldController.isBirthday());
    }

}
