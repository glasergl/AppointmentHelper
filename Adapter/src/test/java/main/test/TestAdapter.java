package main.test;

import static org.junit.Assert.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import appointment.Appointment;
import date.SimpleDate;
import main.AppointmentReader;
import main.IllegalFileFormatException;

public class TestAdapter {

    static File birthdayFile = new File("birthdays.txt");

    @BeforeClass
    public static void addBirthdaysToBirthdayFile() {
	try (BufferedWriter writer = new BufferedWriter(new FileWriter(birthdayFile))) {
	    writer.write("TestPerson1.12.2");
	    writer.newLine();
	    writer.write("# test");
	    writer.newLine();
	    writer.newLine();
	    writer.write("TestPerson2.5.8");
	} catch (IOException e) {
	    e.printStackTrace();
	    fail("Couldn't write to Test-Birthday-File");
	}
    }

    @AfterClass
    public static void deleteBirthdayFile() {
	birthdayFile.delete();
    }

    @Test
    public void testReader() {
	try {
	    AppointmentReader appointmentReader = new AppointmentReader(birthdayFile);
	    List<Appointment> appointments = appointmentReader.getAdaptedAppointments();
	    assertTrue(appointments.contains(new Appointment(new SimpleDate(12, 2), "TestPerson1", "", true)));
	    assertTrue(appointments.contains(new Appointment(new SimpleDate(5, 8), "TestPerson2", "", true)));
	    assertEquals(2, appointments.size());
	} catch (final IllegalFileFormatException e) {
	    fail("The test file with the birthdays doesn't have the correct format.");
	}
    }

}
