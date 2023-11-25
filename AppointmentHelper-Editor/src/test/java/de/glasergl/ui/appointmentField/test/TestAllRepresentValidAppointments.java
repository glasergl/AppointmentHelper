package de.glasergl.ui.appointmentField.test;

import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_0;
import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_1;
import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_2;
import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_3;
import static de.glasergl.standard.util.tests.GeneralTestUtilities.BASE_TEST_RESOURCE_PATH;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
 * Tests the allRepresentsValidAppointments method of
 * AppointmentFieldControllerList.
 * 
 * @author Gabriel Glaser
 * @version 01.07.2022
 */
public final class TestAllRepresentValidAppointments {

    /**
     * Tests whether an empty appointment field list represents valid appointments.
     */
    @Test
    public void testAllRepresentValidAppointmentsOnEmptyConfiguration() {
	final String path = BASE_TEST_RESOURCE_PATH + "testAllRepresentValidAppointmentsOnEmptyConfiguration-Configuration.json";
	final ConfigurationHandler configurationHandler = new ConfigurationHandler(path);
	final AppointmentFieldControllerList appointmentFieldList = new AppointmentFieldControllerList(configurationHandler);

	assertTrue(appointmentFieldList.allRepresentValidAppointments());

	try {
	    Files.delete(Paths.get(path));
	} catch (final IOException e) {
	    fail("Couldn't delete test-file");
	}
    }

    /**
     * Tests whether the initial appointment field list represents valid
     * appointments.
     */
    @Test
    public void testAllRepresentValidAppointmentsOnNonEmptyConfiguration() {
	final String path = BASE_TEST_RESOURCE_PATH + "testAllRepresentValidAppointmentsOnNonEmptyConfiguration-Configuration.json";
	final ConfigurationHandler configurationHandler = new ConfigurationHandler(path);
	final AppointmentsConfigurationHandler appointmentsConfigurationHandler = configurationHandler.getAppointmentsHandler();
	appointmentsConfigurationHandler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_1, TEST_APPOINTMENT_2, TEST_APPOINTMENT_3));
	final AppointmentFieldControllerList appointmentFieldList = new AppointmentFieldControllerList(configurationHandler);

	assertTrue(appointmentFieldList.allRepresentValidAppointments());

	try {
	    Files.delete(Paths.get(path));
	} catch (final IOException e) {
	    fail("Couldn't delete test-file");
	}
    }

    /**
     * Tests whether the validity doesn't change after deleting, restoring and
     * adding new appointments.
     */
    @Test
    public void testAllRepresentValidAppointmentsAfterChange() {
	final String path = BASE_TEST_RESOURCE_PATH + "testAllRepresentValidAppointmentsAfterChange-Configuration.json";
	final ConfigurationHandler configurationHandler = new ConfigurationHandler(path);
	final AppointmentsConfigurationHandler appointmentsConfigurationHandler = configurationHandler.getAppointmentsHandler();
	appointmentsConfigurationHandler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_1, TEST_APPOINTMENT_2, TEST_APPOINTMENT_3));
	final AppointmentFieldControllerList appointmentFieldList = new AppointmentFieldControllerList(configurationHandler);
	appointmentFieldList.delete(0);
	appointmentFieldList.delete(0);
	appointmentFieldList.addEmptyAppointmentField();
	final AppointmentFieldController appointmentField = appointmentFieldList.getAppointmentField(1);
	appointmentField.setInputAppointment(TEST_APPOINTMENT_0);
	appointmentFieldList.restoreLastDeleted();

	assertTrue(appointmentFieldList.allRepresentValidAppointments());

	try {
	    Files.delete(Paths.get(path));
	} catch (final IOException e) {
	    fail("Couldn't delete test-file");
	}
    }

    /**
     * Tests whether the appointment fields aren't considered valid if one has no
     * name, i.e. a name with a length of zero.
     */
    @Test
    public void testAllRepresentValidAppointmentsWithInvalidAppointment() {
	final String path = BASE_TEST_RESOURCE_PATH + "testAllRepresentValidAppointmentsAfterChange-Configuration.json";
	final ConfigurationHandler configurationHandler = new ConfigurationHandler(path);
	final AppointmentsConfigurationHandler appointmentsConfigurationHandler = configurationHandler.getAppointmentsHandler();
	appointmentsConfigurationHandler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_1, TEST_APPOINTMENT_2, TEST_APPOINTMENT_3));
	final AppointmentFieldControllerList appointmentFieldList = new AppointmentFieldControllerList(configurationHandler);
	final AppointmentFieldController appointmentFieldController = appointmentFieldList.getAppointmentField(0);
	appointmentFieldController.setName("");

	assertFalse(appointmentFieldList.allRepresentValidAppointments());

	try {
	    Files.delete(Paths.get(path));
	} catch (final IOException e) {
	    fail("Couldn't delete test-file");
	}
    }

}
