package fileInteraction.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import appointment.Appointment;
import fileInteraction.AppointmentFileInteracter;
import simpleDate.SimpleDate;

/**
 * @author Gabriel Glaser
 * @version 19.01.2022
 */
public class TestAppointmentFileInteracter {

    static Appointment testAppointment1 = new Appointment(new SimpleDate(10, 2), "TestPerson1", "Description1", false);
    static Appointment testAppointment2 = new Appointment(new SimpleDate(7, 11), "TestPerson2", "Description2", true);

    @Test
    public void testCreateEmptyAppointmentFile() {
	File emptyAppointmentFile = new File("src\\test\\resources\\emptyAppointmentTestFile.json");
	assertFalse(emptyAppointmentFile.exists());
	AppointmentFileInteracter.createEmptyAppointmentFile("src\\test\\resources\\emptyAppointmentTestFile.json");
	assertTrue(emptyAppointmentFile.exists());
	try (final BufferedReader reader = new BufferedReader(new FileReader(emptyAppointmentFile))) {
	    assertEquals("[", reader.readLine());
	    assertEquals("", reader.readLine());
	    assertEquals("]", reader.readLine());
	} catch (IOException e) {
	    fail("Couldn't read emptyAppointmentTestFile");
	} finally {
	    emptyAppointmentFile.delete();
	}
    }

    @Test
    public void testAdd() {
	File testAdd = createEmptyTestFile("testAdd.json");
	assertFalse(AppointmentFileInteracter.contains(testAppointment1, testAdd));
	AppointmentFileInteracter.add(testAppointment1, testAdd);
	assertTrue(AppointmentFileInteracter.contains(testAppointment1, testAdd));
	testAdd.delete();
    }

    @Test
    public void testRemove() {
	File testRemove = createEmptyTestFile("testRemove.json");
	AppointmentFileInteracter.add(testAppointment1, testRemove);
	AppointmentFileInteracter.remove(testAppointment1, testRemove);
	assertFalse(AppointmentFileInteracter.contains(testAppointment1, testRemove));
	testRemove.delete();
    }

    @Test
    public void testContains() {
	File testContains = createEmptyTestFile("testContains.json");
	Appointment testAppointment3 = new Appointment("Tom", false);
	AppointmentFileInteracter.add(testAppointment1, testContains);
	AppointmentFileInteracter.add(testAppointment2, testContains);
	assertTrue(AppointmentFileInteracter.contains(testAppointment1, testContains));
	assertTrue(AppointmentFileInteracter.contains(testAppointment2, testContains));
	assertFalse(AppointmentFileInteracter.contains(testAppointment3, testContains));
	testContains.delete();
    }

    @Test
    public void testGetAppointments() {
	File testGetAppointments = createEmptyTestFile("testGetAppointments.json");
	AppointmentFileInteracter.add(testAppointment1, testGetAppointments);
	AppointmentFileInteracter.add(testAppointment2, testGetAppointments);
	final List<Appointment> appointments = AppointmentFileInteracter.getAppointments(testGetAppointments);
	assertTrue(appointments.contains(testAppointment1));
	assertTrue(appointments.contains(testAppointment2));
	assertEquals(2, appointments.size());
	testGetAppointments.delete();
    }

    private static File createEmptyTestFile(final String name) {
	return AppointmentFileInteracter.createEmptyAppointmentFile("src\\test\\resources\\" + name);
    }

}
