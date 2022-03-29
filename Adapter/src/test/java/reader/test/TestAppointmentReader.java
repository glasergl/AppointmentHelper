package reader.test;

import static org.junit.Assert.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import appointment.Appointment;
import reader.AppointmentReader;
import reader.IllegalFileFormatException;
import simpleDate.SimpleDate;

/**
 * @author Gabriel Glaser
 * @version 29.03.2022
 */
public class TestAppointmentReader {

    @Test
    public void testReaderWithCorrectFile() {
	File testBirthdayFile = createTestGeburtstageFile("testReaderWithCorrectFile_Geburtstage.txt");
	addCorrectFormatText(testBirthdayFile);
	AppointmentReader appointmentReader = new AppointmentReader(testBirthdayFile);
	List<Appointment> appointments = appointmentReader.getAdaptedAppointments();
	assertTrue(appointments.contains(new Appointment(new SimpleDate(12, 2), "TestPerson1", "", true)));
	assertTrue(appointments.contains(new Appointment(new SimpleDate(5, 8), "TestPerson2", "", true)));
	assertEquals(2, appointments.size());
	testBirthdayFile.delete();
    }

    @Test
    public void testReaderWithEmptyFile() {
	File emptyTestBirthdayFile = createTestGeburtstageFile("testReaderWithEmptyFile_Geburtstage.txt");
	AppointmentReader appointmentReader = new AppointmentReader(emptyTestBirthdayFile);
	List<Appointment> appointments = appointmentReader.getAdaptedAppointments();
	assertEquals(0, appointments.size());
	emptyTestBirthdayFile.delete();
    }

    @Test
    public void testReaderWithFileWithIllegalFormat1() {
	File testBirthdayFileWithWrongFormat = createTestGeburtstageFile("testReaderWithFileWithIllegalFormat1_Geburtstage.txt");
	addWrongFormatText1(testBirthdayFileWithWrongFormat);
	assertThrows(IllegalFileFormatException.class, () -> {
	    new AppointmentReader(testBirthdayFileWithWrongFormat);
	});
	testBirthdayFileWithWrongFormat.delete();
    }

    @Test
    public void testReaderWithFileWithIllegalFormat2() {
	File testBirthdayFileWithWrongFormat = new File("testReaderWithFileWithIllegalFormat2_Geburtstage.txt");
	addWrongFormatText2(testBirthdayFileWithWrongFormat);
	assertThrows(IllegalFileFormatException.class, () -> {
	    new AppointmentReader(testBirthdayFileWithWrongFormat);
	});
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

    private File createTestGeburtstageFile(final String name) {
	try {
	    final File testGeburtstage = new File("src\\test\\resources\\" + name);
	    testGeburtstage.createNewFile();
	    return testGeburtstage;
	} catch (final IOException exception) {
	    fail("Couldn't create test Geburtstage.txt with name " + name);
	    throw new RuntimeException();
	}
    }

}
