package de.glasergl.configuration.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static de.glasergl.simpleDate.ExampleSimpleDateFactory.TEST_DATE_0;

import org.json.JSONObject;
import org.junit.Test;

import de.glasergl.appointment.Appointment;
import de.glasergl.configuration.AppointmentJSONTransformer;
import de.glasergl.configuration.SimpleDateJSONTransformer;

/**
 * Tests the transforming of JSONObjects to Appointments and back.
 * 
 * @author Gabriel Glaser
 * @version 30.06.2022
 */
public class TestAppointmentJSONTransformer {

	@Test
	public void testAppointmentToJSON() {
		final Appointment appointment = new Appointment(TEST_DATE_0, "TestName", "TestDescription", true);
		final JSONObject jsonOfA = AppointmentJSONTransformer.appointmentToJSON(appointment);

		assertTrue(jsonOfA.has("date"));
		assertTrue(jsonOfA.has("isABirthday"));
		assertTrue(jsonOfA.has("name"));
		assertTrue(jsonOfA.has("description"));

		assertEquals("TestName", jsonOfA.getString("name"));
		assertEquals("TestDescription", jsonOfA.getString("description"));
		assertEquals(TEST_DATE_0, SimpleDateJSONTransformer.jsonToSimpleDate(jsonOfA.getJSONObject("date")));

		assertTrue(jsonOfA.getBoolean("isABirthday"));
	}

	@Test
	public void testRepresentsAppointment() {
		final JSONObject json = new JSONObject();
		json.put("date", SimpleDateJSONTransformer.simpleDateToJSON(TEST_DATE_0));
		json.put("name", "TestName");
		json.put("description", "TestDescription");

		assertFalse(AppointmentJSONTransformer.representsAppointment(json));
		json.put("isABirthday", false);
		assertTrue(AppointmentJSONTransformer.representsAppointment(json));
	}

	@Test
	public void testJSONToAppointment() {
		final JSONObject json = new JSONObject();
		json.put("date", SimpleDateJSONTransformer.simpleDateToJSON(TEST_DATE_0));
		json.put("name", "TestName");
		json.put("description", "TestDescription");
		json.put("isABirthday", false);
		final Appointment appointment = AppointmentJSONTransformer.jsonToAppointment(json);

		assertEquals(TEST_DATE_0, appointment.getDate());
		assertEquals("TestName", appointment.getName());
		assertEquals("TestDescription", appointment.getDescription());

		assertFalse(appointment.isBirthday());
	}

}
