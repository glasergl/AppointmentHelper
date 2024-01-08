package editorUI.appointmentField.test;

import static appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_0;
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
 * Tests the delete method of AppointmentFieldControllerList.
 * 
 * @author Gabriel Glaser
 */
public final class DeleteTest {
	/**
	 * Tests whether the state (number of appointments in the respective lists) are
	 * updated correctly.
	 */
	@Test
	public void testStateAfterDelete() {
		final String path = BASE_TEST_RESOURCE_PATH + "testStateAfterDelete-Configuration.json";
		final AppointmentsConfigurationHandler handler = new AppointmentsConfigurationHandler(path);
		handler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_1, TEST_APPOINTMENT_2, TEST_APPOINTMENT_3));
		final AppointmentFieldWrapperList appointmentFieldList = new AppointmentFieldWrapperList(handler);
		appointmentFieldList.delete(1);
		final AppointmentFieldWrapper appointmentField1 = appointmentFieldList.getAppointmentField(0);
		final AppointmentFieldWrapper appointmentField3 = appointmentFieldList.getAppointmentField(1);

		assertEquals(2, appointmentFieldList.getNumberOfShownAppointments());
		assertEquals(2, appointmentFieldList.getNumberOfAppointmentsInConfiguration());
		assertEquals(TEST_APPOINTMENT_1, appointmentField1.getAppointment());
		assertEquals(TEST_APPOINTMENT_3, appointmentField3.getAppointment());

		GeneralTestElements.deleteTestFileRepresentedBy(handler);
	}

	/**
	 * Tests whether the indices of the input fields are adapted correctly after one
	 * is deleted.
	 */
	@Test
	public void testIndicesAfterDelete() {
		final String path = BASE_TEST_RESOURCE_PATH + "testIndicesAfterDelete-Configuration.json";
		final AppointmentsConfigurationHandler handler = new AppointmentsConfigurationHandler(path);
		handler.updateAppointments(
				Arrays.asList(TEST_APPOINTMENT_0, TEST_APPOINTMENT_1, TEST_APPOINTMENT_2, TEST_APPOINTMENT_3));
		final AppointmentFieldWrapperList appointmentFieldList = new AppointmentFieldWrapperList(handler);
		final AppointmentFieldWrapper appointmentField0 = appointmentFieldList.getAppointmentField(0);
		final AppointmentFieldWrapper appointmentField2 = appointmentFieldList.getAppointmentField(2);
		final AppointmentFieldWrapper appointmentField3 = appointmentFieldList.getAppointmentField(3);
		appointmentFieldList.delete(1);

		assertEquals(0, appointmentField0.getCurrentIndex());
		assertEquals(1, appointmentField2.getCurrentIndex());
		assertEquals(2, appointmentField3.getCurrentIndex());

		GeneralTestElements.deleteTestFileRepresentedBy(handler);
	}
}
