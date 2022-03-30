package ui.test.appointmentField;

import java.io.File;
import appointment.Appointment;
import appointmentField.AppointmentFieldController;
import fileInteraction.AppointmentFileInteracter;
import simpleDate.SimpleDate;

/**
 * Class which contains variables and functions which are used in tests.
 * 
 * @author Gabriel Glaser
 * @version 12.03.2022
 */
public class Tests {

    public static String pathForTestRessources = "src\\test\\resources\\";

    public static Appointment testAppointment1 = new Appointment(new SimpleDate(10, 2), "Tom", "hello there", true);
    public static Appointment testAppointment2 = new Appointment(new SimpleDate(7, 10), "Freddyyy", "siuuuuu", false);

    public static void initializeAppointmentFieldController(final AppointmentFieldController toInitialize, final Appointment content) {
	toInitialize.setDate(content.getDate());
	toInitialize.setName(content.getName());
	toInitialize.setDescription(content.getDescription());
	toInitialize.setIsBirthday(content.isBirthday());
    }

    public static File createTestAppointmentFile(String name) {
	return AppointmentFileInteracter.createEmptyAppointmentFile(pathForTestRessources + name);
    }

}
