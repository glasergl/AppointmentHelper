package fileConfiguration.test;

import static org.junit.Assert.assertEquals;
import static test.GeneralTestElements.BASE_TEST_RESOURCE_PATH;
import static appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_0;
import static appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_4;

import java.util.Arrays;

import org.junit.Test;

import fileConfiguration.AppointmentsConfigurationHandler;
import test.GeneralTestElements;

/**
 * Tests whether getSize returns the correct size.
 * 
 * @author Gabriel Glaser
 */
public final class GetSizeTest {
	/**
	 * Tests whether the size of an empty configuration is zero.
	 */
	@Test
	public void testGetSizeOnEmptyConfiguration() {
		final String path = BASE_TEST_RESOURCE_PATH + "testGetSizeOnEmptyConfiguration-Configuration.json";
		final AppointmentsConfigurationHandler handler = new AppointmentsConfigurationHandler(path);
		assertEquals(0, handler.getSize());
		GeneralTestElements.deleteTestFileRepresentedBy(handler);
	}

	/**
	 * Tests whether the size is correctly adapted when new appointments are added.
	 */
	@Test
	public void testGetSizeWithUpdatedConfiguration() {
		final String path = BASE_TEST_RESOURCE_PATH + "testGetSizeWithUpdatedConfiguration-Configuration.json";
		final AppointmentsConfigurationHandler handler = new AppointmentsConfigurationHandler(path);
		handler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_0, TEST_APPOINTMENT_4));
		assertEquals(2, handler.getSize());
		GeneralTestElements.deleteTestFileRepresentedBy(handler);
	}
}
