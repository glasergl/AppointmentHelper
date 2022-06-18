package de.glasergl.ui.test.appointmentField.appointmentFieldController;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Color;
import java.io.File;

import org.junit.Test;

import de.glasergl.appointment.InvalidAppointmentException;
import de.glasergl.appointmentField.AllAppointmentFields;
import de.glasergl.appointmentField.AppointmentFieldController;
import de.glasergl.configuration.ConfigurationHandler;
import de.glasergl.simpleDate.SimpleDate;
import de.glasergl.ui.test.appointmentField.Tests;

/**
 * @author Gabriel Glaser
 * @version 12.03.2022
 */
public class TestBackground {

    AllAppointmentFields allAppointmentFieldsStub = new AllAppointmentFields();
    Color testColor = Color.GRAY;

    @Test
    public void testBackgroundOfNewAppointmentController() {
	AppointmentFieldController appointmentFieldController = new AppointmentFieldController(allAppointmentFieldsStub);
	appointmentFieldController.setDefaultBackground(testColor);
	assertTrue(appointmentFieldController.hasUnsavedBackground());
    }

    @Test
    public void testBackgroundAfterFirstNameChange() {
	AppointmentFieldController appointmentFieldController = new AppointmentFieldController(allAppointmentFieldsStub);
	appointmentFieldController.setDefaultBackground(testColor);
	appointmentFieldController.setName("TestName");
	assertTrue(appointmentFieldController.hasUnsavedBackground());
    }

    @Test
    public void testBackgroundOfAppointmentControllerWithInitialAppointment() {
	AppointmentFieldController appointmentFieldController = new AppointmentFieldController(allAppointmentFieldsStub, Tests.testAppointment1);
	appointmentFieldController.setDefaultBackground(testColor);
	assertTrue(appointmentFieldController.hasDefaultBackground());
    }

    @Test
    public void testBackgroundOfAppointmentControllerWithInitialAppointmentAndChange() {
	AppointmentFieldController appointmentFieldController = new AppointmentFieldController(allAppointmentFieldsStub, Tests.testAppointment1);
	appointmentFieldController.setDefaultBackground(testColor);
	assertTrue(appointmentFieldController.hasDefaultBackground());
	appointmentFieldController.setDate(new SimpleDate(12, 2));
	assertTrue(appointmentFieldController.hasUnsavedBackground());
    }

    @Test
    public void testBackgroundAfterSave() {
	try {
	    File testAppointmentFile = Tests.createTestAppointmentFile("TestBackgroundAfterSaveAppointments.json");
	    AppointmentFieldController appointmentFieldController = new AppointmentFieldController(allAppointmentFieldsStub);
	    appointmentFieldController.setDefaultBackground(testColor);
	    appointmentFieldController.setName("TestName");
	    appointmentFieldController.setDescription("Hello");
	    appointmentFieldController.save(testAppointmentFile);
	    assertTrue(appointmentFieldController.hasDefaultBackground());
	    appointmentFieldController.delete(testAppointmentFile);
	    testAppointmentFile.delete();
	} catch (InvalidAppointmentException e) {
	    e.printStackTrace();
	    fail("Couldn't save Appointment");
	}
    }

    @Test
    public void testBackgroundAfterSaveWithInitialAppointment() {
	try {
	    File testAppointmentFile = Tests.createTestAppointmentFile("TestBackgroundAfterSaveWithInitialAppointmentAppointments.json");
	    ConfigurationHandler.add(Tests.testAppointment1, testAppointmentFile);
	    AppointmentFieldController appointmentFieldController = new AppointmentFieldController(allAppointmentFieldsStub, Tests.testAppointment1);
	    appointmentFieldController.setDefaultBackground(testColor);
	    appointmentFieldController.setName("TestName");
	    appointmentFieldController.setDescription("Hello");
	    appointmentFieldController.save(testAppointmentFile);
	    assertTrue(appointmentFieldController.hasDefaultBackground());
	    testAppointmentFile.delete();
	} catch (InvalidAppointmentException e) {
	    e.printStackTrace();
	    fail("Couldn't save Appointment");
	}
    }

    @Test
    public void testBackgroundAfterCancelWithInitialAppointment() {
	AppointmentFieldController appointmentFieldController = new AppointmentFieldController(allAppointmentFieldsStub, Tests.testAppointment1);
	appointmentFieldController.setDefaultBackground(testColor);
	appointmentFieldController.setName("Test123");
	appointmentFieldController.setDate(new SimpleDate(14, 5));
	appointmentFieldController.cancel();
	assertTrue(appointmentFieldController.hasDefaultBackground());
    }

}
