package de.glasergl.ui.appointmentField.test;

import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_1;
import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_2;
import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_3;
import static de.glasergl.standard.util.tests.GeneralTestUtilities.BASE_TEST_RESOURCE_PATH;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import de.glasergl.appointment.Appointment;
import de.glasergl.configuration.AppointmentsConfigurationHandler;
import de.glasergl.configuration.ConfigurationHandler;
import de.glasergl.ui.appointmentField.AppointmentFieldController;
import de.glasergl.ui.appointmentField.AppointmentFieldControllerList;

/**
 * Tests the getInput method of AppointmentFieldControllerList.
 * 
 * @author Gabriel Glaser
 * @version 01.07.2022
 */
public final class TestGetInput {

    /**
     * Tests whether the resulting input list is empty on an empty configuration.
     */
    @Test
    public void testInitialGetInputOnEmptyConfiguration() {
	final String path = BASE_TEST_RESOURCE_PATH + "testInitialGetInputOnEmptyConfiguration-Configuration.json";
	final ConfigurationHandler configurationHandler = new ConfigurationHandler(path);
	final AppointmentFieldControllerList appointmentFieldList = new AppointmentFieldControllerList(configurationHandler);
	final List<Appointment> input = appointmentFieldList.getInput();

	assertTrue(input.isEmpty());

	try {
	    Files.delete(Paths.get(path));
	} catch (final IOException e) {
	    fail("Couldn't delete test-file");
	}
    }

    /**
     * Tests whether the the appointments in the configuration are contained and in
     * the correct order in the input list.
     */
    @Test
    public void testInitialGetInputOnNonEmptyConfiguration() {
	final String path = BASE_TEST_RESOURCE_PATH + "testInitialGetInputOnNonEmptyConfiguration-Configuration.json";
	final ConfigurationHandler configurationHandler = new ConfigurationHandler(path);
	final AppointmentsConfigurationHandler appointmentsConfigurationHandler = configurationHandler.getAppointmentsHandler();
	appointmentsConfigurationHandler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_1, TEST_APPOINTMENT_2, TEST_APPOINTMENT_3));
	final AppointmentFieldControllerList appointmentFieldList = new AppointmentFieldControllerList(configurationHandler);
	final List<Appointment> input = appointmentFieldList.getInput();

	assertEquals(TEST_APPOINTMENT_1, input.get(0));
	assertEquals(TEST_APPOINTMENT_2, input.get(1));
	assertEquals(TEST_APPOINTMENT_3, input.get(2));
	assertEquals(3, input.size());

	try {
	    Files.delete(Paths.get(path));
	} catch (final IOException e) {
	    fail("Couldn't delete test-file");
	}
    }

    /**
     * Tests whether adding a new appointment field adapts the resulting input list
     * accordingly.
     */
    @Test
    public void testGetInputOnUpdatedInputWithAdd() {
	final String path = BASE_TEST_RESOURCE_PATH + "testGetInputOnUpdatedInputWithAdd-Configuration.json";
	final ConfigurationHandler configurationHandler = new ConfigurationHandler(path);
	final AppointmentsConfigurationHandler appointmentsConfigurationHandler = configurationHandler.getAppointmentsHandler();
	appointmentsConfigurationHandler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_2));
	final AppointmentFieldControllerList appointmentFieldList = new AppointmentFieldControllerList(configurationHandler);
	appointmentFieldList.addEmptyAppointmentField();
	final AppointmentFieldController appointmentField = appointmentFieldList.getAppointmentField(1);
	appointmentField.setInputAppointment(TEST_APPOINTMENT_3);
	final List<Appointment> input = appointmentFieldList.getInput();

	assertEquals(TEST_APPOINTMENT_2, input.get(0));
	assertEquals(TEST_APPOINTMENT_3, input.get(1));
	assertEquals(2, input.size());

	try {
	    Files.delete(Paths.get(path));
	} catch (final IOException e) {
	    fail("Couldn't delete test-file");
	}
    }

    /**
     * Tests whether removing an appointment field adapts the resulting input list
     * accordingly.
     */
    @Test
    public void testGetInputOnUpdatedInputWithRemove() {
	final String path = BASE_TEST_RESOURCE_PATH + "testGetInputOnUpdatedInputWithRemove-Configuration.json";
	final ConfigurationHandler configurationHandler = new ConfigurationHandler(path);
	final AppointmentsConfigurationHandler appointmentsConfigurationHandler = configurationHandler.getAppointmentsHandler();
	appointmentsConfigurationHandler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_2, TEST_APPOINTMENT_1));
	final AppointmentFieldControllerList appointmentFieldList = new AppointmentFieldControllerList(configurationHandler);
	appointmentFieldList.delete(0);
	final List<Appointment> input = appointmentFieldList.getInput();

	assertEquals(TEST_APPOINTMENT_1, input.get(0));
	assertEquals(1, input.size());

	try {
	    Files.delete(Paths.get(path));
	} catch (final IOException e) {
	    fail("Couldn't delete test-file");
	}
    }

    /**
     * Tests whether restoring a previously deleted appointment field adapts the
     * resulting input list accordingly.
     */
    @Test
    public void testGetInputOnUpdatedInputWithRestoreLastDeleted() {
	final String path = BASE_TEST_RESOURCE_PATH + "testGetInputOnUpdatedInputWithRestoreLastDeleted-Configuration.json";
	final ConfigurationHandler configurationHandler = new ConfigurationHandler(path);
	final AppointmentsConfigurationHandler appointmentsConfigurationHandler = configurationHandler.getAppointmentsHandler();
	appointmentsConfigurationHandler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_2, TEST_APPOINTMENT_1, TEST_APPOINTMENT_3));
	final AppointmentFieldControllerList appointmentFieldList = new AppointmentFieldControllerList(configurationHandler);
	appointmentFieldList.delete(1);
	appointmentFieldList.restoreLastDeleted();
	final List<Appointment> input = appointmentFieldList.getInput();

	assertEquals(TEST_APPOINTMENT_2, input.get(0));
	assertEquals(TEST_APPOINTMENT_3, input.get(1));
	assertEquals(TEST_APPOINTMENT_1, input.get(2));
	assertEquals(3, input.size());

	try {
	    Files.delete(Paths.get(path));
	} catch (final IOException e) {
	    fail("Couldn't delete test-file");
	}
    }

}
