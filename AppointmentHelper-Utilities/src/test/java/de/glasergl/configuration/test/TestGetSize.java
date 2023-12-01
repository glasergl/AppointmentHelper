package de.glasergl.configuration.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_0;
import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.Test;

import de.glasergl.configuration.AppointmentsConfigurationHandler;
import de.glasergl.configuration.ConfigurationHandler;
import static de.glasergl.configuration.test.PathToTestResources.BASE_TEST_RESOURCE_PATH;

/**
 * Tests whether getSize returns the correct size.
 * 
 * @author Gabriel Glaser
 */
public final class TestGetSize {

	/**
	 * Tests whether the size of an empty configuration is zero.
	 */
	@Test
	public void testGetSizeOnEmptyConfiguration() {
		final String path = BASE_TEST_RESOURCE_PATH + "testGetSizeOnEmptyConfiguration-Configuration.json";
		final ConfigurationHandler configurationHandler = new ConfigurationHandler(path);
		final AppointmentsConfigurationHandler appointmentsConfigurationHandler = configurationHandler
				.getAppointmentsHandler();
		assertEquals(0, appointmentsConfigurationHandler.getSize());
		try {
			Files.delete(Paths.get(path));
		} catch (final IOException e) {
			fail("Couldn't delete test file.");
		}
	}

	/**
	 * Tests whether the size is correctly adapted when new appointments are added.
	 */
	@Test
	public void testGetSizeWithUpdatedConfiguration() {
		final String path = BASE_TEST_RESOURCE_PATH + "testGetSizeWithUpdatedConfiguration-Configuration.json";
		final ConfigurationHandler configurationHandler = new ConfigurationHandler(path);
		final AppointmentsConfigurationHandler appointmentsConfigurationHandler = configurationHandler
				.getAppointmentsHandler();
		appointmentsConfigurationHandler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_0, TEST_APPOINTMENT_4));
		assertEquals(2, appointmentsConfigurationHandler.getSize());
		try {
			Files.delete(Paths.get(path));
		} catch (final IOException e) {
			fail("Couldn't delete test file.");
		}
	}

}
