package fileInteraction.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.json.JSONObject;
import org.junit.Test;

import appointment.Appointment;
import fileInteraction.AppointmentJSONTransformer;
import fileInteraction.SimpleDateJSONTransformer;
import simpleDate.SimpleDate;

/**
 * @author Gabriel Glaser
 * @version 19.01.2022
 */
public class TestAppointmentJSONTransformer {

    SimpleDate testDate = new SimpleDate(15, 6);

    @Test
    public void testAppointmentToJSON() {
	Appointment appointment = new Appointment(testDate, "TestName", "TestDescription", true);
	JSONObject jsonOfA = AppointmentJSONTransformer.appointmentToJSON(appointment);

	assertTrue(jsonOfA.has("date"));
	assertTrue(jsonOfA.has("isABirthday"));
	assertTrue(jsonOfA.has("name"));
	assertTrue(jsonOfA.has("description"));

	assertEquals("TestName", jsonOfA.getString("name"));
	assertEquals("TestDescription", jsonOfA.getString("description"));
	assertEquals(testDate, SimpleDateJSONTransformer.jsonToSimpleDate(jsonOfA.getJSONObject("date")));

	assertTrue(jsonOfA.getBoolean("isABirthday"));
    }

    @Test
    public void testRepresentsAppointment() {
	JSONObject json = new JSONObject();
	json.put("date", SimpleDateJSONTransformer.simpleDateToJSON(testDate));
	json.put("name", "TestName");
	json.put("description", "TestDescription");

	assertFalse(AppointmentJSONTransformer.representsAppointment(json));
	json.put("isABirthday", false);
	assertTrue(AppointmentJSONTransformer.representsAppointment(json));
    }

    @Test
    public void testJSONToAppointment() {
	JSONObject json = new JSONObject();
	json.put("date", SimpleDateJSONTransformer.simpleDateToJSON(testDate));
	json.put("name", "TestName");
	json.put("description", "TestDescription");
	json.put("isABirthday", false);
	Appointment appointment = AppointmentJSONTransformer.jsonToAppointment(json);

	assertEquals(testDate, appointment.getDate());
	assertEquals("TestName", appointment.getName());
	assertEquals("TestDescription", appointment.getDescription());

	assertFalse(appointment.isBirthday());
    }

}
