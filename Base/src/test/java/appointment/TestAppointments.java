package appointment;

import static org.junit.Assert.*;
import org.json.JSONObject;
import org.junit.Test;

public class TestAppointments {

    static final SimpleDate testDate = new SimpleDate(15, 6);

    @Test
    public void testToJSON() {
	Appointment a = new Appointment(testDate, "Frodo", "", true);
	JSONObject jsonOfA = a.toJSON();
	assertTrue(jsonOfA.has("description"));
	assertTrue(jsonOfA.has("isABirthday"));
	assertTrue(jsonOfA.has("name"));
	assertTrue(jsonOfA.has("description"));
	assertEquals("Frodo", jsonOfA.getString("name"));
	assertEquals("", jsonOfA.getString("description"));
	assertEquals(testDate, SimpleDate.jsonToDate(jsonOfA.getJSONObject("date")));
	assertTrue(jsonOfA.getBoolean("isABirthday"));
    }

    @Test
    public void testRepresentsAppointment() {
	JSONObject json = new JSONObject();
	json.put("date", testDate.toJSON());
	json.put("name", "Sam");
	json.put("description", "Mordor");
	assertFalse(Appointment.representsAppointment(json));
	json.put("isABirthday", false);
	json.put("id", 15512124);
	assertTrue(Appointment.representsAppointment(json));
    }

    @Test
    public void testJSONToAppointment() {
	JSONObject json = new JSONObject();
	json.put("date", testDate.toJSON());
	json.put("name", "Sam");
	json.put("description", "Mordor");
	json.put("isABirthday", false);
	json.put("id", 53221212);
	Appointment a = Appointment.jsonToAppointment(json);
	assertEquals(testDate, a.getDate());
	assertEquals("Sam", a.getName());
	assertEquals("Mordor", a.getDescription());
	assertFalse(a.isABirthday());
    }

}
