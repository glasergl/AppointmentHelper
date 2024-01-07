package de.glasergl.ui.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import de.glasergl.appointment.Appointment;
import de.glasergl.simpleDate.SimpleDate;
import de.glasergl.ui.AppointmentsSummaryPanel;

/**
 * @author Gabriel Glaser
 */
public class AppointmentSummaryTest {
	SimpleDate testDate1 = new SimpleDate(14, 7);
	SimpleDate testDate2 = new SimpleDate(18, 6);

	@Test
	public void testNoAppointments() {
		final List<Appointment> appointments = List.of();
		final AppointmentsSummaryPanel appointmentsSummaryPanel = new AppointmentsSummaryPanel(appointments);
		assertEquals("", appointmentsSummaryPanel.getSummaryString());
	}

	@Test
	public void testWithOne() {
		final String name = "Sam";
		final Appointment appointment = new Appointment(testDate1, name, true);
		final List<Appointment> appointments = List.of(appointment);
		final AppointmentsSummaryPanel appointmentsSummaryPanel = new AppointmentsSummaryPanel(appointments);
		assertEquals(name, appointmentsSummaryPanel.getSummaryString());
	}

	@Test
	public void testWithTwo() {
		final String name1 = "Sam";
		final String name2 = "Frodo";
		final Appointment appointment1 = new Appointment(testDate1, name1, true);
		final Appointment appointment2 = new Appointment(testDate1, name2, true);
		final List<Appointment> appointments = List.of(appointment1, appointment2);
		final AppointmentsSummaryPanel appointmentsSummaryPanel = new AppointmentsSummaryPanel(appointments);
		assertEquals("2 Geburtstage", appointmentsSummaryPanel.getSummaryString());
	}

	@Test
	public void testWithTwoWithoutAllBirthdays() {
		final String name1 = "Sam";
		final String name2 = "Frodo";
		final Appointment appointment1 = new Appointment(testDate1, name1, false);
		final Appointment appointment2 = new Appointment(testDate2, name2, true);
		final List<Appointment> appointments = List.of(appointment1, appointment2);
		final AppointmentsSummaryPanel appointmentsSummaryPanel = new AppointmentsSummaryPanel(appointments);
		assertEquals("2 Termine", appointmentsSummaryPanel.getSummaryString());
	}
}
