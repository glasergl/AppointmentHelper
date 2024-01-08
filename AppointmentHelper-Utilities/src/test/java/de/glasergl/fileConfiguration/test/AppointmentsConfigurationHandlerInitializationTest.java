package de.glasergl.fileConfiguration.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static test.GeneralTestElements.BASE_TEST_RESOURCE_PATH;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;

import fileConfiguration.AppointmentsConfigurationHandler;

/**
 * Tests whether creating a valid new configuration file is possible.
 * 
 * @author Gabriel Glaser
 */
public class AppointmentsConfigurationHandlerInitializationTest {
	/**
	 * Tests whether creating a configuration at a path where no file is, works.
	 */
	@Test
	public void testExistanceAfterCreateConfigurationFile() {
		final String path = BASE_TEST_RESOURCE_PATH + "testExistanceAfterCreateConfigurationFile-Configuration.json";
		assertFalse(Files.exists(Paths.get(path)));
		final AppointmentsConfigurationHandler appointmentsConfigurationHandler = new AppointmentsConfigurationHandler(
				path);
		assertTrue(Files.exists(Paths.get(path)));
		final File testFile = appointmentsConfigurationHandler.getConfigurationFile();
		testFile.delete();
	}

	/**
	 * Tests whether the created configuration contains the correct initial keys.
	 */
	@Test
	public void testConfigurationAfterCreateConfigurationFile() {
		final String path = BASE_TEST_RESOURCE_PATH
				+ "testConfigurationAfterCreateConfigurationFile-Configuration.json";
		final AppointmentsConfigurationHandler appointmentsConfigurationHandler = new AppointmentsConfigurationHandler(
				path);
		final File emptyConfigurationFile = appointmentsConfigurationHandler.getConfigurationFile();
		try (final InputStreamReader reader = new InputStreamReader(new FileInputStream(emptyConfigurationFile))) {
			final JSONObject configuration = new JSONObject(new JSONTokener(reader));
			validateInitialConfiguration(configuration);
		} catch (final IOException e) {
			fail("Couldn't read the test configuration file");
		}
		emptyConfigurationFile.delete();
	}

	/**
	 * Tests whether the given configuration is a valid initial configuration.
	 * 
	 * @param initialConfiguration
	 */
	private void validateInitialConfiguration(final JSONObject initialConfiguration) {
		assertTrue(initialConfiguration.has(AppointmentsConfigurationHandler.APPOINTMENTS_JSON_KEY));
		final Set<String> keys = initialConfiguration.keySet();
		assertEquals(1, keys.size());
		final JSONArray appointments = initialConfiguration
				.getJSONArray(AppointmentsConfigurationHandler.APPOINTMENTS_JSON_KEY);
		assertTrue(appointments.isEmpty());
	}
}
