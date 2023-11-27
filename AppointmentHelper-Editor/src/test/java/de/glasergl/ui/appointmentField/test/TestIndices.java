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
 * Tests whether indices the indices are correct in the initial situation. Other
 * test classes will test the adaption of the indices respectively.
 * 
 * @author Gabriel Glaser
 * @version 01.07.2022
 */
public final class TestIndices {

	@Test
	public void testInitialIndices() {
		final String path = BASE_TEST_RESOURCE_PATH + "testInitialIndices-Configuration.json";
		final ConfigurationHandler configurationHandler = new ConfigurationHandler(path);
		final AppointmentsConfigurationHandler appointmentsConfigurationHandler = configurationHandler
				.getAppointmentsHandler();
		appointmentsConfigurationHandler
				.updateAppointments(Arrays.asList(TEST_APPOINTMENT_2, TEST_APPOINTMENT_3, TEST_APPOINTMENT_1));
		final AppointmentFieldControllerList appointmentFieldList = new AppointmentFieldControllerList(
				configurationHandler);
		final AppointmentFieldController appointmentFieldController0 = appointmentFieldList.getAppointmentField(0);
		final AppointmentFieldController appointmentFieldController1 = appointmentFieldList.getAppointmentField(1);
		final AppointmentFieldController appointmentFieldController2 = appointmentFieldList.getAppointmentField(2);

		assertEquals(0, appointmentFieldController0.getCurrentIndex());
		assertEquals(1, appointmentFieldController1.getCurrentIndex());
		assertEquals(2, appointmentFieldController2.getCurrentIndex());
		assertEquals(TEST_APPOINTMENT_2, appointmentFieldController0.getAppointment());
		assertEquals(TEST_APPOINTMENT_3, appointmentFieldController1.getAppointment());
		assertEquals(TEST_APPOINTMENT_1, appointmentFieldController2.getAppointment());

		try {
			Files.delete(Paths.get(path));
		} catch (final IOException e) {
			fail("Couldn't delete test-file");
		}
	}

}
