package editorUI.appointmentField.test;

import static appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_1;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import appointment.Appointment;
import appointment.InvalidAppointmentException;
import editorUI.appointmentField.AppointmentField;

/**
 * @author Gabriel Glaser
 */
public class AppointmentFieldTest {
	@Test
	public void testRepresentsValidAppointmentWithInitialAppointment() {
		final AppointmentField appointmentField = new AppointmentField(TEST_APPOINTMENT_1);
		assertTrue(appointmentField.representsValidAppointment());
		appointmentField.setAppointmentName("");
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
			appointmentField.setAppointmentName("TestName");
			appointmentField.getAppointment();
		} catch (InvalidAppointmentException e) {
			e.printStackTrace();
			fail("Couldn't retrieve Appointment from AppointmentField, but should have been able to");
		}
	}

	@Test
	public void testErrorWithGetAppointmentWithInitialAppointment() throws InvalidAppointmentException {
		AppointmentField appointmentField = new AppointmentField(TEST_APPOINTMENT_1);
		appointmentField.setAppointmentName("");
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
