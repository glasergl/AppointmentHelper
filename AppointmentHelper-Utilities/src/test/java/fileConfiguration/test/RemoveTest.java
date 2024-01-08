package fileConfiguration.test;

import static appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_0;
import static appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_1;
import static appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_2;
import static appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_3;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static test.GeneralTestElements.BASE_TEST_RESOURCE_PATH;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import appointment.Appointment;
import fileConfiguration.AppointmentsConfigurationHandler;
import test.GeneralTestElements;

/**
 * Test whether removing appointments from a configuration works.
 * 
 * @author Gabriel Glaser
 */
public final class RemoveTest {
	/**
	 * Tests whether removing an appointment at a specific index works.
	 */
	@Test
	public void testRemoveOne() {
		final String path = BASE_TEST_RESOURCE_PATH + "testRemoveOne-Configuration.json";
		final AppointmentsConfigurationHandler handler = new AppointmentsConfigurationHandler(path);
		handler.updateAppointments(
				Arrays.asList(TEST_APPOINTMENT_0, TEST_APPOINTMENT_1, TEST_APPOINTMENT_2, TEST_APPOINTMENT_3));
		handler.delete(1);
		final List<Appointment> appointments = handler.getAppointments();
		assertFalse(appointments.contains(TEST_APPOINTMENT_1));
		assertEquals(3, appointments.size());
		GeneralTestElements.deleteTestFileRepresentedBy(handler);
	}
}
