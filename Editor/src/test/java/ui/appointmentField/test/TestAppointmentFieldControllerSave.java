package ui.appointmentField.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.AfterClass;
import org.junit.Test;
import appointment.Appointment;
import appointment.InvalidAppointmentException;
import date.SimpleDate;
import fileInteraction.AppointmentFileInteracter;
import ui.appointmentField.AppointmentFieldController;
import ui.appointmentField.AllAppointmentFields;

/**
 * @author Gabriel Glaser
 * @version 8.2.2022
 */
public class TestAppointmentFieldControllerSave {

    static File testAppointmentFile = AppointmentFileInteracter.createEmptyAppointmentFile("src\\test\\resources\\TestAppointmentFieldControllerAppointments.json");
    static AllAppointmentFields allAppointments = new AllAppointmentFields();

    static SimpleDate testDate = new SimpleDate(10, 10);
    static String testName = "Tim";
    static String testDescription = "Hello There";
    static boolean testIsBirthday = false;
    static Appointment testAppointment = new Appointment(testDate, testName, testDescription, testIsBirthday);

    @AfterClass
    public static void setupTestAppointmentFile() {
	testAppointmentFile.delete();
    }

    @Test
    public void testIsSavedWithoutInitialAppointment() {
	AppointmentFieldController testWithoutInitialAppointment = new AppointmentFieldController(allAppointments);
	assertFalse(testWithoutInitialAppointment.isSaved());
    }

    @Test
    public void testIsSavedWithInitialAppointment() {
	AppointmentFieldController testWithInitialAppointment = new AppointmentFieldController(allAppointments, testAppointment);
	assertTrue(testWithInitialAppointment.isSaved());
    }

    @Test
    public void testSavedStateWithChangeDate() {
	AppointmentFieldController testWithInitialAppointment = new AppointmentFieldController(allAppointments, testAppointment);
	testWithInitialAppointment.setDate(new SimpleDate(15, 10));
	assertFalse(testWithInitialAppointment.isSaved());
	testWithInitialAppointment.setDate(testDate);
	assertTrue(testWithInitialAppointment.isSaved());
    }

    @Test
    public void testSavedStateWithChangeName() {
	AppointmentFieldController testWithInitialAppointment = new AppointmentFieldController(allAppointments, testAppointment);
	testWithInitialAppointment.setName("Sam");
	assertFalse(testWithInitialAppointment.isSaved());
	testWithInitialAppointment.setName(testName);
	assertTrue(testWithInitialAppointment.isSaved());
    }

    @Test
    public void testSavedStateWithDescription() {
	AppointmentFieldController testWithInitialAppointment = new AppointmentFieldController(allAppointments, testAppointment);
	testWithInitialAppointment.setDescription("I need to get a cake!");
	assertFalse(testWithInitialAppointment.isSaved());
	testWithInitialAppointment.setDescription(testDescription);
	assertTrue(testWithInitialAppointment.isSaved());
    }

    @Test
    public void testSavedStateWithChangeIsBirthday() {
	AppointmentFieldController testWithInitialAppointment = new AppointmentFieldController(allAppointments, testAppointment);
	testWithInitialAppointment.setIsBirthday(!testIsBirthday);
	assertFalse(testWithInitialAppointment.isSaved());
	testWithInitialAppointment.setIsBirthday(testIsBirthday);
	assertTrue(testWithInitialAppointment.isSaved());
    }

    @Test
    public void testSaveNormalCase() {
	try {
	    AppointmentFieldController appointmentFieldController = new AppointmentFieldController(allAppointments);
	    appointmentFieldController.setDate(new SimpleDate(12, 2));
	    appointmentFieldController.setName("Peter");
	    appointmentFieldController.setDescription("new computer");
	    appointmentFieldController.setIsBirthday(true);
	    appointmentFieldController.save(testAppointmentFile);
	    assertTrue(AppointmentFileInteracter.contains(appointmentFieldController.getAppointment(), testAppointmentFile));
	} catch (InvalidAppointmentException e) {
	    e.printStackTrace();
	    fail("Wasn't able to save Appointment.");
	}
    }

    @Test(expected = InvalidAppointmentException.class)
    public void testExceptionOnSaveOfAppointmentWithEmptyName() throws InvalidAppointmentException {
	AppointmentFieldController testWithoutInitialAppointment = new AppointmentFieldController(allAppointments);
	testWithoutInitialAppointment.save(testAppointmentFile);
    }

    @Test(expected = InvalidAppointmentException.class)
    public void testExceptionOnSaveOfAppointmentWithInitialAppointment() throws InvalidAppointmentException {
	AppointmentFieldController testWithInitialAppointment = new AppointmentFieldController(allAppointments, testAppointment);
	testWithInitialAppointment.setName("");
	testWithInitialAppointment.save(testAppointmentFile);
    }

}
