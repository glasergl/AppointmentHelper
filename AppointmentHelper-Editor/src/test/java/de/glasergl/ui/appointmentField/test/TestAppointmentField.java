package de.glasergl.ui.appointmentField.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_1;

import org.junit.Test;

import de.glasergl.appointment.Appointment;
import de.glasergl.appointment.InvalidAppointmentException;
import de.glasergl.ui.appointmentField.AppointmentField;

/**
 * @author Gabriel Glaser
 * @version 12.03.2022
 */
public class TestAppointmentField {

    @Test
    public void testRepresentsValidAppointmentWithInitialAppointment() {
	AppointmentField appointmentField = new AppointmentField(TEST_APPOINTMENT_1);
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
	    AppointmentField appointmentField = new AppointmentField(TEST_APPOINTMENT_1);
	    Appointment result = appointmentField.getAppointment();
	    assertEquals(TEST_APPOINTMENT_1, result);
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
	AppointmentField appointmentField = new AppointmentField(TEST_APPOINTMENT_1);
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
