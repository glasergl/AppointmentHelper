package ui.test.appointmentField.appointmentFieldController;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Test;

import appointment.Appointment;
import appointment.InvalidAppointmentException;
import appointmentField.AllAppointmentFields;
import appointmentField.AppointmentFieldController;
import fileInteraction.AppointmentFileInteracter;
import simpleDate.SimpleDate;
import ui.test.appointmentField.Tests;

/**
 * @author Gabriel Glaser
 * @version 12.03.2022
 */
public class TestDelete {

    AllAppointmentFields allAppointmentFieldsStub = new AllAppointmentFields();

    @Test
    public void testDeleteDirectly() {
	try {
	    AppointmentFieldController appointmentFieldController = new AppointmentFieldController(allAppointmentFieldsStub);
	    appointmentFieldController.delete();
	} catch (final Throwable e) {
	    fail("Caught an exception which was not expected");
	}
    }

    /**
     * Should throw no Exception, should just do nothing.
     */
    @Test
    public void testDeleteWithoutSave() {
	AppointmentFieldController appointmentFieldController = new AppointmentFieldController(allAppointmentFieldsStub);
	appointmentFieldController.setName("Turkey");
	appointmentFieldController.setDate(new SimpleDate(8, 1));
	appointmentFieldController.delete();
    }

    @Test
    public void testDeleteWithSave() {
	try {
	    File testAppointmentFile = Tests.createTestAppointmentFile("TestDeleteWithSaveAppointments.json");
	    AppointmentFieldController appointmentFieldController = new AppointmentFieldController(allAppointmentFieldsStub);
	    appointmentFieldController.setName("Turkey");
	    appointmentFieldController.setDate(new SimpleDate(8, 1));
	    Appointment currentInput = appointmentFieldController.getAppointment();
	    appointmentFieldController.save(testAppointmentFile);
	    assertTrue(AppointmentFileInteracter.contains(currentInput, testAppointmentFile));
	    appointmentFieldController.delete(testAppointmentFile);
	    assertFalse(AppointmentFileInteracter.contains(currentInput, testAppointmentFile));
	    testAppointmentFile.delete();
	} catch (final InvalidAppointmentException e) {
	    e.printStackTrace();
	    fail("");
	}
    }

    @Test
    public void testDeleteWithoutSaveButInitialAppointment() {
	File testAppointmentFile = Tests.createTestAppointmentFile("TestDeleteWithoutSaveButInitialAppointmentAppointments.json");
	AppointmentFileInteracter.add(Tests.testAppointment1, testAppointmentFile);
	AppointmentFieldController appointmentFieldController = new AppointmentFieldController(allAppointmentFieldsStub, Tests.testAppointment1);
	appointmentFieldController.delete(testAppointmentFile);
	assertFalse(AppointmentFileInteracter.contains(Tests.testAppointment1, testAppointmentFile));
	testAppointmentFile.delete();
    }

}
