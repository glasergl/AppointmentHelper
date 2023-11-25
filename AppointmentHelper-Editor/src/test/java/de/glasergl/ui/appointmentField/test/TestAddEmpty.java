package de.glasergl.ui.appointmentField.test;

import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_1;
import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_2;
import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_3;
import static de.glasergl.standard.util.tests.GeneralTestUtilities.BASE_TEST_RESOURCE_PATH;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.Test;

import de.glasergl.configuration.AppointmentsConfigurationHandler;
import de.glasergl.configuration.ConfigurationHandler;
import de.glasergl.ui.appointmentField.AppointmentFieldController;
import de.glasergl.ui.appointmentField.AppointmentFieldControllerList;

/**
 * Tests the add empty method of AppointmentFieldControllerList.
 * 
 * @author Gabriel Glaser
 * @version 01.07.2022
 */
public final class TestAddEmpty {

    /**
     * Tests whether the AppointmentFieldControllerList updates its state correctly
     * after adding an empty appointment field to an empty configuration.
     */
    @Test
    public void testAddEmptyOnEmptyConfiguration() {
	final String path = BASE_TEST_RESOURCE_PATH + "testAddEmptyOnEmptyConfiguration-Configuration.json";
	final ConfigurationHandler configurationHandler = new ConfigurationHandler(path);
	final AppointmentFieldControllerList appointmentFieldList = new AppointmentFieldControllerList(configurationHandler);
	appointmentFieldList.addEmptyAppointmentField();

	assertEquals(1, appointmentFieldList.getNumberOfShownAppointments());
	assertEquals(0, appointmentFieldList.getNumberOfAppointmentsInConfiguration());

	try {
	    Files.delete(Paths.get(path));
	} catch (final IOException e) {
	    fail("Couldn't delete test-file");
	}
    }

    /**
     * Tests whether the AppointmentFieldControllerList updates its state correctly
     * after adding an empty appointment field to an existing configuration.
     * 
     */
    @Test
    public void testAddEmptyOnNonEmptyConfiguration() {
	final String path = BASE_TEST_RESOURCE_PATH + "testAddEmptyOnNonEmptyConfiguration-Configuration.json";
	final ConfigurationHandler configurationHandler = new ConfigurationHandler(path);
	final AppointmentsConfigurationHandler appointmentsConfigurationHandler = configurationHandler.getAppointmentsHandler();
	appointmentsConfigurationHandler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_1, TEST_APPOINTMENT_2, TEST_APPOINTMENT_3));
	final AppointmentFieldControllerList appointmentFieldList = new AppointmentFieldControllerList(configurationHandler);
	appointmentFieldList.addEmptyAppointmentField();

	assertEquals(4, appointmentFieldList.getNumberOfShownAppointments());
	assertEquals(3, appointmentFieldList.getNumberOfAppointmentsInConfiguration());

	try {
	    Files.delete(Paths.get(path));
	} catch (final IOException e) {
	    fail("Couldn't delete test-file");
	}
    }

    /**
     * Tests whether the indices of the appointment input fields are adapted
     * correctly after adding a new input field.
     */
    @Test
    public void testIndicesAfterAddEmpty() {
	final String path = BASE_TEST_RESOURCE_PATH + "testAddEmptyOnNonEmptyConfiguration-Configuration.json";
	final ConfigurationHandler configurationHandler = new ConfigurationHandler(path);
	final AppointmentsConfigurationHandler appointmentsConfigurationHandler = configurationHandler.getAppointmentsHandler();
	appointmentsConfigurationHandler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_1, TEST_APPOINTMENT_2));
	final AppointmentFieldControllerList appointmentFieldList = new AppointmentFieldControllerList(configurationHandler);
	final AppointmentFieldController appointmentFieldController0 = appointmentFieldList.getAppointmentField(0);
	final AppointmentFieldController appointmentFieldController1 = appointmentFieldList.getAppointmentField(1);
	appointmentFieldList.addEmptyAppointmentField();
	final AppointmentFieldController appointmentFieldController2 = appointmentFieldList.getAppointmentField(2);

	assertEquals(0, appointmentFieldController0.getCurrentIndex());
	assertEquals(1, appointmentFieldController1.getCurrentIndex());
	assertEquals(2, appointmentFieldController2.getCurrentIndex());
    }
}
