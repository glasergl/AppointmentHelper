package de.glasergl.ui.appointmentField.test;

import static de.glasergl.standard.util.tests.GeneralTestUtilities.BASE_TEST_RESOURCE_PATH;
import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_1;
import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_2;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.Test;

import de.glasergl.configuration.AppointmentsConfigurationHandler;
import de.glasergl.configuration.ConfigurationHandler;
import de.glasergl.ui.appointmentField.AppointmentFieldControllerList;

/**
 * Tests common things about the TestAppointmentFieldControllerList.
 * 
 * @author Gabriel Glaser
 * @version 01.07.2022
 */
public final class TestAppointmentFieldControllerList {

    /**
     * Tests whether the instance initializes itself correctly with an empty
     * configuration.
     */
    @Test
    public void testInitialStateWithEmptyConfiguration() {
	final String path = BASE_TEST_RESOURCE_PATH + "testInitialStateWithEmptyConfiguration-Configuration.json";
	final ConfigurationHandler configurationHandler = new ConfigurationHandler(path);
	final AppointmentFieldControllerList appointmentFieldList = new AppointmentFieldControllerList(configurationHandler);
	
	assertEquals(0, appointmentFieldList.getNumberOfDeletedAppointments());
	assertEquals(0, appointmentFieldList.getNumberOfShownAppointments());
	assertEquals(0, appointmentFieldList.getNumberOfAppointmentsInConfiguration());
	
	try {
	    Files.delete(Paths.get(path));
	} catch (final IOException e) {
	    fail("Couldn't delete test-file");
	}
    }

    /**
     * Tests whether the instance initializes itself correctly with a non-empty
     * configuration.
     */
    @Test
    public void testInitialStateWithNonEmpyConfiguration() {
	final String path = BASE_TEST_RESOURCE_PATH + "testInitialStateWithNonEmpyConfiguration-Configuration.json";
	final ConfigurationHandler configurationHandler = new ConfigurationHandler(path);
	final AppointmentsConfigurationHandler appointmentsConfigurationHandler = configurationHandler.getAppointmentsHandler();
	appointmentsConfigurationHandler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_1, TEST_APPOINTMENT_2));
	final AppointmentFieldControllerList appointmentFieldList = new AppointmentFieldControllerList(configurationHandler);
	
	assertEquals(0, appointmentFieldList.getNumberOfDeletedAppointments());
	assertEquals(2, appointmentFieldList.getNumberOfShownAppointments());
	assertEquals(2, appointmentFieldList.getNumberOfAppointmentsInConfiguration());
	
	try {
	    Files.delete(Paths.get(path));
	} catch (final IOException e) {
	    fail("Couldn't delete test-file");
	}
    }

}
