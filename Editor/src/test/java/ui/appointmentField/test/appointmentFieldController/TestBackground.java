package ui.appointmentField.test;

import static org.junit.Assert.*;
import java.awt.Color;
import java.io.File;
import org.junit.AfterClass;
import org.junit.Test;
import appointment.Appointment;
import appointment.InvalidAppointmentException;
import date.SimpleDate;
import fileInteraction.AppointmentFileInteracter;
import ui.appointmentField.AllAppointmentFields;
import ui.appointmentField.AppointmentFieldController;

/**
 * @author Gabriel Glaser
 * @version 09.02.2022
 */
public class TestAppointmentControllerBackground {

    static File testAppointmentFile = AppointmentFileInteracter.createEmptyAppointmentFile("src\\test\\resources\\TestAppointmentFieldControllerBackgroundAppointments.json");
    static AllAppointmentFields allAppointments = new AllAppointmentFields();
    static Color defaultBackground = Color.GRAY;

    static SimpleDate testDate = new SimpleDate(10, 10);
    static String testName = "Tim";
    static String testDescription = "Hello There";
    static boolean testIsBirthday = false;
    static Appointment testAppointment = new Appointment(testDate, testName, testDescription, testIsBirthday);

    @AfterClass
    public static void deleteTestFile() {
	testAppointmentFile.delete();
    }

    @Test
    public void testBackgroundOfNewAppointmentController() {
	AppointmentFieldController appointmentFieldController = new AppointmentFieldController(allAppointments);
	appointmentFieldController.setDefaultBackground(defaultBackground);
	assertTrue(appointmentFieldController.hasUnsavedBackground());
    }

    @Test
    public void testBackgroundAfterFirstNameChange() {
	AppointmentFieldController appointmentFieldController = new AppointmentFieldController(allAppointments);
	appointmentFieldController.setDefaultBackground(defaultBackground);
	appointmentFieldController.setName("TestName");
	assertTrue(appointmentFieldController.hasUnsavedBackground());
    }

    @Test
    public void testBackgroundOfAppointmentControllerWithInitialAppointment() {
	AppointmentFieldController appointmentFieldController = new AppointmentFieldController(allAppointments, testAppointment);
	appointmentFieldController.setDefaultBackground(defaultBackground);
	assertTrue(appointmentFieldController.hasDefaultBackground());
    }

    @Test
    public void testBackgroundOfAppointmentControllerWithInitialAppointmentAndChange() {
	AppointmentFieldController appointmentFieldController = new AppointmentFieldController(allAppointments, testAppointment);
	appointmentFieldController.setDefaultBackground(defaultBackground);
	assertTrue(appointmentFieldController.hasDefaultBackground());
	appointmentFieldController.setDate(new SimpleDate(12, 2));
	assertTrue(appointmentFieldController.hasUnsavedBackground());
    }

    @Test
    public void testBackgroundAfterSave() {
	try {
	    AppointmentFieldController appointmentFieldController = new AppointmentFieldController(allAppointments);
	    appointmentFieldController.setDefaultBackground(defaultBackground);
	    appointmentFieldController.setName("TestName");
	    appointmentFieldController.setDescription("Hello");
	    appointmentFieldController.save(testAppointmentFile);
	    assertTrue(appointmentFieldController.hasDefaultBackground());
	    appointmentFieldController.delete(testAppointmentFile);
	} catch (InvalidAppointmentException e) {
	    e.printStackTrace();
	    fail("Couldn't save Appointment");
	}
    }

    @Test
    public void testBackgroundAfterSaveWithInitialAppointment() {
	try {
	    AppointmentFileInteracter.add(testAppointment, testAppointmentFile);
	    AppointmentFieldController appointmentFieldController = new AppointmentFieldController(allAppointments, testAppointment);
	    appointmentFieldController.setDefaultBackground(defaultBackground);
	    appointmentFieldController.setName("TestName");
	    appointmentFieldController.setDescription("Hello");
	    appointmentFieldController.save(testAppointmentFile);
	    assertTrue(appointmentFieldController.hasDefaultBackground());
	} catch (InvalidAppointmentException e) {
	    e.printStackTrace();
	    fail("Couldn't save Appointment");
	}
    }
    
    @Test
    public void testBackgroundAfterCancelWithInitialAppointment() {
	AppointmentFieldController appointmentFieldController = new AppointmentFieldController(allAppointments, testAppointment);
	appointmentFieldController.setDefaultBackground(defaultBackground);
	appointmentFieldController.setName("Test123");
	appointmentFieldController.setDate(new SimpleDate(14, 5));
	appointmentFieldController.cancel();
	assertTrue(appointmentFieldController.hasDefaultBackground());
    }

}
