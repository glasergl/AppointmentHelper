package de.glasergl.ui.appointmentField.test;

import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_1;
import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_2;
import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_3;
import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_4;
import static de.glasergl.standard.util.tests.GeneralTestUtilities.BASE_TEST_RESOURCE_PATH;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import de.glasergl.appointment.InvalidAppointmentException;
import de.glasergl.fileConfiguration.AppointmentsConfigurationHandler;
import de.glasergl.test.GeneralTestElements;
import de.glasergl.ui.appointmentField.AppointmentFieldWrapper;
import de.glasergl.ui.appointmentField.AppointmentFieldWrapperList;

/**
 * Tests the store method of AppointmentFieldControllerList.
 * 
 * @author Gabriel Glaser
 */
public final class StoreTest {
	/**
	 * Tests whether adding a new appointment field and storing it to an empty
	 * configuration updates the state of the appointment field list and
	 * configuration correctly.
	 */
	@Test
	public void testSuccessfulStoreOnEmptyConfiguration() {
		final String path = BASE_TEST_RESOURCE_PATH + "testSuccessfulStoreOnEmptyConfiguration-Configuration.json";
		final AppointmentsConfigurationHandler handler = new AppointmentsConfigurationHandler(path);
		final AppointmentFieldWrapperList appointmentFieldList = new AppointmentFieldWrapperList(handler);
		appointmentFieldList.addEmptyAppointmentField();
		final AppointmentFieldWrapper appointmentField = appointmentFieldList.getAppointmentField(0);
		appointmentField.setInputAppointment(TEST_APPOINTMENT_3);
		appointmentFieldList.storeAll();

		assertEquals(1, appointmentFieldList.getNumberOfShownAppointments());
		assertEquals(1, appointmentFieldList.getNumberOfAppointmentsInConfiguration());
		assertEquals(TEST_APPOINTMENT_3, handler.getAppointment(0));

		GeneralTestElements.deleteTestFileRepresentedBy(handler);
	}

	/**
	 * Tests whether adding new appointment fields and storing them updates the
	 * state of the appointment field list and configuration correctly.
	 */
	@Test
	public void testSuccessfulStoreOnNonEmptyConfiguration() {
		final String path = BASE_TEST_RESOURCE_PATH + "testSuccessfulStoreOnNonEmptyConfiguration-Configuration.json";
		final AppointmentsConfigurationHandler handler = new AppointmentsConfigurationHandler(path);
		handler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_1, TEST_APPOINTMENT_2, TEST_APPOINTMENT_3));
		final AppointmentFieldWrapperList appointmentFieldList = new AppointmentFieldWrapperList(handler);
		appointmentFieldList.addEmptyAppointmentField();
		final AppointmentFieldWrapper appointmentField4 = appointmentFieldList.getAppointmentField(3);
		appointmentField4.setInputAppointment(TEST_APPOINTMENT_4);
		appointmentFieldList.storeAll();

		assertEquals(4, appointmentFieldList.getNumberOfShownAppointments());
		assertEquals(4, appointmentFieldList.getNumberOfAppointmentsInConfiguration());
		assertEquals(TEST_APPOINTMENT_4, handler.getAppointment(3));

		GeneralTestElements.deleteTestFileRepresentedBy(handler);
	}

	/**
	 * Testing whether trying to store an invalid appointment leads to an
	 * InvalidAppointmentException.
	 */
	@Test
	public void testStoreFailOnEmptyConfiguration() {
		final String path = BASE_TEST_RESOURCE_PATH + "testStoreFailOnEmptyConfiguration-Configuration.json";
		final AppointmentsConfigurationHandler handler = new AppointmentsConfigurationHandler(path);
		final AppointmentFieldWrapperList appointmentFieldList = new AppointmentFieldWrapperList(handler);
		appointmentFieldList.addEmptyAppointmentField();
		assertThrows(InvalidAppointmentException.class, () -> {
			appointmentFieldList.storeAll();
		});
		GeneralTestElements.deleteTestFileRepresentedBy(handler);
	}

	/**
	 * Tests whether trying to store an existing, non-valid appointment leads to an
	 * InvalidAppointmentException.
	 */
	@Test
	public void testStoreFailOnNonEmptyConfiguration() {
		final String path = BASE_TEST_RESOURCE_PATH + "testStoreFailOnNonEmptyConfiguration-Configuration.json";
		final AppointmentsConfigurationHandler handler = new AppointmentsConfigurationHandler(path);
		handler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_1, TEST_APPOINTMENT_2, TEST_APPOINTMENT_3));
		final AppointmentFieldWrapperList appointmentFieldList = new AppointmentFieldWrapperList(handler);
		final AppointmentFieldWrapper appointmentField = appointmentFieldList.getAppointmentField(1);
		appointmentField.setAppointmentName("");
		assertThrows(InvalidAppointmentException.class, () -> {
			appointmentFieldList.storeAll();
		});
		GeneralTestElements.deleteTestFileRepresentedBy(handler);
	}
}
