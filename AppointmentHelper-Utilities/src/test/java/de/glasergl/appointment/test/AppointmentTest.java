package de.glasergl.appointment.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.glasergl.appointment.Appointment;
import de.glasergl.simpleDate.SimpleDate;

/**
 * @author Gabriel Glaser
 */
public class AppointmentTest {
	@Test
	public void testCompareToWithDifferenceAtLevel1() {
		final Appointment a1 = new Appointment(new SimpleDate(12, 5), "Tom");
		Appointment a2 = new Appointment(new SimpleDate(24, 3), "Tom");
		assertTrue(a1.compareTo(a2) > 0);
	}

	@Test
	public void testCompareToWithDifferenceAtLevel2() {
		final Appointment a1 = new Appointment(new SimpleDate(12, 5), "Tom");
		final Appointment a2 = new Appointment(new SimpleDate(12, 5), "Peter");
		assertTrue(a1.compareTo(a2) > 0);
	}

	@Test
	public void testCompareToWithDifferenceAtLevel3() {
		final Appointment a1 = new Appointment(new SimpleDate(12, 5), "Tom", false);
		final Appointment a2 = new Appointment(new SimpleDate(12, 5), "Tom", true);
		assertTrue(a1.compareTo(a2) < 0);
	}

	@Test
	public void testCompareToWithEqualAppointments() {
		final Appointment a1 = new Appointment(new SimpleDate(12, 5), "Tom", false);
		final Appointment a2 = new Appointment(new SimpleDate(12, 5), "Tom", false);
		assertEquals(0, a1.compareTo(a2));
	}
}
