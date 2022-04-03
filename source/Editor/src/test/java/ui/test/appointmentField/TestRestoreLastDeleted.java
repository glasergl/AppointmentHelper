package ui.test.appointmentField;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;

import appointmentField.AllAppointmentFields;
import appointmentField.AppointmentFieldController;

/**
 * @author Gabriel Glaser
 * @version 11.03.2022
 */
public class TestRestoreLastDeleted {

    @Test
    public void testWithEmpty() {
	AllAppointmentFields testAppointmentFields = new AllAppointmentFields();
	try {
	    testAppointmentFields.restoreLastDeletedAppointmentField();
	} catch (final Throwable e) {
	    fail("Threw " + e.getClass().getName() + " which wasn't expected.");
	}
    }

    @Test
    public void testWithOne() {
	AllAppointmentFields testAppointmentFields = new AllAppointmentFields();
	testAppointmentFields.addEmptyAppointmentField();
	final AppointmentFieldController appointmentFieldController = testAppointmentFields.getAppointmentFields().get(0);
	Tests.initializeAppointmentFieldController(appointmentFieldController, Tests.testAppointment1);
	assertTrue(testAppointmentFields.containsAppointmentAsInput(Tests.testAppointment1));
	appointmentFieldController.delete();
	assertFalse(testAppointmentFields.containsAppointmentAsInput(Tests.testAppointment1));
	testAppointmentFields.restoreLastDeletedAppointmentField();
	assertTrue(testAppointmentFields.containsAppointmentAsInput(Tests.testAppointment1));
    }

    @Test
    public void testWithTwo() {
	AllAppointmentFields testAppointmentFields = new AllAppointmentFields();
	testAppointmentFields.addEmptyAppointmentField();
	testAppointmentFields.addEmptyAppointmentField();
	final List<AppointmentFieldController> allAppointmentFieldController = testAppointmentFields.getAppointmentFields();
	final AppointmentFieldController first = allAppointmentFieldController.get(0);
	final AppointmentFieldController second = allAppointmentFieldController.get(1);
	Tests.initializeAppointmentFieldController(first, Tests.testAppointment1);
	Tests.initializeAppointmentFieldController(second, Tests.testAppointment2);
	assertTrue(testAppointmentFields.containsAppointmentAsInput(Tests.testAppointment1));
	assertTrue(testAppointmentFields.containsAppointmentAsInput(Tests.testAppointment2));
	first.delete();
	second.delete();
	assertFalse(testAppointmentFields.containsAppointmentAsInput(Tests.testAppointment1));
	assertFalse(testAppointmentFields.containsAppointmentAsInput(Tests.testAppointment2));
	testAppointmentFields.restoreLastDeletedAppointmentField();
	assertFalse(testAppointmentFields.containsAppointmentAsInput(Tests.testAppointment1));
	assertTrue(testAppointmentFields.containsAppointmentAsInput(Tests.testAppointment2));
	testAppointmentFields.restoreLastDeletedAppointmentField();
	assertTrue(testAppointmentFields.containsAppointmentAsInput(Tests.testAppointment1));
	assertTrue(testAppointmentFields.containsAppointmentAsInput(Tests.testAppointment2));
    }

}
