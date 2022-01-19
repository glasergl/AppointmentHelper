package main.test;

import static org.junit.Assert.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import appointment.Appointment;
import date.SimpleDate;
import main.AppointmentReader;
import main.IllegalFileFormatException;

/**
 * @author Gabriel Glaser
 * @version 19.01.2022
 */
public class TestAdapter {

    @Test
    public void testReaderWithCorrectFile() {
	File testBirthdayFile = new File("TestGeburtstage.txt");
	addCorrectFormatText(testBirthdayFile);
	try {
	    AppointmentReader appointmentReader = new AppointmentReader(testBirthdayFile);
	    List<Appointment> appointments = appointmentReader.getAdaptedAppointments();
	    assertTrue(appointments.contains(new Appointment(new SimpleDate(12, 2), "TestPerson1", "", true)));
	    assertTrue(appointments.contains(new Appointment(new SimpleDate(5, 8), "TestPerson2", "", true)));
	    assertEquals(2, appointments.size());
	} catch (IllegalFileFormatException e) {
	    fail("The test file with the birthdays doesn't have the correct format, but should have.");
	} finally {
	    testBirthdayFile.delete();
	}
    }

    @Test
    public void testReaderWithEmptyFile() {
	File emptyTestBirthdayFile = new File("TestGeburtstage.txt");
	try {
	    emptyTestBirthdayFile.createNewFile();
	    AppointmentReader appointmentReader = new AppointmentReader(emptyTestBirthdayFile);
	    List<Appointment> appointments = appointmentReader.getAdaptedAppointments();
	    assertEquals(0, appointments.size());
	} catch (IOException e) {
	    fail("Couldn't create an empty Test-Geburtstage File.");
	} catch (IllegalFileFormatException e) {
	    fail("The test file with the birthdays doesn't have the correct format, but should have.");
	} finally {
	    emptyTestBirthdayFile.delete();
	}

    }

    @Test(expected = IllegalFileFormatException.class)
    public void testReaderWithFileWithIllegalFormat1() throws IllegalFileFormatException {
	File testBirthdayFileWithWrongFormat = new File("TestGeburtstage.txt");
	addWrongFormatText1(testBirthdayFileWithWrongFormat);
	new AppointmentReader(testBirthdayFileWithWrongFormat);
	testBirthdayFileWithWrongFormat.delete();
    }

    @Test(expected = IllegalFileFormatException.class)
    public void testReaderWithFileWithIllegalFormat2() throws IllegalFileFormatException {
	File testBirthdayFileWithWrongFormat = new File("TestGeburtstage.txt");
	addWrongFormatText2(testBirthdayFileWithWrongFormat);
	new AppointmentReader(testBirthdayFileWithWrongFormat);
	testBirthdayFileWithWrongFormat.delete();
    }

    /**
     * Adds a correct version of the old version to the given File.
     */
    private void addCorrectFormatText(final File fileToAddTextTo) {
	try (final BufferedWriter writer = new BufferedWriter(new FileWriter(fileToAddTextTo))) {
	    writer.write("TestPerson1.12.2");
	    writer.newLine();
	    writer.write("# test");
	    writer.newLine();
	    writer.newLine();
	    writer.write("TestPerson2.5.8");
	} catch (final IOException e) {
	    fail("Couldn't write to correct Test-Birthday-File");
	}
    }

    /**
     * Writes a random String which should not be able to be interpreted.
     */
    private void addWrongFormatText1(final File fileToAddTextTo) {
	try (final BufferedWriter writer = new BufferedWriter(new FileWriter(fileToAddTextTo))) {
	    writer.write("TestPerson1.12.2");
	    writer.newLine();
	    writer.write("# test");
	    writer.newLine();
	    writer.newLine();
	    writer.write("TestPerson2.5.8");
	    writer.newLine();
	    writer.write("dsafdsfaawaw");
	} catch (final IOException e) {
	    fail("Couldn't write to wrong Test-Birthday-File1");
	}
    }

    /**
     * Writes the 31.2 as date of a test person.
     */
    private void addWrongFormatText2(final File fileToAddTextTo) {
	try (final BufferedWriter writer = new BufferedWriter(new FileWriter(fileToAddTextTo))) {
	    writer.write("TestPerson1.31.2");
	    writer.newLine();
	    writer.write("# test");
	    writer.newLine();
	    writer.newLine();
	    writer.write("TestPerson2.5.8");
	} catch (final IOException e) {
	    fail("Couldn't write to wrong Test-Birthday-File2");
	}
    }

}
