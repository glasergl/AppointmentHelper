package de.glasergl.ui.appointmentField.test;

import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_0;
import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_1;
import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_2;
import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_3;
import static de.glasergl.standard.util.tests.GeneralTestUtilities.BASE_TEST_RESOURCE_PATH;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import de.glasergl.fileConfiguration.AppointmentsConfigurationHandler;
import de.glasergl.test.GeneralTestElements;
import de.glasergl.ui.appointmentField.AppointmentFieldWrapper;
import de.glasergl.ui.appointmentField.AppointmentFieldWrapperList;

/**
 * Tests the allRepresentsValidAppointments method of
 * AppointmentFieldControllerList.
 * 
 * @author Gabriel Glaser
 */
public final class AllRepresentValidAppointmentsTest {
	/**
	 * Tests whether an empty appointment field list represents valid appointments.
	 */
	@Test
	public void testAllRepresentValidAppointmentsOnEmptyConfiguration() {
		final String path = BASE_TEST_RESOURCE_PATH
				+ "testAllRepresentValidAppointmentsOnEmptyConfiguration-Configuration.json";
		final AppointmentsConfigurationHandler handler = new AppointmentsConfigurationHandler(path);
		final AppointmentFieldWrapperList appointmentFieldList = new AppointmentFieldWrapperList(handler);
		assertTrue(appointmentFieldList.allRepresentValidAppointments());
		GeneralTestElements.deleteTestFileRepresentedBy(handler);
	}

	/**
	 * Tests whether the initial appointment field list represents valid
	 * appointments.
	 */
	@Test
	public void testAllRepresentValidAppointmentsOnNonEmptyConfiguration() {
		final String path = BASE_TEST_RESOURCE_PATH
				+ "testAllRepresentValidAppointmentsOnNonEmptyConfiguration-Configuration.json";
		final AppointmentsConfigurationHandler handler = new AppointmentsConfigurationHandler(path);
		handler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_1, TEST_APPOINTMENT_2, TEST_APPOINTMENT_3));
		final AppointmentFieldWrapperList appointmentFieldList = new AppointmentFieldWrapperList(handler);
		assertTrue(appointmentFieldList.allRepresentValidAppointments());
		GeneralTestElements.deleteTestFileRepresentedBy(handler);
	}

	/**
	 * Tests whether the validity doesn't change after deleting, restoring and
	 * adding new appointments.
	 */
	@Test
	public void testAllRepresentValidAppointmentsAfterChange() {
		final String path = BASE_TEST_RESOURCE_PATH + "testAllRepresentValidAppointmentsAfterChange-Configuration.json";
		final AppointmentsConfigurationHandler handler = new AppointmentsConfigurationHandler(path);
		handler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_1, TEST_APPOINTMENT_2, TEST_APPOINTMENT_3));
		final AppointmentFieldWrapperList appointmentFieldList = new AppointmentFieldWrapperList(handler);
		assertTrue(appointmentFieldList.allRepresentValidAppointments());
		
		appointmentFieldList.delete(0);
		appointmentFieldList.delete(0);
		assertTrue(appointmentFieldList.allRepresentValidAppointments());
		
		appointmentFieldList.addEmptyAppointmentField();
		final AppointmentFieldWrapper appointmentField = appointmentFieldList.getAppointmentField(1);
		appointmentField.setInputAppointment(TEST_APPOINTMENT_0);
		assertTrue(appointmentFieldList.allRepresentValidAppointments());
		
		GeneralTestElements.deleteTestFileRepresentedBy(handler);
	}

	/**
	 * Tests whether the appointment fields aren't considered valid if one has no
	 * name, i.e. a name with a length of zero.
	 */
	@Test
	public void testAllRepresentValidAppointmentsWithInvalidAppointment() {
		final String path = BASE_TEST_RESOURCE_PATH + "testAllRepresentValidAppointmentsAfterChange-Configuration.json";
		final AppointmentsConfigurationHandler handler = new AppointmentsConfigurationHandler(path);
		handler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_1, TEST_APPOINTMENT_2, TEST_APPOINTMENT_3));
		final AppointmentFieldWrapperList appointmentFieldList = new AppointmentFieldWrapperList(handler);
		final AppointmentFieldWrapper appointmentFieldController = appointmentFieldList.getAppointmentField(0);
		appointmentFieldController.setAppointmentName("");
		assertFalse(appointmentFieldList.allRepresentValidAppointments());
		GeneralTestElements.deleteTestFileRepresentedBy(handler);
	}
}
