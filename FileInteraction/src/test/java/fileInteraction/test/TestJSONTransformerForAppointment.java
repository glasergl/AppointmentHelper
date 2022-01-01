package fileInteraction.test;

import static org.junit.Assert.*;
import org.json.JSONObject;
import org.junit.Test;
import appointment.Appointment;
import date.SimpleDate;
import fileInteraction.JSONTransformer;

public class TestJSONTransformerForAppointment {

    SimpleDate testDate = new SimpleDate(15, 6);

    @Test
    public void testAppointmentToJSON() {
	Appointment appointment = new Appointment(testDate, "TestName", "TestDescription", true);
	JSONObject jsonOfA = JSONTransformer.appointmentToJSON(appointment);

	assertTrue(jsonOfA.has("date"));
	assertTrue(jsonOfA.has("isABirthday"));
	assertTrue(jsonOfA.has("name"));
	assertTrue(jsonOfA.has("description"));

	assertEquals("TestName", jsonOfA.getString("name"));
	assertEquals("TestDescription", jsonOfA.getString("description"));
	assertEquals(testDate, JSONTransformer.jsonToSimpleDate(jsonOfA.getJSONObject("date")));

	assertTrue(jsonOfA.getBoolean("isABirthday"));
    }

    @Test
    public void testRepresentsAppointment() {
	JSONObject json = new JSONObject();
	json.put("date", JSONTransformer.simpleDateToJSON(testDate));
	json.put("name", "TestName");
	json.put("description", "TestDescription");

	assertFalse(JSONTransformer.representsAppointment(json));
	json.put("isABirthday", false);
	assertTrue(JSONTransformer.representsAppointment(json));
    }

    @Test
    public void testJSONToAppointment() {
	JSONObject json = new JSONObject();
	json.put("date", JSONTransformer.simpleDateToJSON(testDate));
	json.put("name", "TestName");
	json.put("description", "TestDescription");
	json.put("isABirthday", false);
	Appointment appointment = JSONTransformer.jsonToAppointment(json);

	assertEquals(testDate, appointment.getDate());
	assertEquals("TestName", appointment.getName());
	assertEquals("TestDescription", appointment.getDescription());

	assertFalse(appointment.isBirthday());
    }

}
