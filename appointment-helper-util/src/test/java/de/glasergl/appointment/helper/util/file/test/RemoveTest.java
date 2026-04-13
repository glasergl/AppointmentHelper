package de.glasergl.appointment.helper.util.file.test;

import static de.glasergl.appointment.helper.util.entity.ExampleAppointmentFactory.TEST_APPOINTMENT_0;
import static de.glasergl.appointment.helper.util.entity.ExampleAppointmentFactory.TEST_APPOINTMENT_1;
import static de.glasergl.appointment.helper.util.entity.ExampleAppointmentFactory.TEST_APPOINTMENT_2;
import static de.glasergl.appointment.helper.util.entity.ExampleAppointmentFactory.TEST_APPOINTMENT_3;
import static de.glasergl.appointment.helper.util.test.GeneralTestElements.BASE_TEST_RESOURCE_PATH;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import de.glasergl.appointment.helper.util.entity.Appointment;
import de.glasergl.appointment.helper.util.file.AppointmentsConfigurationHandler;
import de.glasergl.appointment.helper.util.test.GeneralTestElements;

/**
 * Test whether removing appointments from a configuration works.
 * 
 * @author glasergl
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
