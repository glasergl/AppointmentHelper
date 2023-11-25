package de.glasergl.configuration.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_1;
import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_2;
import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_3;

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
 * Tests whether getting the appointments of a configuration via
 * AppointmentConfigurationHandler works.
 * 
 * @author Gabriel Glaser
 * @version 30.06.2022
 */
public final class TestGetAppointments {
	/**
	 * Tests whether getting the appointments on an empty configuration results in
	 * an empty list.
	 */
	@Test
	public void testWithEmptyConfiguration() {
		final String path = BASE_TEST_RESOURCE_PATH + "testWithEmptyConfiguration-Configuration.json";
		final ConfigurationHandler configurationHandler = new ConfigurationHandler(path);
		final AppointmentsConfigurationHandler appointmentsConfigurationHandler = configurationHandler
				.getAppointmentsHandler();
		final List<Appointment> appointments = appointmentsConfigurationHandler.getAppointments();
		assertTrue(appointments.isEmpty());
		try {
			Files.delete(Paths.get(path));
		} catch (final IOException e) {
			fail("Couldn't delete test file.");
		}
	}

	/**
	 * Tests whether all previously added appointments are retrievable and in the
	 * correct order.
	 */
	@Test
	public void testWithInitialAppointments() {
		final String path = BASE_TEST_RESOURCE_PATH + "testWithInitialAppointments-Configuration.json";
		final ConfigurationHandler configurationHandler = new ConfigurationHandler(path);
		final AppointmentsConfigurationHandler appointmentsConfigurationHandler = configurationHandler
				.getAppointmentsHandler();
		appointmentsConfigurationHandler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_2, TEST_APPOINTMENT_1));
		final List<Appointment> appointments = appointmentsConfigurationHandler.getAppointments();
		assertEquals(TEST_APPOINTMENT_2, appointments.get(0));
		assertEquals(TEST_APPOINTMENT_1, appointments.get(1));
		assertEquals(2, appointments.size());
		try {
			Files.delete(Paths.get(path));
		} catch (final IOException e) {
			fail("Couldn't delete test file.");
		}
	}

	/**
	 * Tests whether accessing a single appointment works.
	 */
	@Test
	public void testSingleAccessGetAppointment() {
		final String path = BASE_TEST_RESOURCE_PATH + "testSingleAccessGetAppointment-Configuration.json";
		final ConfigurationHandler configurationHandler = new ConfigurationHandler(path);
		final AppointmentsConfigurationHandler appointmentsConfigurationHandler = configurationHandler
				.getAppointmentsHandler();
		appointmentsConfigurationHandler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_2, TEST_APPOINTMENT_3));
		assertEquals(TEST_APPOINTMENT_2, appointmentsConfigurationHandler.getAppointment(0));
		assertEquals(TEST_APPOINTMENT_3, appointmentsConfigurationHandler.getAppointment(1));
		try {
			Files.delete(Paths.get(path));
		} catch (final IOException e) {
			fail("Couldn't delete test file.");
		}
	}

}
