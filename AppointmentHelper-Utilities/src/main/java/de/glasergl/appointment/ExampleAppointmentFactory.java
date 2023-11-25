package de.glasergl.appointment;

import de.glasergl.simpleDate.SimpleDate;

/**
 * Class which contains some example Appointments.
 * 
 * @author Gabriel Glaser
 * @version 30.06.2022
 */
public final class ExampleAppointmentFactory {

    public static final Appointment TEST_APPOINTMENT_0 = new Appointment(new SimpleDate(10, 2), "TestPerson0", "Description0", false);
    public static final Appointment TEST_APPOINTMENT_1 = new Appointment(new SimpleDate(7, 11), "TestPerson1", "Description1", false);
    public static final Appointment TEST_APPOINTMENT_2 = new Appointment(new SimpleDate(3, 7), "TestPerson2", "Description2", true);
    public static final Appointment TEST_APPOINTMENT_3 = new Appointment(new SimpleDate(12, 3), "TestPerson3", "Description3", false);
    public static final Appointment TEST_APPOINTMENT_4 = new Appointment(new SimpleDate(5, 11), "TestPerson4", "Description4", true);

}
