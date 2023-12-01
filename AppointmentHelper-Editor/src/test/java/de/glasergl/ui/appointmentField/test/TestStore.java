package de.glasergl.ui.appointmentField.test;

import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_1;
import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_2;
import static de.glasergl.appointment.ExampleAppointmentFactory.TEST_APPOINTMENT_3;
import static de.glasergl.standard.util.tests.GeneralTestUtilities.BASE_TEST_RESOURCE_PATH;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import org.junit.Test;

import de.glasergl.appointment.InvalidAppointmentException;
import de.glasergl.configuration.AppointmentsConfigurationHandler;
import de.glasergl.configuration.ConfigurationHandler;
import de.glasergl.ui.appointmentField.AppointmentFieldController;
import de.glasergl.ui.appointmentField.AppointmentFieldControllerList;

/**
 * Tests the store method of AppointmentFieldControllerList.
 * 
 * @author Gabriel Glaser
 */
public final class TestStore {

	/**
	 * Tests whether adding a new appointment field and storing it to an empty
	 * configuration updates the state of the appointment field list and
	 * configuration correctly.
	 */
	@Test
	public void testSuccessfulStoreOnEmptyConfiguration() {
		final String path = BASE_TEST_RESOURCE_PATH + "testSuccessfulStoreOnEmptyConfiguration-Configuration.json";
		final ConfigurationHandler configurationHandler = new ConfigurationHandler(path);
		final AppointmentsConfigurationHandler appointmentsConfigurationHandler = configurationHandler
				.getAppointmentsHandler();
		final AppointmentFieldControllerList appointmentFieldList = new AppointmentFieldControllerList(
				configurationHandler);
		appointmentFieldList.addEmptyAppointmentField();
		final AppointmentFieldController appointmentField = appointmentFieldList.getAppointmentField(0);
		appointmentField.setInputAppointment(TEST_APPOINTMENT_3);
		appointmentFieldList.storeAll();

		assertEquals(1, appointmentFieldList.getNumberOfShownAppointments());
		assertEquals(1, appointmentFieldList.getNumberOfAppointmentsInConfiguration());
		assertEquals(TEST_APPOINTMENT_3, appointmentsConfigurationHandler.getAppointment(0));

		try {
			Files.delete(Paths.get(path));
		} catch (final IOException e) {
			fail("Couldn't delete test-file");
		}
	}

	/**
	 * Tests whether adding new appointment fields and storing them updates the
	 * state of the appointment field list and configuration correctly.
	 */
	@Test
	public void testSuccessfulStoreOnNonEmptyConfiguration() {
		final String path = BASE_TEST_RESOURCE_PATH + "testSuccessfulStoreOnNonEmptyConfiguration-Configuration.json";
		final ConfigurationHandler configurationHandler = new ConfigurationHandler(path);
		final AppointmentsConfigurationHandler appointmentsConfigurationHandler = configurationHandler
				.getAppointmentsHandler();
		appointmentsConfigurationHandler
				.updateAppointments(Arrays.asList(TEST_APPOINTMENT_1, TEST_APPOINTMENT_2, TEST_APPOINTMENT_3));
		final AppointmentFieldControllerList appointmentFieldList = new AppointmentFieldControllerList(
				configurationHandler);
		appointmentFieldList.addEmptyAppointmentField();
		appointmentFieldList.addEmptyAppointmentField();
		final AppointmentFieldController appointmentField3 = appointmentFieldList.getAppointmentField(3);
		appointmentField3.setInputAppointment(TEST_APPOINTMENT_2);
		final AppointmentFieldController appointmentField4 = appointmentFieldList.getAppointmentField(4);
		appointmentField4.setInputAppointment(TEST_APPOINTMENT_1);
		appointmentFieldList.storeAll();

		assertEquals(5, appointmentFieldList.getNumberOfShownAppointments());
		assertEquals(5, appointmentFieldList.getNumberOfAppointmentsInConfiguration());
		assertEquals(TEST_APPOINTMENT_2, appointmentsConfigurationHandler.getAppointment(3));
		assertEquals(TEST_APPOINTMENT_1, appointmentsConfigurationHandler.getAppointment(4));

		try {
			Files.delete(Paths.get(path));
		} catch (final IOException e) {
			fail("Couldn't delete test-file");
		}
	}

	/**
	 * Testing whether trying to store an invalid appointment leads to an
	 * InvalidAppointmentException.
	 */
	@Test
	public void testStoreFailOnEmptyConfiguration() {
		final String path = BASE_TEST_RESOURCE_PATH + "testStoreFailOnEmptyConfiguration-Configuration.json";
		final ConfigurationHandler configurationHandler = new ConfigurationHandler(path);
		final AppointmentFieldControllerList appointmentFieldList = new AppointmentFieldControllerList(
				configurationHandler);
		appointmentFieldList.addEmptyAppointmentField();

		assertThrows(InvalidAppointmentException.class, () -> {
			appointmentFieldList.storeAll();
		});

		try {
			Files.delete(Paths.get(path));
		} catch (final IOException e) {
			fail("Couldn't delete test-file");
		}
	}

	/**
	 * Tests whether trying to store an existing, non-valid appointment leads to an
	 * InvalidAppointmentException.
	 */
	@Test
	public void testStoreFailOnNonEmptyConfiguration() {
		final String path = BASE_TEST_RESOURCE_PATH + "testStoreFailOnNonEmptyConfiguration-Configuration.json";
		final ConfigurationHandler configurationHandler = new ConfigurationHandler(path);
		final AppointmentsConfigurationHandler appointmentsConfigurationHandler = configurationHandler
				.getAppointmentsHandler();
		appointmentsConfigurationHandler
				.updateAppointments(Arrays.asList(TEST_APPOINTMENT_1, TEST_APPOINTMENT_2, TEST_APPOINTMENT_3));
		final AppointmentFieldControllerList appointmentFieldList = new AppointmentFieldControllerList(
				configurationHandler);
		final AppointmentFieldController appointmentField = appointmentFieldList.getAppointmentField(1);
		appointmentField.setName("");

		assertThrows(InvalidAppointmentException.class, () -> {
			appointmentFieldList.storeAll();
		});

		try {
			Files.delete(Paths.get(path));
		} catch (final IOException e) {
			fail("Couldn't delete test-file");
		}
	}

}
