package de.glasergl.appointment;

import de.glasergl.simpleDate.SimpleDate;

/**
 * Class which contains some example appointments which can be used for testing.
 * 
 * @author Gabriel Glaser
 */
public final class ExampleAppointmentFactory {
	public static final Appointment TEST_APPOINTMENT_0 = new Appointment(new SimpleDate(10, 2), "TestPerson0", false);
	public static final Appointment TEST_APPOINTMENT_1 = new Appointment(new SimpleDate(7, 11), "TestPerson1", false);
	public static final Appointment TEST_APPOINTMENT_2 = new Appointment(new SimpleDate(3, 7), "TestPerson2", true);
	public static final Appointment TEST_APPOINTMENT_3 = new Appointment(new SimpleDate(12, 3), "TestPerson3", false);
	public static final Appointment TEST_APPOINTMENT_4 = new Appointment(new SimpleDate(5, 11), "TestPerson4", true);
}
