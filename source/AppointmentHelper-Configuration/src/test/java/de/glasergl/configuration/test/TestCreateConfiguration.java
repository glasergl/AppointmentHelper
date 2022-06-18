package de.glasergl.configuration.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import de.glasergl.configuration.ConfigurationHandler;

/**
 * Tests whether creating a new configuration file is possible.
 * 
 * @author Gabriel Glaser
 * @version 18.06.2022
 */
public class TestCreateConfiguration {

    @Test
    public void testCreateNewConfigurationFile() {
	String path = "src\\test\\resources\\emptyAppointmentTestFile.json";
	File emptyAppointmentFile = new File(path);
	assertFalse(emptyAppointmentFile.exists());
	ConfigurationHandler.createNewConfigurationFile(path);
	assertTrue(emptyAppointmentFile.exists());
	emptyAppointmentFile.delete();
    }

    @Test
    public void testCreateNewConfigurationFileWhichAlreadyExists() {
	String path = "src\\test\\resources\\emptyAppointmentTestFile.json";
	File emptyAppointmentFile = new File(path);
	try {
	    emptyAppointmentFile.createNewFile();
	} catch (IOException e) {
	    fail("");
	}
	assertThrows(IllegalArgumentException.class, () -> {
	    ConfigurationHandler.createNewConfigurationFile(path);
	});
	emptyAppointmentFile.delete();
    }

}
