package fileConfiguration.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static test.GeneralTestElements.BASE_TEST_RESOURCE_PATH;
import static appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_1;
import static appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_2;
import static appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_3;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import appointment.Appointment;
import fileConfiguration.AppointmentsConfigurationHandler;
import test.GeneralTestElements;

/**
 * Tests whether getting the appointments of a configuration via
 * AppointmentConfigurationHandler works.
 * 
 * @author Gabriel Glaser
 */
public final class GetAppointmentsTest {
	/**
	 * Tests whether getting the appointments on an empty configuration results in
	 * an empty list.
	 */
	@Test
	public void testWithEmptyConfiguration() {
		final String path = BASE_TEST_RESOURCE_PATH + "testWithEmptyConfiguration-Configuration.json";
		final AppointmentsConfigurationHandler handler = new AppointmentsConfigurationHandler(path);
		final List<Appointment> appointments = handler.getAppointments();
		assertTrue(appointments.isEmpty());
		GeneralTestElements.deleteTestFileRepresentedBy(handler);
	}

	/**
	 * Tests whether all previously added appointments are retrievable and in the
	 * correct order.
	 */
	@Test
	public void testWithInitialAppointments() {
		final String path = BASE_TEST_RESOURCE_PATH + "testWithInitialAppointments-Configuration.json";
		final AppointmentsConfigurationHandler handler = new AppointmentsConfigurationHandler(path);
		handler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_1, TEST_APPOINTMENT_2));
		final List<Appointment> appointments = handler.getAppointments();
		assertEquals(TEST_APPOINTMENT_1, appointments.get(0));
		assertEquals(TEST_APPOINTMENT_2, appointments.get(1));
		assertEquals(2, appointments.size());
		GeneralTestElements.deleteTestFileRepresentedBy(handler);
	}

	/**
	 * Tests whether accessing a single appointment works.
	 */
	@Test
	public void testSingleAccessGetAppointment() {
		final String path = BASE_TEST_RESOURCE_PATH + "testSingleAccessGetAppointment-Configuration.json";
		final AppointmentsConfigurationHandler handler = new AppointmentsConfigurationHandler(path);
		handler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_2, TEST_APPOINTMENT_3));
		assertEquals(TEST_APPOINTMENT_2, handler.getAppointment(0));
		assertEquals(TEST_APPOINTMENT_3, handler.getAppointment(1));
		GeneralTestElements.deleteTestFileRepresentedBy(handler);
	}

}
