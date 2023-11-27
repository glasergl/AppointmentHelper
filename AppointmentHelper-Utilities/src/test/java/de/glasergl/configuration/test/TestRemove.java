package de.glasergl.configuration.test;

import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_0;
import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_1;
import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_2;
import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import de.glasergl.appointment.Appointment;
import de.glasergl.configuration.AppointmentsConfigurationHandler;
import de.glasergl.configuration.ConfigurationHandler;
import static de.glasergl.configuration.test.PathToTestResources.BASE_TEST_RESOURCE_PATH;

/**
 * Test whether removing appointments from a configuration works.
 * 
 * @author Gabriel Glaser
 * @version 30.06.2022
 */
public final class TestRemove {

	/**
	 * Tests whether removing one works properly and the resulting appointments
	 * don't change their order.
	 */
	@Test
	public void testRemoveOne() {
		final String path = BASE_TEST_RESOURCE_PATH + "testRemoveOne-Configuration.json";
		final ConfigurationHandler configurationHandler = new ConfigurationHandler(path);
		final AppointmentsConfigurationHandler appointmentsConfigurationHandler = configurationHandler
				.getAppointmentsHandler();
		appointmentsConfigurationHandler.updateAppointments(
				Arrays.asList(TEST_APPOINTMENT_0, TEST_APPOINTMENT_1, TEST_APPOINTMENT_2, TEST_APPOINTMENT_3));
		appointmentsConfigurationHandler.delete(1);
		final List<Appointment> appointments = appointmentsConfigurationHandler.getAppointments();
		assertFalse(appointments.contains(TEST_APPOINTMENT_1));
		assertEquals(3, appointments.size());
		try {
			Files.delete(Paths.get(path));
		} catch (final IOException e) {
			fail("Couldn't delete test file.");
		}
	}

}
