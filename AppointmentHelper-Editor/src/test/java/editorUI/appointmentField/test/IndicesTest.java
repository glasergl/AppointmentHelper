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
 * Tests whether indices the indices are correct in the initial situation. Other
 * test classes will test the adaption of the indices respectively.
 * 
 * @author Gabriel Glaser
 */
public final class IndicesTest {

	@Test
	public void testInitialIndices() {
		final String path = BASE_TEST_RESOURCE_PATH + "testInitialIndices-Configuration.json";
		final AppointmentsConfigurationHandler handler = new AppointmentsConfigurationHandler(path);
		handler.updateAppointments(Arrays.asList(TEST_APPOINTMENT_1, TEST_APPOINTMENT_2, TEST_APPOINTMENT_3));
		final AppointmentFieldWrapperList appointmentFieldList = new AppointmentFieldWrapperList(handler);
		final AppointmentFieldWrapper appointmentFieldController0 = appointmentFieldList.getAppointmentField(0);
		final AppointmentFieldWrapper appointmentFieldController1 = appointmentFieldList.getAppointmentField(1);
		final AppointmentFieldWrapper appointmentFieldController2 = appointmentFieldList.getAppointmentField(2);

		assertEquals(0, appointmentFieldController0.getCurrentIndex());
		assertEquals(1, appointmentFieldController1.getCurrentIndex());
		assertEquals(2, appointmentFieldController2.getCurrentIndex());
		assertEquals(TEST_APPOINTMENT_1, appointmentFieldController0.getAppointment());
		assertEquals(TEST_APPOINTMENT_2, appointmentFieldController1.getAppointment());
		assertEquals(TEST_APPOINTMENT_3, appointmentFieldController2.getAppointment());

		GeneralTestElements.deleteTestFileRepresentedBy(handler);
	}

}
