package ui;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import appointments.Appointment;
import appointments.SimpleDate;

public class TestAppointmentOutputText {

	@Test
	public void test00() {
		List<Appointment> appointments = getTestAppointmentsWithDates(SimpleDate.getDateIn(122),
				SimpleDate.getDateIn(13), SimpleDate.getDateIn(54), SimpleDate.getDateIn(16));
		AppointmentOutputText output = new AppointmentOutputText("Heute", Appointment::isToday, appointments);
		assertEquals("", output.toString());
	}

	@Test
	public void test01() {
		List<Appointment> appointments = getTestAppointmentsWithDates(SimpleDate.getDateIn(122),
				SimpleDate.getDateIn(13), SimpleDate.getToday(), SimpleDate.getDateIn(16));
		AppointmentOutputText output = new AppointmentOutputText("Heute", Appointment::isToday, appointments);
		assertEquals("Heute ist Besprechung.", output.toString());
	}

	@Test
	public void test10() {
		List<Appointment> appointments = getTestAppointmentsWithDates(SimpleDate.getToday(), SimpleDate.getDateIn(13),
				SimpleDate.getDateIn(54), SimpleDate.getDateIn(16));
		AppointmentOutputText output = new AppointmentOutputText("Heute", Appointment::isToday, appointments);
		assertEquals("Heute hat Sam Geburtstag!", output.toString());
	}

	@Test
	public void test11() {
		List<Appointment> appointments = getTestAppointmentsWithDates(SimpleDate.getToday(), SimpleDate.getDateIn(13),
				SimpleDate.getToday(), SimpleDate.getDateIn(16));
		AppointmentOutputText output = new AppointmentOutputText("Heute", Appointment::isToday, appointments);
		assertEquals("Heute hat Sam Geburtstag und es ist Besprechung.", output.toString());
	}

	@Test
	public void test02() {
		List<Appointment> appointments = getTestAppointmentsWithDates(SimpleDate.getDateIn(122),
				SimpleDate.getDateIn(13), SimpleDate.getToday(), SimpleDate.getToday());
		AppointmentOutputText output = new AppointmentOutputText("Heute", Appointment::isToday, appointments);
		assertEquals("Heute sind Besprechung und Party.", output.toString());
	}

	@Test
	public void test12() {
		List<Appointment> appointments = getTestAppointmentsWithDates(SimpleDate.getToday(), SimpleDate.getDateIn(13),
				SimpleDate.getToday(), SimpleDate.getToday());
		AppointmentOutputText output = new AppointmentOutputText("Heute", Appointment::isToday, appointments);
		assertEquals("Heute hat Sam Geburtstag und es sind Besprechung und Party.", output.toString());
	}

	@Test
	public void test22() {
		List<Appointment> appointments = getTestAppointmentsWithDates(SimpleDate.getToday(), SimpleDate.getToday(),
				SimpleDate.getToday(), SimpleDate.getToday());
		AppointmentOutputText output = new AppointmentOutputText("Heute", Appointment::isToday, appointments);
		assertEquals("Heute haben Sam und Frodo Geburtstag und es sind Besprechung und Party.", output.toString());
	}

	@Test
	public void test20() {
		List<Appointment> appointments = getTestAppointmentsWithDates(SimpleDate.getToday(), SimpleDate.getToday(),
				SimpleDate.getDateIn(54), SimpleDate.getDateIn(16));
		AppointmentOutputText output = new AppointmentOutputText("Heute", Appointment::isToday, appointments);
		assertEquals("Heute haben Sam und Frodo Geburtstag!", output.toString());
	}

	@Test
	public void test21() {
		List<Appointment> appointments = getTestAppointmentsWithDates(SimpleDate.getToday(), SimpleDate.getToday(),
				SimpleDate.getToday(), SimpleDate.getDateIn(16));
		AppointmentOutputText output = new AppointmentOutputText("Heute", Appointment::isToday, appointments);
		assertEquals("Heute haben Sam und Frodo Geburtstag und es ist Besprechung.", output.toString());
	}

	private List<Appointment> getTestAppointmentsWithDates(SimpleDate d1, SimpleDate d2, SimpleDate d3, SimpleDate d4) {
		Appointment a1 = new Appointment(d1, "Sam", "beschreibung1", true);
		Appointment a2 = new Appointment(d2, "Frodo", "beschreibung2", true);
		Appointment a3 = new Appointment(d3, "Besprechung", "beschreibung3", false);
		Appointment a4 = new Appointment(d4, "Party", "beschreibung4", false);
		return Arrays.asList(a1, a2, a3, a4);
	}

}
