package de.glasergl.ui.appointmentField.test;

import static de.glasergl.standard.util.tests.GeneralTestUtilities.BASE_TEST_RESOURCE_PATH;
import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_1;
import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_2;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import de.glasergl.fileConfiguration.AppointmentsConfigurationHandler;
import de.glasergl.test.GeneralTestElements;
import de.glasergl.ui.appointmentField.AppointmentFieldWrapperList;

/**
 * Tests common things about the TestAppointmentFieldControllerList.
 * 
 * @author Gabriel Glaser
 */
public final class AppointmentFieldControllerListTest {
	/**
	 * Tests whether the instance initializes itself correctly with an empty
	 * configuration.
	 */
	@Test
	public void testInitialStateWithEmptyConfiguration() {
		final String path = BASE_TEST_RESOURCE_PATH + "testInitialStateWithEmptyConfiguration-Configuration.json";
		final AppointmentsConfigurationHandler handler = new AppointmentsConfigurationHandler(path);
		final AppointmentFieldWrapperList appointmentFieldList = new AppointmentFieldWrapperList(handler);

		assertEquals(0, appointmentFieldList.getNumberOfShownAppointments());
		assertEquals(0, appointmentFieldList.getNumberOfAppointmentsInConfiguration());

		GeneralTestElements.deleteTestFileRepresentedBy(handler);
	}

	/**
	 * Tests whether the instance initializes itself correctly with a non-empty
	 * configuration.
	 */
	@Test
	public void testInitialStateWithNonEmpyConfiguration() {
		final String path = BASE_TEST_RESOURCE_PATH + "testInitialStateWithNonEmpyConfiguration-Configuration.json";
		final AppointmentsConfigurationHandler handler = new AppointmentsConfigurationHandler(path);
		handler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_1, TEST_APPOINTMENT_2));
		final AppointmentFieldWrapperList appointmentFieldList = new AppointmentFieldWrapperList(handler);

		assertEquals(2, appointmentFieldList.getNumberOfShownAppointments());
		assertEquals(2, appointmentFieldList.getNumberOfAppointmentsInConfiguration());

		GeneralTestElements.deleteTestFileRepresentedBy(handler);
	}
}
