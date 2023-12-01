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
 */
public class TestAppointmentJSONTransformer {

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

		assertEquals(TEST_DATE_0, appointment.getDate());
		assertEquals("TestName", appointment.getName());

		assertFalse(appointment.isBirthday());
	}

}
