package ui.appointmentField.test;

import static org.junit.Assert.*;
import org.junit.Test;
import appointment.InvalidAppointmentException;
import ui.appointmentField.AllAppointmentFields;
import ui.appointmentField.AppointmentFieldController;

public class TestAllAppointmentFieldsRestoreDeleted {

    static AllAppointmentFields testAppointmentFields = new AllAppointmentFields();

    /**
     * Shouldn't throw Exception.
     */
    @Test
    public void testWithOneEmpty() {
	AppointmentFieldController appointmentFieldController = new AppointmentFieldController(testAppointmentFields);
	testAppointmentFields.add(appointmentFieldController);
	testAppointmentFields.restoreLastDeletedAppointmentField();
    }

}
