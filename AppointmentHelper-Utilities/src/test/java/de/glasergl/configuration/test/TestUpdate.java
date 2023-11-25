package de.glasergl.configuration.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_1;
import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;

import de.glasergl.appointment.Appointment;
import de.glasergl.configuration.AppointmentsConfigurationHandler;
import de.glasergl.configuration.ConfigurationHandler;
import de.glasergl.configuration.AppointmentJSONTransformer;
import static de.glasergl.configuration.test.PathToTestResources.BASE_TEST_RESOURCE_PATH;

/**
 * Tests the update function of AppointmentsConfigurationHandler.
 * 
 * @author Gabriel Glaser
 * @version 30.06.2022
 */
public class TestUpdate {

	/**
	 * Tests whether updating an existing configuration works.
	 */
	@Test
	public void testWithEmptyConfiguration() {
		final String path = BASE_TEST_RESOURCE_PATH + "testWithEmptyConfiguration-Configuration.json";
		final ConfigurationHandler configurationHandler = new ConfigurationHandler(path);
		final AppointmentsConfigurationHandler appointmentConfigurationHandler = configurationHandler
				.getAppointmentsHandler();
		appointmentConfigurationHandler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_1, TEST_APPOINTMENT_2));
		assertEquals(TEST_APPOINTMENT_1, appointmentConfigurationHandler.getAppointment(0));
		assertEquals(TEST_APPOINTMENT_2, appointmentConfigurationHandler.getAppointment(1));
		assertEquals(2, appointmentConfigurationHandler.getSize());
		try {
			Files.delete(Paths.get(path));
		} catch (final IOException e) {
			fail("Couldn't delete test file.");
		}
	}

	/**
	 * Tests whether updating a configuration with existing appointments works.
	 */
	@Test
	public void testWithExistingAppointments() {
		final String path = BASE_TEST_RESOURCE_PATH + "testWithExistingAppointments-Configuration.json";
		createExistingTestConfiguration(path, TEST_APPOINTMENT_1, TEST_APPOINTMENT_2);
		final ConfigurationHandler configurationHandler = new ConfigurationHandler(path);
		final AppointmentsConfigurationHandler appointmentsConfigurationHandler = configurationHandler
				.getAppointmentsHandler();
		appointmentsConfigurationHandler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_2));
		assertEquals(TEST_APPOINTMENT_2, appointmentsConfigurationHandler.getAppointment(0));
		assertEquals(1, appointmentsConfigurationHandler.getSize());
		try {
			Files.delete(Paths.get(path));
		} catch (IOException e) {
			fail("Couldn't delete test file");
		}
	}

	/**
	 * Creates a valid configuration file at the given path and adds the given
	 * appointments to it.
	 * 
	 * @param path
	 * @param appointments
	 */
	private static void createExistingTestConfiguration(final String path, final Appointment... appointments) {
		final File testConfigurationFile = ConfigurationHandler.createConfigurationFile(path);
		try {
			final InputStreamReader reader = new InputStreamReader(new FileInputStream(testConfigurationFile));
			final JSONObject configuration = new JSONObject(new JSONTokener(reader));
			final JSONArray appointmentsAsJSON = configuration.getJSONArray(AppointmentsConfigurationHandler.JSON_KEY);
			for (final Appointment appointment : appointments) {
				appointmentsAsJSON.put(AppointmentJSONTransformer.appointmentToJSON(appointment));
			}
			reader.close();
			final OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(testConfigurationFile));
			configuration.write(writer);
			writer.close();
		} catch (final IOException e) {
			fail("Couldn't access from test file.");
		}
	}

}
