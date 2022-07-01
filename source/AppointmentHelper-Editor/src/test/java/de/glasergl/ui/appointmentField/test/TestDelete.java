package de.glasergl.ui.appointmentField.test;

import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_0;
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
 * Tests the delete method of AppointmentFieldControllerList.
 * 
 * @author Gabriel Glaser
 * @version 01.07.2022
 */
public final class TestDelete {

    /**
     * Tests whether the state (number of appointments in the respective lists) are
     * updated correctly.
     */
    @Test
    public void testStateAfterDelete() {
	final String path = BASE_TEST_RESOURCE_PATH + "testStateAfterDelete-Configuration.json";
	final ConfigurationHandler configurationHandler = new ConfigurationHandler(path);
	final AppointmentsConfigurationHandler appointmentsConfigurationHandler = configurationHandler.getAppointmentsHandler();
	appointmentsConfigurationHandler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_1, TEST_APPOINTMENT_2, TEST_APPOINTMENT_3));
	final AppointmentFieldControllerList appointmentFieldList = new AppointmentFieldControllerList(configurationHandler);
	appointmentFieldList.delete(1);
	final AppointmentFieldController appointmentField1 = appointmentFieldList.getAppointmentField(0);
	final AppointmentFieldController appointmentField3 = appointmentFieldList.getAppointmentField(1);

	assertEquals(1, appointmentFieldList.getNumberOfDeletedAppointments());
	assertEquals(2, appointmentFieldList.getNumberOfShownAppointments());
	assertEquals(2, appointmentFieldList.getNumberOfAppointmentsInConfiguration());
	assertEquals(TEST_APPOINTMENT_1, appointmentField1.getAppointment());
	assertEquals(TEST_APPOINTMENT_3, appointmentField3.getAppointment());

	try {
	    Files.delete(Paths.get(path));
	} catch (final IOException e) {
	    fail("Couldn't delete test-file");
	}
    }

    /**
     * Tests whether the indices of the input fields are adapted correctly after one
     * is deleted.
     */
    @Test
    public void testIndicesAfterDelete() {
	final String path = BASE_TEST_RESOURCE_PATH + "testIndicesAfterDelete-Configuration.json";
	final ConfigurationHandler configurationHandler = new ConfigurationHandler(path);
	final AppointmentsConfigurationHandler appointmentsConfigurationHandler = configurationHandler.getAppointmentsHandler();
	appointmentsConfigurationHandler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_0, TEST_APPOINTMENT_1, TEST_APPOINTMENT_2, TEST_APPOINTMENT_3));
	final AppointmentFieldControllerList appointmentFieldList = new AppointmentFieldControllerList(configurationHandler);
	final AppointmentFieldController appointmentField0 = appointmentFieldList.getAppointmentField(0);
	final AppointmentFieldController appointmentField1 = appointmentFieldList.getAppointmentField(1);
	final AppointmentFieldController appointmentField2 = appointmentFieldList.getAppointmentField(2);
	final AppointmentFieldController appointmentField3 = appointmentFieldList.getAppointmentField(3);
	appointmentFieldList.delete(1);

	assertEquals(0, appointmentField0.getCurrentIndex());
	assertEquals(-1, appointmentField1.getCurrentIndex());
	assertEquals(1, appointmentField2.getCurrentIndex());
	assertEquals(2, appointmentField3.getCurrentIndex());

	try {
	    Files.delete(Paths.get(path));
	} catch (final IOException e) {
	    fail("Couldn't delete test-file");
	}
    }

}
