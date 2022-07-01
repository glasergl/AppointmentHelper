package de.glasergl.configuration.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static de.glasergl.standard.util.tests.GeneralTestUtilities.BASE_TEST_RESOURCE_PATH;

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

import de.glasergl.configuration.AppointmentsConfigurationHandler;
import de.glasergl.configuration.ConfigurationHandler;

/**
 * Tests whether creating a valid new configuration file is possible.
 * 
 * @author Gabriel Glaser
 * @version 30.06.2022
 */
public class TestCreateConfigurationFile {

    /**
     * Tests whether creating a configuration at a path where no file is, works.
     */
    @Test
    public void testExistanceAfterCreateConfigurationFile() {
	final String path = BASE_TEST_RESOURCE_PATH + "testExistanceAfterCreateConfigurationFile-Configuration.json";
	assertFalse(Files.exists(Paths.get(path)));
	final File emptyConfigurationFile = ConfigurationHandler.createConfigurationFile(path);
	assertTrue(Files.exists(Paths.get(path)));
	emptyConfigurationFile.delete();
    }

    /**
     * Tests whether the created configuration contains the correct initial keys.
     */
    @Test
    public void testConfigurationAfterCreateConfigurationFile() {
	final File emptyConfigurationFile = ConfigurationHandler.createConfigurationFile(BASE_TEST_RESOURCE_PATH + "testConfigurationAfterCreateConfigurationFile-Configuration.json");
	try (final InputStreamReader reader = new InputStreamReader(new FileInputStream(emptyConfigurationFile))) {
	    final JSONObject configuration = new JSONObject(new JSONTokener(reader));
	    validateInitialConfiguration(configuration);
	} catch (final IOException e) {
	    fail("Couldn't read the test configuration file");
	}
	emptyConfigurationFile.delete();
    }

    /**
     * Tests whether trying to create a configuration on a file which already exists
     * throws an exception.
     */
    @Test
    public void testCreateConfigurationFileWhichAlreadyExists() {
	final String path = BASE_TEST_RESOURCE_PATH + "testCreateConfigurationFileWhichAlreadyExists-Configuration.json";
	final File file = new File(path);
	try {
	    file.createNewFile();
	} catch (IOException e) {
	    fail("Couldn't create empty configuration file.");
	}
	assertThrows(IllegalArgumentException.class, () -> {
	    ConfigurationHandler.createConfigurationFile(path);
	});
	file.delete();
    }

    /**
     * Tests whether the given configuration is a valid initial configuration.
     * 
     * @param initialConfiguration
     */
    private void validateInitialConfiguration(final JSONObject initialConfiguration) {
	assertTrue(initialConfiguration.has(AppointmentsConfigurationHandler.JSON_KEY));
	final Set<String> keys = initialConfiguration.keySet();
	assertEquals(1, keys.size());
	final JSONArray appointments = initialConfiguration.getJSONArray(AppointmentsConfigurationHandler.JSON_KEY);
	assertTrue(appointments.isEmpty());
    }

}
