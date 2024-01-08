package de.glasergl.fileConfiguration.test;

import static org.junit.Assert.assertEquals;
import static test.GeneralTestElements.BASE_TEST_RESOURCE_PATH;
import static appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_1;
import static appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_2;
import static appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_3;

import java.util.Arrays;

import org.junit.Test;

import fileConfiguration.AppointmentsConfigurationHandler;
import test.GeneralTestElements;

/**
 * Tests the update function of AppointmentsConfigurationHandler.
 * 
 * @author Gabriel Glaser
 */
public class UpdateAppointmentsTest {
	/**
	 * Tests whether updating an existing configuration works.
	 */
	@Test
	public void testWithEmptyConfiguration() {
		final String path = BASE_TEST_RESOURCE_PATH + "testWithEmptyConfiguration-Configuration.json";
		final AppointmentsConfigurationHandler handler = new AppointmentsConfigurationHandler(path);
		handler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_1, TEST_APPOINTMENT_2));
		assertEquals(TEST_APPOINTMENT_1, handler.getAppointment(0));
		assertEquals(TEST_APPOINTMENT_2, handler.getAppointment(1));
		assertEquals(2, handler.getSize());
		GeneralTestElements.deleteTestFileRepresentedBy(handler);
	}

	/**
	 * Tests whether updating a configuration with existing appointments works.
	 */
	@Test
	public void testWithExistingAppointments() {
		final String path = BASE_TEST_RESOURCE_PATH + "testWithExistingAppointments-Configuration.json";
		final AppointmentsConfigurationHandler handler = new AppointmentsConfigurationHandler(path);
		handler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_3, TEST_APPOINTMENT_1));
		handler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_2));
		assertEquals(TEST_APPOINTMENT_2, handler.getAppointment(0));
		assertEquals(1, handler.getSize());
		GeneralTestElements.deleteTestFileRepresentedBy(handler);
	}
}
