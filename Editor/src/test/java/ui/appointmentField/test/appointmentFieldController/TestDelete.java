package ui.appointmentField.test.appointmentFieldController;

import static org.junit.Assert.*;
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
 * @version 08.02.2022
 */
public class TestDelete {

    static File testAppointmentFile = AppointmentFileInteracter.createEmptyAppointmentFile("src\\test\\resources\\testDeleteAppointmentFile.json");
    static AllAppointmentFields allAppointments = new AllAppointmentFields();

    static SimpleDate testDate = new SimpleDate(10, 10);
    static String testName = "Tim";
    static String testDescription = "Hello There";
    static boolean testIsBirthday = false;
    static Appointment testAppointment = new Appointment(testDate, testName, testDescription, testIsBirthday);

    /**
     * Should throw no Exception, should just do nothing.
     */
    @Test
    public void testDeleteDirectly() {
	AppointmentFieldController appointmentFieldController = new AppointmentFieldController(allAppointments);
	appointmentFieldController.delete();
    }

    /**
     * Should throw no Exception, should just do nothing.
     */
    @Test
    public void testDeleteWithoutSave() {
	AppointmentFieldController appointmentFieldController = new AppointmentFieldController(allAppointments);
	appointmentFieldController.setName("Turkey");
	appointmentFieldController.setDate(new SimpleDate(8, 1));
	appointmentFieldController.delete();
    }

    @Test
    public void testDeleteWithSave() {
	try {
	    AppointmentFieldController appointmentFieldController = new AppointmentFieldController(allAppointments);
	    appointmentFieldController.setName("Turkey");
	    appointmentFieldController.setDate(new SimpleDate(8, 1));
	    Appointment currentInput = appointmentFieldController.getInputAppointment();
	    appointmentFieldController.save(testAppointmentFile);
	    assertTrue(AppointmentFileInteracter.contains(currentInput, testAppointmentFile));
	    appointmentFieldController.delete(testAppointmentFile);
	    assertFalse(AppointmentFileInteracter.contains(currentInput, testAppointmentFile));
	} catch (final InvalidAppointmentException e) {
	    e.printStackTrace();
	    fail("");
	}
    }

    @Test
    public void testDeleteWithoutSaveButInitialAppointment() {
	AppointmentFileInteracter.add(testAppointment, testAppointmentFile);
	AppointmentFieldController appointmentFieldController = new AppointmentFieldController(allAppointments, testAppointment);
	appointmentFieldController.delete(testAppointmentFile);
	assertFalse(AppointmentFileInteracter.contains(testAppointment, testAppointmentFile));
    }

    @AfterClass
    public static void deleteTestFile() {
	testAppointmentFile.delete();
    }

}
