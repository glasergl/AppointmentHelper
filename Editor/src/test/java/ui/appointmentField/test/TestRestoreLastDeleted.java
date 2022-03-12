package ui.appointmentField.test;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import appointment.Appointment;
import date.SimpleDate;
import ui.appointmentField.AllAppointmentFields;
import ui.appointmentField.AppointmentFieldController;

/**
 * @author Gabriel Glaser
 * @version 11.03.2022
 */
public class TestRestoreLastDeleted {

    static AllAppointmentFields testAppointmentFields = new AllAppointmentFields();
    static Appointment testAppointment1 = new Appointment(new SimpleDate(10, 2), "Tom", "hello there", true);
    static Appointment testAppointment2 = new Appointment(new SimpleDate(7, 10), "Freddyyy", "siuuuuu", false);

    /**
     * Shouldn't throw Exception.
     */
    @Test
    public void testWithEmpty() {
	testAppointmentFields.restoreLastDeletedAppointmentField();
    }

    @Test
    public void testWithOne() {
	testAppointmentFields.addEmptyAppointmentField();
	final AppointmentFieldController appointmentFieldController = testAppointmentFields.getAppointmentFields().get(0);
	initializeAppointmentFieldController(appointmentFieldController, testAppointment1);
	assertTrue(testAppointmentFields.containsAppointmentAsInput(testAppointment1));
	appointmentFieldController.delete();
	assertFalse(testAppointmentFields.containsAppointmentAsInput(testAppointment1));
	testAppointmentFields.restoreLastDeletedAppointmentField();
	assertTrue(testAppointmentFields.containsAppointmentAsInput(testAppointment1));
	appointmentFieldController.delete();
    }

    @Test
    public void testWithTwo() {
	testAppointmentFields.addEmptyAppointmentField();
	testAppointmentFields.addEmptyAppointmentField();
	final List<AppointmentFieldController> allAppointmentFieldController = testAppointmentFields.getAppointmentFields();
	final AppointmentFieldController first = allAppointmentFieldController.get(0);
	final AppointmentFieldController second = allAppointmentFieldController.get(1);
	initializeAppointmentFieldController(first, testAppointment1);
	initializeAppointmentFieldController(second, testAppointment2);
	assertTrue(testAppointmentFields.containsAppointmentAsInput(testAppointment1));
	assertTrue(testAppointmentFields.containsAppointmentAsInput(testAppointment2));
	first.delete();
	second.delete();
	assertFalse(testAppointmentFields.containsAppointmentAsInput(testAppointment1));
	assertFalse(testAppointmentFields.containsAppointmentAsInput(testAppointment2));
	testAppointmentFields.restoreLastDeletedAppointmentField();
	assertFalse(testAppointmentFields.containsAppointmentAsInput(testAppointment1));
	assertTrue(testAppointmentFields.containsAppointmentAsInput(testAppointment2));
	testAppointmentFields.restoreLastDeletedAppointmentField();
	assertTrue(testAppointmentFields.containsAppointmentAsInput(testAppointment1));
	assertTrue(testAppointmentFields.containsAppointmentAsInput(testAppointment2));
	first.delete();
	second.delete();
    }

    private void initializeAppointmentFieldController(final AppointmentFieldController toInitialize, final Appointment content) {
	toInitialize.setDate(content.getDate());
	toInitialize.setName(content.getName());
	toInitialize.setDescription(content.getDescription());
	toInitialize.setIsBirthday(content.isBirthday());
    }

}
