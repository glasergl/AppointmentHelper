package editorUI.appointmentField.test;

import static appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_1;
import static appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_2;
import static appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_3;
import static de.glasergl.standard.util.tests.GeneralTestUtilities.BASE_TEST_RESOURCE_PATH;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import appointment.Appointment;
import editorUI.appointmentField.AppointmentFieldWrapper;
import editorUI.appointmentField.AppointmentFieldWrapperList;
import fileConfiguration.AppointmentsConfigurationHandler;
import test.GeneralTestElements;

/**
 * Tests the getInput method of AppointmentFieldControllerList.
 * 
 * @author Gabriel Glaser
 */
public final class GetInputTest {
	/**
	 * Tests whether the resulting input list is empty on an empty configuration.
	 */
	@Test
	public void testInitialGetInputOnEmptyConfiguration() {
		final String path = BASE_TEST_RESOURCE_PATH + "testInitialGetInputOnEmptyConfiguration-Configuration.json";
		final AppointmentsConfigurationHandler handler = new AppointmentsConfigurationHandler(path);
		final AppointmentFieldWrapperList appointmentFieldList = new AppointmentFieldWrapperList(handler);
		final List<Appointment> input = appointmentFieldList.getInput();
		assertTrue(input.isEmpty());
		GeneralTestElements.deleteTestFileRepresentedBy(handler);
	}

	/**
	 * Tests whether the the appointments in the configuration are contained and in
	 * the correct order in the input list.
	 */
	@Test
	public void testInitialGetInputOnNonEmptyConfiguration() {
		final String path = BASE_TEST_RESOURCE_PATH + "testInitialGetInputOnNonEmptyConfiguration-Configuration.json";
		final AppointmentsConfigurationHandler handler = new AppointmentsConfigurationHandler(path);
		handler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_1, TEST_APPOINTMENT_2, TEST_APPOINTMENT_3));
		final AppointmentFieldWrapperList appointmentFieldList = new AppointmentFieldWrapperList(handler);
		final List<Appointment> input = appointmentFieldList.getInput();

		assertEquals(TEST_APPOINTMENT_1, input.get(0));
		assertEquals(TEST_APPOINTMENT_2, input.get(1));
		assertEquals(TEST_APPOINTMENT_3, input.get(2));
		assertEquals(3, input.size());

		GeneralTestElements.deleteTestFileRepresentedBy(handler);
	}

	/**
	 * Tests whether adding a new appointment field adapts the resulting input list
	 * accordingly.
	 */
	@Test
	public void testGetInputOnUpdatedInputWithAdd() {
		final String path = BASE_TEST_RESOURCE_PATH + "testGetInputOnUpdatedInputWithAdd-Configuration.json";
		final AppointmentsConfigurationHandler handler = new AppointmentsConfigurationHandler(path);
		handler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_2));
		final AppointmentFieldWrapperList appointmentFieldList = new AppointmentFieldWrapperList(handler);
		appointmentFieldList.addEmptyAppointmentField();
		final AppointmentFieldWrapper appointmentField = appointmentFieldList.getAppointmentField(1);
		appointmentField.setInputAppointment(TEST_APPOINTMENT_3);
		final List<Appointment> input = appointmentFieldList.getInput();

		assertEquals(TEST_APPOINTMENT_2, input.get(0));
		assertEquals(TEST_APPOINTMENT_3, input.get(1));
		assertEquals(2, input.size());

		GeneralTestElements.deleteTestFileRepresentedBy(handler);
	}

	/**
	 * Tests whether removing an appointment field adapts the resulting input list
	 * accordingly.
	 */
	@Test
	public void testGetInputOnUpdatedInputWithRemove() {
		final String path = BASE_TEST_RESOURCE_PATH + "testGetInputOnUpdatedInputWithRemove-Configuration.json";
		final AppointmentsConfigurationHandler handler = new AppointmentsConfigurationHandler(path);
		handler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_1, TEST_APPOINTMENT_2));
		final AppointmentFieldWrapperList appointmentFieldList = new AppointmentFieldWrapperList(handler);
		appointmentFieldList.delete(0);
		final List<Appointment> input = appointmentFieldList.getInput();

		assertEquals(TEST_APPOINTMENT_2, input.get(0));
		assertEquals(1, input.size());

		GeneralTestElements.deleteTestFileRepresentedBy(handler);
	}
}
