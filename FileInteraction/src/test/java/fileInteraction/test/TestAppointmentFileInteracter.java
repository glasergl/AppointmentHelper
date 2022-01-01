package fileInteraction.test;

import static org.junit.Assert.*;
import org.junit.*;
import appointment.Appointment;
import date.SimpleDate;
import fileInteraction.AppointmentFileInteracter;
import java.io.File;
import java.util.List;

public class TestAppointmentFileInteracter {

    static String testPath = "src\\test\\resources";
    static File testAppointments = createEmptyTestFile("correctAppointments.json");

    static Appointment testAppointment1 = new Appointment(new SimpleDate(10, 2), "TestPerson1", "Description1", false);
    static Appointment testAppointment2 = new Appointment(new SimpleDate(7, 11), "TestPerson2", "Description2", true);
    static Appointment testAppointment3WithEmptyDescription = new Appointment(new SimpleDate(8, 8), "TestPerson3", "", false);

    @BeforeClass
    public static void setupCorrectTestFile() {
	AppointmentFileInteracter.add(testAppointment1, testAppointments);
	AppointmentFileInteracter.add(testAppointment2, testAppointments);
	AppointmentFileInteracter.add(testAppointment3WithEmptyDescription, testAppointments);
    }

    @AfterClass
    public static void deleteCorrectTestFile() {
	testAppointments.delete();
    }

    @Test
    public void testAdd() {
	File testAdd = createEmptyTestFile("testAdd.json");
	AppointmentFileInteracter.add(testAppointment2, testAdd);
	assertTrue(AppointmentFileInteracter.contains(testAppointment2, testAdd));
	testAdd.delete();
    }

    @Test
    public void testRemove() {
	File testRemove = createEmptyTestFile("testRemove.json");
	AppointmentFileInteracter.add(testAppointment2, testRemove);
	AppointmentFileInteracter.remove(testAppointment2, testRemove);
	assertFalse(AppointmentFileInteracter.contains(testAppointment1, testRemove));
	testRemove.delete();
    }

    @Test
    public void testContains() {
	assertTrue(AppointmentFileInteracter.contains(testAppointment1, testAppointments));
	assertTrue(AppointmentFileInteracter.contains(testAppointment2, testAppointments));
	assertTrue(AppointmentFileInteracter.contains(testAppointment3WithEmptyDescription, testAppointments));
	Appointment appointment = new Appointment("Hello there", false);
	assertFalse(AppointmentFileInteracter.contains(appointment, testAppointments));
    }

    @Test
    public void testGetAppointments() {
	List<Appointment> termine = AppointmentFileInteracter.getAppointments(testAppointments);
	assertTrue(termine.contains(testAppointment1));
	assertTrue(termine.contains(testAppointment2));
	assertTrue(termine.contains(testAppointment3WithEmptyDescription));
	assertEquals(3, termine.size());
    }

    private static File createEmptyTestFile(final String name) {
	return AppointmentFileInteracter.createAppointmentFile(testPath + name);
    }

}
