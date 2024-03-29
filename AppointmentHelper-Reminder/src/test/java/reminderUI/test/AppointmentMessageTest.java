package reminderUI.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import appointment.Appointment;
import reminderUI.AppointmentMessageModel;
import simpleDate.SimpleDate;
import simpleDate.SimpleDates;

/**
 * @author Gabriel Glaser
 */
public class AppointmentMessageTest {
	@Test
	public void test00() {
		List<Appointment> appointments = getTestAppointmentsWithDates(SimpleDates.getDateIn(122),
				SimpleDates.getDateIn(13), SimpleDates.getDateIn(54), SimpleDates.getDateIn(16));
		AppointmentMessageModel output = new AppointmentMessageModel("Heute", Appointment::isToday, appointments);
		assertEquals("", output.getAppointmentMessage());
	}

	@Test
	public void test01() {
		List<Appointment> appointments = getTestAppointmentsWithDates(SimpleDates.getDateIn(122),
				SimpleDates.getDateIn(13), SimpleDates.getToday(), SimpleDates.getDateIn(16));
		AppointmentMessageModel output = new AppointmentMessageModel("Heute", Appointment::isToday, appointments);
		assertEquals("Heute ist Besprechung.", output.getAppointmentMessage());
	}

	@Test
	public void test02() {
		List<Appointment> appointments = getTestAppointmentsWithDates(SimpleDates.getDateIn(122),
				SimpleDates.getDateIn(13), SimpleDates.getToday(), SimpleDates.getToday());
		AppointmentMessageModel output = new AppointmentMessageModel("Heute", Appointment::isToday, appointments);
		assertEquals("Heute sind Besprechung und Party.", output.getAppointmentMessage());
	}

	@Test
	public void test10() {
		List<Appointment> appointments = getTestAppointmentsWithDates(SimpleDates.getToday(), SimpleDates.getDateIn(13),
				SimpleDates.getDateIn(54), SimpleDates.getDateIn(16));
		AppointmentMessageModel output = new AppointmentMessageModel("Heute", Appointment::isToday, appointments);
		assertEquals("Heute hat Sam Geburtstag!", output.getAppointmentMessage());
	}

	@Test
	public void test11() {
		List<Appointment> appointments = getTestAppointmentsWithDates(SimpleDates.getToday(), SimpleDates.getDateIn(13),
				SimpleDates.getToday(), SimpleDates.getDateIn(16));
		AppointmentMessageModel output = new AppointmentMessageModel("Heute", Appointment::isToday, appointments);
		assertEquals("Heute hat Sam Geburtstag und es ist Besprechung.", output.getAppointmentMessage());
	}

	@Test
	public void test12() {
		List<Appointment> appointments = getTestAppointmentsWithDates(SimpleDates.getToday(), SimpleDates.getDateIn(13),
				SimpleDates.getToday(), SimpleDates.getToday());
		AppointmentMessageModel output = new AppointmentMessageModel("Heute", Appointment::isToday, appointments);
		assertEquals("Heute hat Sam Geburtstag und es sind Besprechung und Party.", output.getAppointmentMessage());
	}

	@Test
	public void test20() {
		List<Appointment> appointments = getTestAppointmentsWithDates(SimpleDates.getToday(), SimpleDates.getToday(),
				SimpleDates.getDateIn(54), SimpleDates.getDateIn(16));
		AppointmentMessageModel output = new AppointmentMessageModel("Heute", Appointment::isToday, appointments);
		assertEquals("Heute haben Sam und Frodo Geburtstag!", output.getAppointmentMessage());
	}

	@Test
	public void test21() {
		List<Appointment> appointments = getTestAppointmentsWithDates(SimpleDates.getToday(), SimpleDates.getToday(),
				SimpleDates.getToday(), SimpleDates.getDateIn(16));
		AppointmentMessageModel output = new AppointmentMessageModel("Heute", Appointment::isToday, appointments);
		assertEquals("Heute haben Sam und Frodo Geburtstag und es ist Besprechung.", output.getAppointmentMessage());
	}

	@Test
	public void test22() {
		List<Appointment> appointments = getTestAppointmentsWithDates(SimpleDates.getToday(), SimpleDates.getToday(),
				SimpleDates.getToday(), SimpleDates.getToday());
		AppointmentMessageModel output = new AppointmentMessageModel("Heute", Appointment::isToday, appointments);
		assertEquals("Heute haben Sam und Frodo Geburtstag und es sind Besprechung und Party.",
				output.getAppointmentMessage());
	}

	private List<Appointment> getTestAppointmentsWithDates(SimpleDate d1, SimpleDate d2, SimpleDate d3, SimpleDate d4) {
		Appointment appointment1 = new Appointment(d1, "Sam", true);
		Appointment appointment2 = new Appointment(d2, "Frodo", true);
		Appointment appointment3 = new Appointment(d3, "Besprechung", false);
		Appointment appointment4 = new Appointment(d4, "Party", false);
		return Arrays.asList(appointment1, appointment2, appointment3, appointment4);
	}
}
