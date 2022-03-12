package ui.test.appointmentField;

import static org.junit.Assert.*;
import org.junit.Test;
import appointment.Appointment;
import appointment.InvalidAppointmentException;
import ui.appointmentField.AppointmentField;

/**
 * @author Gabriel Glaser
 * @version 12.03.2022
 */
public class TestAppointmentField {

    @Test
    public void testRepresentsValidAppointmentWithInitialAppointment() {
	AppointmentField appointmentField = new AppointmentField(Tests.testAppointment1);
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
	    AppointmentField appointmentField = new AppointmentField(Tests.testAppointment1);
	    Appointment result = appointmentField.getAppointment();
	    assertEquals(Tests.testAppointment1, result);
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

    @Test
    public void testErrorWithGetAppointmentWithInitialAppointment() throws InvalidAppointmentException {
	AppointmentField appointmentField = new AppointmentField(Tests.testAppointment1);
	appointmentField.setName("");
	assertThrows(InvalidAppointmentException.class, () -> {
	    appointmentField.getAppointment();
	});
    }

    @Test
    public void testErrorWithGetAppointmentWithoutInitialAppointment() throws InvalidAppointmentException {
	AppointmentField appointmentField = new AppointmentField();
	assertThrows(InvalidAppointmentException.class, () -> {
	    appointmentField.getAppointment();
	});
    }

}
