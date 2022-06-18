package de.glasergl.ui.test.appointmentField.appointmentFieldController;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
 * @version 12.3.2022
 */
public class TestSave {

    AllAppointmentFields allAppointmentFieldsStub = new AllAppointmentFields();

    @Test
    public void testIsSavedWithoutInitialAppointment() {
	AppointmentFieldController testWithoutInitialAppointment = new AppointmentFieldController(allAppointmentFieldsStub);
	assertFalse(testWithoutInitialAppointment.isSaved());
    }

    @Test
    public void testIsSavedWithInitialAppointment() {
	AppointmentFieldController testWithInitialAppointment = new AppointmentFieldController(allAppointmentFieldsStub, Tests.testAppointment1);
	assertTrue(testWithInitialAppointment.isSaved());
    }

    @Test
    public void testSavedStateWithChangeDate() {
	AppointmentFieldController testWithInitialAppointment = new AppointmentFieldController(allAppointmentFieldsStub, Tests.testAppointment1);
	testWithInitialAppointment.setDate(new SimpleDate(15, 10));
	assertFalse(testWithInitialAppointment.isSaved());
	testWithInitialAppointment.setDate(Tests.testAppointment1.getDate());
	assertTrue(testWithInitialAppointment.isSaved());
    }

    @Test
    public void testSavedStateWithChangeName() {
	AppointmentFieldController testWithInitialAppointment = new AppointmentFieldController(allAppointmentFieldsStub, Tests.testAppointment1);
	testWithInitialAppointment.setName("Sam");
	assertFalse(testWithInitialAppointment.isSaved());
	testWithInitialAppointment.setName(Tests.testAppointment1.getName());
	assertTrue(testWithInitialAppointment.isSaved());
    }

    @Test
    public void testSavedStateWithDescription() {
	AppointmentFieldController testWithInitialAppointment = new AppointmentFieldController(allAppointmentFieldsStub, Tests.testAppointment1);
	testWithInitialAppointment.setDescription("I need to get a cake!");
	assertFalse(testWithInitialAppointment.isSaved());
	testWithInitialAppointment.setDescription(Tests.testAppointment1.getDescription());
	assertTrue(testWithInitialAppointment.isSaved());
    }

    @Test
    public void testSavedStateWithChangeIsBirthday() {
	AppointmentFieldController testWithInitialAppointment = new AppointmentFieldController(allAppointmentFieldsStub, Tests.testAppointment1);
	testWithInitialAppointment.setIsBirthday(!Tests.testAppointment1.isBirthday());
	assertFalse(testWithInitialAppointment.isSaved());
	testWithInitialAppointment.setIsBirthday(Tests.testAppointment1.isBirthday());
	assertTrue(testWithInitialAppointment.isSaved());
    }

    @Test
    public void testSaveNormalCase() {
	try {
	    File testAppointmentFile = Tests.createTestAppointmentFile("TestSaveNormalCaseAppointments.json");
	    AppointmentFieldController appointmentFieldController = new AppointmentFieldController(allAppointmentFieldsStub);
	    appointmentFieldController.setDate(new SimpleDate(12, 2));
	    appointmentFieldController.setName("Peter");
	    appointmentFieldController.setDescription("new computer");
	    appointmentFieldController.setIsBirthday(true);
	    appointmentFieldController.save(testAppointmentFile);
	    assertTrue(ConfigurationHandler.contains(appointmentFieldController.getAppointment(), testAppointmentFile));
	    testAppointmentFile.delete();
	} catch (InvalidAppointmentException e) {
	    e.printStackTrace();
	    fail("Wasn't able to save Appointment.");
	}
    }

    @Test
    public void testExceptionOnSaveOfAppointmentWithEmptyName() throws InvalidAppointmentException {
	File testAppointmentFile = Tests.createTestAppointmentFile("TestExceptionOnSaveOfAppointmentWithEmptyNameAppointments.json");
	AppointmentFieldController testWithoutInitialAppointment = new AppointmentFieldController(allAppointmentFieldsStub);
	assertThrows(InvalidAppointmentException.class, () -> {
	    testWithoutInitialAppointment.save(testAppointmentFile);
	});
	testAppointmentFile.delete();
    }

    @Test
    public void testExceptionOnSaveOfAppointmentWithInitialAppointment() throws InvalidAppointmentException {
	File testAppointmentFile = Tests.createTestAppointmentFile("TestExceptionOnSaveOfAppointmentWithInitialAppointmentAppointments.json");
	AppointmentFieldController testWithInitialAppointment = new AppointmentFieldController(allAppointmentFieldsStub, Tests.testAppointment1);
	testWithInitialAppointment.setName("");
	assertThrows(InvalidAppointmentException.class, () -> {
	    testWithInitialAppointment.save(testAppointmentFile);
	});
	testAppointmentFile.delete();
    }

}
