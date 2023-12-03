package de.glasergl.appointment;

import de.glasergl.simpleDate.SimpleDate;

/**
 * Class which contains some example appointments which can be used for testing.
 * The index at the end of the static variable represents an order among all
 * test appointments, i.e., TEST_APPOINTMENT_i < TEST_APPOINTMENT_j for all i, j
 * (realized by ascending dates).
 * 
 * @author Gabriel Glaser
 */
public final class ExampleAppointmentFactory {
	public static final Appointment TEST_APPOINTMENT_0 = new Appointment(new SimpleDate(10, 2), "TestPerson0", false);
	public static final Appointment TEST_APPOINTMENT_1 = new Appointment(new SimpleDate(7, 5), "TestPerson1", false);
	public static final Appointment TEST_APPOINTMENT_2 = new Appointment(new SimpleDate(8, 6), "TestPerson2", true);
	public static final Appointment TEST_APPOINTMENT_3 = new Appointment(new SimpleDate(30, 8), "TestPerson3", false);
	public static final Appointment TEST_APPOINTMENT_4 = new Appointment(new SimpleDate(5, 12), "TestPerson4", true);
}
