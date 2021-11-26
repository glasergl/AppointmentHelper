package appointments;

import static org.junit.Assert.*;
import org.junit.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author Gabriel Glaser
 * @version 10.9.2020
 */
public class TestAppointmentInteracter {

    static File testAppointments = createEmptyTestFile("correctAppointments.json");

    static Appointment testAppointment1 = new Appointment(new SimpleDate(10, 2), "Party", "Chips mitbringen", false);
    static Appointment testAppointment2 = new Appointment(new SimpleDate(7, 11), "Gustav Ganz", "Kuchen kaufen", true);
    static Appointment testAppointment3 = new Appointment(new SimpleDate(8, 8), "Prüfung", "Theo2 ist toll", false);

    @BeforeClass
    public static void setupCorrectTestFile() {
	AppointmentInteracter.add(testAppointment1, testAppointments);
	AppointmentInteracter.add(testAppointment2, testAppointments);
	AppointmentInteracter.add(testAppointment3, testAppointments);
    }

    @AfterClass
    public static void deleteCorrectTestFile() {
	testAppointments.delete();
    }

    @Test
    public void testGetAppointments() {
	List<Appointment> termine = AppointmentInteracter.getAppointments(testAppointments);
	assertTrue(termine.contains(testAppointment1));
	assertTrue(termine.contains(testAppointment2));
	assertTrue(termine.contains(testAppointment3));
	assertEquals(3, termine.size());
    }

    @Test
    public void testContains() {
	assertTrue(AppointmentInteracter.contains(testAppointment1, testAppointments));
	assertTrue(AppointmentInteracter.contains(testAppointment2, testAppointments));
	assertTrue(AppointmentInteracter.contains(testAppointment3, testAppointments));
	assertFalse(AppointmentInteracter.contains(new Appointment("Hello there", false), testAppointments));
    }

    @Test
    public void testAdd() {
	File testAdd = createEmptyTestFile("testAdd.json");
	AppointmentInteracter.add(testAppointment2, testAdd);
	assertTrue(AppointmentInteracter.contains(testAppointment2, testAdd));
	testAdd.delete();
    }

    @Test
    public void testRemove() {
	File testRemove = createEmptyTestFile("testRemove.json");
	AppointmentInteracter.add(testAppointment2, testRemove);
	AppointmentInteracter.remove(testAppointment2, testRemove);
	assertFalse(AppointmentInteracter.contains(testAppointment1, testRemove));
	testRemove.delete();
    }

    @Test
    public void testUpdateAppointment() {
	File testUpdate = createEmptyTestFile("testUpdate.json");
	AppointmentInteracter.add(testAppointment1, testUpdate);
	Appointment updated = new Appointment(testAppointment1.getDate(), "New Name", testAppointment1.getDescription(), true,
		testAppointment1.getID());
	AppointmentInteracter.updateAppointment(updated, testUpdate);
	List<Appointment> appointments = AppointmentInteracter.getAppointments(testUpdate);
	assertEquals(1, appointments.size());
	Appointment appointment = appointments.get(0);
	assertEquals(testAppointment1.getDate(), appointment.getDate());
	assertEquals("New Name", appointment.getName());
	assertEquals(testAppointment1.getDescription(), appointment.getDescription());
	assertTrue(appointment.isABirthday());
	assertEquals(testAppointment1.getID(), appointment.getID());
	testUpdate.delete();
    }

    private static File createEmptyTestFile(final String name) {
	try {
	    final File test = new File(name);
	    test.createNewFile();
	    BufferedWriter writer = new BufferedWriter(new FileWriter(test));
	    writer.write("[]");
	    writer.close();
	    return test;
	} catch (IOException e) {
	    fail("Couldn't create test-file with name: " + name);
	    throw new RuntimeException();
	}
    }

}