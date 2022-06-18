package de.glasergl.configuration.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;

import de.glasergl.appointment.Appointment;
import de.glasergl.configuration.AppointmentsConfigurationHandler;
import de.glasergl.configuration.ConfigurationHandler;
import de.glasergl.simpleDate.SimpleDate;

/**
 * @author Gabriel Glaser
 * @version 19.01.2022
 */
public class TestAppointmentsHandler {

    static Appointment testAppointment1 = new Appointment(new SimpleDate(10, 2), "TestPerson1", "Description1", false);
    static Appointment testAppointment2 = new Appointment(new SimpleDate(7, 11), "TestPerson2", "Description2", true);
    static Appointment testAppointment3 = new Appointment(new SimpleDate(3, 7), "TestPerson3", "Description3", true);

    @Test
    public void testInitialState() {
	File testInitialStateConfigurationFile = createEmptyTestFile("testInitialStateConfiguration.json");
	AppointmentsConfigurationHandler appointmentsHandler = getAppointmentsHandler(testInitialStateConfigurationFile);
	List<Appointment> appointments = appointmentsHandler.getAppointments();
	assertEquals(0, appointments.size());
    }

    @Test
    public void testAdd() {
	File testAddFile = createEmptyTestFile("testAdd.json");
	AppointmentsConfigurationHandler appointmentsHandler = getAppointmentsHandler(testAddFile);
	assertFalse(appointmentsHandler.contains(testAppointment1));
	appointmentsHandler.add(testAppointment1);
	assertTrue(appointmentsHandler.contains(testAppointment1));
    }

    @Test
    public void testRemove() {
	File testRemoveFile = createEmptyTestFile("testRemove.json");
	AppointmentsConfigurationHandler appointmentsHandler = getAppointmentsHandler(testRemoveFile);
	appointmentsHandler.add(testAppointment1);
	appointmentsHandler.add(testAppointment2);
	appointmentsHandler.remove(1);
	assertTrue(appointmentsHandler.contains(testAppointment1));
	assertFalse(appointmentsHandler.contains(testAppointment2));
	assertEquals(1, appointmentsHandler.size());
    }

    @Test
    public void testRemoveWithIllegalIndex() {
	File testRemoveWithIllegalIndexFile = createEmptyTestFile("testRemoveWithIllegalIndex.json");
	AppointmentsConfigurationHandler appointmentsHandler = getAppointmentsHandler(testRemoveWithIllegalIndexFile);
	appointmentsHandler.add(testAppointment1);
	assertThrows(NoSuchElementException.class, () -> {
	    appointmentsHandler.remove(1);
	});
    }

    @Test
    public void testGetAppointments() {
	File testGetAppointmentsFile = createEmptyTestFile("testGetAppointments.json");
	AppointmentsConfigurationHandler appointmentsHandler = getAppointmentsHandler(testGetAppointmentsFile);
	appointmentsHandler.add(testAppointment3);
	appointmentsHandler.add(testAppointment2);
	final List<Appointment> appointments = appointmentsHandler.getAppointments();
	assertFalse(appointments.contains(testAppointment1));
	assertTrue(appointments.contains(testAppointment2));
	assertTrue(appointments.contains(testAppointment3));
	assertEquals(2, appointments.size());
    }

    private static File createEmptyTestFile(final String fileName) {
	final File testConfigurationFile = ConfigurationHandler.createNewConfigurationFile("src\\test\\resources\\" + fileName);
	testConfigurationFile.deleteOnExit();
	return testConfigurationFile;
    }

    private static AppointmentsConfigurationHandler getAppointmentsHandler(final File configurationFile) {
	ConfigurationHandler configurationHandler = new ConfigurationHandler(configurationFile);
	return configurationHandler.getAppointmentsHandler();
    }

}
