package de.glasergl.appointmentMessage.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.Test;

import de.glasergl.appointment.Appointment;
import de.glasergl.model.AppointmentMessageModel;
import de.glasergl.simpleDate.SimpleDates;

/**
 * @author Gabriel Glaser
 * @version 03.04.2022
 */
public class TestAppointmentMessageMapping {

    static Appointment appointmentToday1 = new Appointment(SimpleDates.getToday(), "Sam", "beschreibung1", true);
    static Appointment appointmentToday2 = new Appointment(SimpleDates.getToday(), "Frodo", "beschreibung2", true);
    static Appointment appointmentTomorrow1 = new Appointment(SimpleDates.getTomorrow(), "Besprechung", "beschreibung3", false);
    static Appointment appointmentTomorrow2 = new Appointment(SimpleDates.getTomorrow(), "Party", "beschreibung4", false);

    @Test
    public void testMappingWithBirthdays() {
	List<Appointment> appointments = Arrays.asList(appointmentToday1, appointmentToday2, appointmentTomorrow1, appointmentTomorrow2);
	testMappingWith(appointments, Appointment::isToday, 2);
    }

    @Test
    public void testMappingWithNonBirthdays() {
	List<Appointment> appointments = Arrays.asList(appointmentToday1, appointmentToday2, appointmentTomorrow1, appointmentTomorrow2);
	testMappingWith(appointments, Appointment::isTomorrow, 2);
    }

    public void testMappingWith(final List<Appointment> appointments, final Predicate<Appointment> timeCondition, final int expectedSize) {
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
