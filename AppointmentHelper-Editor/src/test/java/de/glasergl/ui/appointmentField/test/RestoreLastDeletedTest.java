package de.glasergl.ui.appointmentField.test;

import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_0;
import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_1;
import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_2;
import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_3;
import static de.glasergl.standard.util.tests.GeneralTestUtilities.BASE_TEST_RESOURCE_PATH;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import de.glasergl.fileConfiguration.AppointmentsConfigurationHandler;
import de.glasergl.test.GeneralTestElements;
import de.glasergl.ui.appointmentField.AppointmentFieldWrapper;
import de.glasergl.ui.appointmentField.AppointmentFieldWrapperList;

/**
 * Tests the restoreLastDeleted method of AppointmentFieldControllerList.
 * 
 * @author Gabriel Glaser
 */
public final class RestoreLastDeletedTest {
	/**
	 * Tests whether the state (number of appointments in the respective lists) are
	 * updated correctly.
	 */
	@Test
	public void testStateAfterRestoreLastDeleted() {
		final String path = BASE_TEST_RESOURCE_PATH + "testStateAfterDelete-Configuration.json";
		final AppointmentsConfigurationHandler handler = new AppointmentsConfigurationHandler(path);
		handler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_1, TEST_APPOINTMENT_2, TEST_APPOINTMENT_3));
		final AppointmentFieldWrapperList appointmentFieldList = new AppointmentFieldWrapperList(handler);
		appointmentFieldList.delete(0);
		appointmentFieldList.delete(0);
		appointmentFieldList.restoreLastDeleted();
		final AppointmentFieldWrapper appointmentField0 = appointmentFieldList.getAppointmentField(0);
		final AppointmentFieldWrapper appointmentField1 = appointmentFieldList.getAppointmentField(1);

		assertEquals(1, appointmentFieldList.getNumberOfDeletedAppointments());
		assertEquals(2, appointmentFieldList.getNumberOfShownAppointments());
		assertEquals(1, appointmentFieldList.getNumberOfAppointmentsInConfiguration());
		assertEquals(TEST_APPOINTMENT_3, appointmentField0.getAppointment());
		assertEquals(TEST_APPOINTMENT_2, appointmentField1.getAppointment());

		GeneralTestElements.deleteTestFileRepresentedBy(handler);
	}

	/**
	 * Tests whether the indices of the input fields are adapted correctly after one
	 * is restored after deletion.
	 */
	@Test
	public void testIndicesAfterRestoreLastDeleted() {
		final String path = BASE_TEST_RESOURCE_PATH + "testStateAfterDelete-Configuration.json";
		final AppointmentsConfigurationHandler handler = new AppointmentsConfigurationHandler(path);
		handler.updateAppointments(
				Arrays.asList(TEST_APPOINTMENT_0, TEST_APPOINTMENT_1, TEST_APPOINTMENT_2, TEST_APPOINTMENT_3));
		final AppointmentFieldWrapperList appointmentFieldList = new AppointmentFieldWrapperList(handler);
		appointmentFieldList.delete(1);
		appointmentFieldList.delete(0);
		appointmentFieldList.restoreLastDeleted();
		final AppointmentFieldWrapper appointmentFieldController0 = appointmentFieldList.getAppointmentField(0);
		final AppointmentFieldWrapper appointmentFieldController1 = appointmentFieldList.getAppointmentField(1);
		final AppointmentFieldWrapper appointmentFieldController2 = appointmentFieldList.getAppointmentField(2);

		assertEquals(0, appointmentFieldController0.getCurrentIndex());
		assertEquals(1, appointmentFieldController1.getCurrentIndex());
		assertEquals(2, appointmentFieldController2.getCurrentIndex());
		assertEquals(TEST_APPOINTMENT_2, appointmentFieldController0.getAppointment());
		assertEquals(TEST_APPOINTMENT_3, appointmentFieldController1.getAppointment());
		assertEquals(TEST_APPOINTMENT_0, appointmentFieldController2.getAppointment());

		GeneralTestElements.deleteTestFileRepresentedBy(handler);
	}
}
