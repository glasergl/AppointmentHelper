package reminderUI.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.Test;

import appointment.Appointment;
import reminderUI.AppointmentMessageModel;
import simpleDate.SimpleDates;

/**
 * @author Gabriel Glaser
 */
public class AppointmentMessageMappingTest {
	static Appointment appointmentToday1 = new Appointment(SimpleDates.getToday(), "Sam", true);
	static Appointment appointmentToday2 = new Appointment(SimpleDates.getToday(), "Frodo", true);
	static Appointment appointmentTomorrow1 = new Appointment(SimpleDates.getTomorrow(), "Besprechung", false);
	static Appointment appointmentTomorrow2 = new Appointment(SimpleDates.getTomorrow(), "Party", false);

	@Test
	public void testMappingWithBirthdays() {
		List<Appointment> appointments = Arrays.asList(appointmentToday1, appointmentToday2, appointmentTomorrow1,
				appointmentTomorrow2);
		testMappingWith(appointments, Appointment::isToday, 2);
	}

	@Test
	public void testMappingWithNonBirthdays() {
		List<Appointment> appointments = Arrays.asList(appointmentToday1, appointmentToday2, appointmentTomorrow1,
				appointmentTomorrow2);
		testMappingWith(appointments, Appointment::isTomorrow, 2);
	}

	public void testMappingWith(final List<Appointment> appointments, final Predicate<Appointment> timeCondition,
			final int expectedSize) {
		AppointmentMessageModel appointmentMessage = new AppointmentMessageModel("Time", timeCondition, appointments);
		List<String> messageComponents = appointmentMessage.getComponents();
		int numberOfSeenNames = 0;
		for (int i = 0; i < messageComponents.size(); i++) {
			if (appointmentMessage.isName(i)) {
				numberOfSeenNames++;
				assertTrue(appointments.contains(appointmentMessage.getAppointment(i)));
			}
		}
		assertEquals(expectedSize, numberOfSeenNames);
	}

}
