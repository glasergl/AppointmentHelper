package de.glasergl.appointment.helper.util.file.test;

import de.glasergl.appointment.helper.util.entity.Appointment;
import de.glasergl.appointment.helper.util.file.AppointmentJSONTransformer;
import de.glasergl.appointment.helper.util.file.SimpleDateJSONTransformer;
import org.json.JSONObject;
import org.junit.Test;

import static de.glasergl.appointment.helper.util.entity.ExampleSimpleDateFactory.TEST_DATE_0;
import static org.junit.Assert.*;

/**
 * Tests the transforming of JSONObjects to Appointments and back.
 *
 * @author glasergl
 */
public class AppointmentJSONTransformerTest {

    @Test
    public void testAppointmentToJSON() {
        final Appointment appointment = new Appointment(TEST_DATE_0, "TestName", true);
        final JSONObject jsonOfA = AppointmentJSONTransformer.appointmentToJSON(appointment);

        assertTrue(jsonOfA.has("date"));
        assertTrue(jsonOfA.has("isABirthday"));
        assertTrue(jsonOfA.has("name"));

        assertEquals("TestName", jsonOfA.getString("name"));
        assertEquals(TEST_DATE_0, SimpleDateJSONTransformer.jsonToSimpleDate(jsonOfA.getJSONObject("date")));
        assertTrue(jsonOfA.getBoolean("isABirthday"));
    }

    @Test
    public void testRepresentsAppointment() {
        final JSONObject json = new JSONObject();
        json.put("date", SimpleDateJSONTransformer.simpleDateToJSON(TEST_DATE_0));
        json.put("name", "TestName");

        assertFalse(AppointmentJSONTransformer.representsAppointment(json));
        json.put("isABirthday", false);
        assertTrue(AppointmentJSONTransformer.representsAppointment(json));
    }

    @Test
    public void testJSONToAppointment() {
        final JSONObject json = new JSONObject();
        json.put("date", SimpleDateJSONTransformer.simpleDateToJSON(TEST_DATE_0));
        json.put("name", "TestName");
        json.put("isABirthday", false);
        final Appointment appointment = AppointmentJSONTransformer.jsonToAppointment(json);

        assertEquals(TEST_DATE_0, appointment.date());
        assertEquals("TestName", appointment.name());

        assertFalse(appointment.isBirthday());
    }

}
