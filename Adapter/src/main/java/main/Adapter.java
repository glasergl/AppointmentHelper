package main;

import java.io.File;
import appointment.Appointment;
import fileInteraction.AppointmentFileInteracter;

/**
 * Entry-Point of the Adapter program which transforms the File of the older
 * version "Geburtstage.txt" to the new version "appointments.json"
 * 
 * @author Gabriel Glaser
 * @version 1.1.2022
 */
public final class Adapter {

    private static final File GEBURTSTAGE = new File("Geburtstage.txt");

    public static void main(final String[] args) {
	final AppointmentReader reader = new AppointmentReader(GEBURTSTAGE);
	AppointmentFileInteracter.createEmptyAppointmentFile();
	for (final Appointment appointmentToStore : reader.getAdapted()) {
	    AppointmentFileInteracter.add(appointmentToStore);
	}
    }

}
