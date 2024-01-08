package editorUI.appointmentField.test;

import static appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_1;
import static appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_2;
import static appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_3;
import static standardGlaserGl.util.tests.GeneralTestUtilities.BASE_TEST_RESOURCE_PATH;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import editorUI.appointmentField.AppointmentFieldWrapper;
import editorUI.appointmentField.AppointmentFieldWrapperList;
import fileConfiguration.AppointmentsConfigurationHandler;
import test.GeneralTestElements;

/**
 * Tests the add empty method of AppointmentFieldControllerList.
 * 
 * @author Gabriel Glaser
 */
public final class AddEmptyTest {
	/**
	 * Tests whether the AppointmentFieldControllerList updates its state correctly
	 * after adding an empty appointment field to an empty configuration.
	 */
	@Test
	public void testAddEmptyOnEmptyConfiguration() {
		final String path = BASE_TEST_RESOURCE_PATH + "testAddEmptyOnEmptyConfiguration-Configuration.json";
		final AppointmentsConfigurationHandler handler = new AppointmentsConfigurationHandler(path);
		final AppointmentFieldWrapperList appointmentFieldList = new AppointmentFieldWrapperList(handler);
		appointmentFieldList.addEmptyAppointmentField();

		assertEquals(1, appointmentFieldList.getNumberOfShownAppointments());
		assertEquals(0, appointmentFieldList.getNumberOfAppointmentsInConfiguration());

		GeneralTestElements.deleteTestFileRepresentedBy(handler);
	}

	/**
	 * Tests whether the AppointmentFieldControllerList updates its state correctly
	 * after adding an empty appointment field to an existing configuration.
	 * 
	 */
	@Test
	public void testAddEmptyOnNonEmptyConfiguration() {
		final String path = BASE_TEST_RESOURCE_PATH + "testAddEmptyOnNonEmptyConfiguration-Configuration.json";
		final AppointmentsConfigurationHandler handler = new AppointmentsConfigurationHandler(path);
		handler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_1, TEST_APPOINTMENT_2, TEST_APPOINTMENT_3));
		final AppointmentFieldWrapperList appointmentFieldList = new AppointmentFieldWrapperList(handler);
		appointmentFieldList.addEmptyAppointmentField();

		assertEquals(4, appointmentFieldList.getNumberOfShownAppointments());
		assertEquals(3, appointmentFieldList.getNumberOfAppointmentsInConfiguration());

		GeneralTestElements.deleteTestFileRepresentedBy(handler);
	}

	/**
	 * Tests whether the indices of the appointment input fields are adapted
	 * correctly after adding a new input field.
	 */
	@Test
	public void testIndicesAfterAddEmpty() {
		final String path = BASE_TEST_RESOURCE_PATH + "testAddEmptyOnNonEmptyConfiguration-Configuration.json";
		final AppointmentsConfigurationHandler handler = new AppointmentsConfigurationHandler(path);
		handler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_1, TEST_APPOINTMENT_2));
		final AppointmentFieldWrapperList appointmentFieldList = new AppointmentFieldWrapperList(handler);
		final AppointmentFieldWrapper appointmentFieldController0 = appointmentFieldList.getAppointmentField(0);
		final AppointmentFieldWrapper appointmentFieldController1 = appointmentFieldList.getAppointmentField(1);
		appointmentFieldList.addEmptyAppointmentField();
		final AppointmentFieldWrapper appointmentFieldController2 = appointmentFieldList.getAppointmentField(2);

		assertEquals(0, appointmentFieldController0.getCurrentIndex());
		assertEquals(1, appointmentFieldController1.getCurrentIndex());
		assertEquals(2, appointmentFieldController2.getCurrentIndex());

		GeneralTestElements.deleteTestFileRepresentedBy(handler);
	}
}
