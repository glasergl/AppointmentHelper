package main;

import java.io.File;

/**
 * Entry-Point of the Adapter program which transforms the File of the older
 * version "Geburtstage.txt" to the new version "appointments.json"
 * 
 * @author Gabriel Glaser
 * @version 1.1.2022
 */
public final class Adapter {

    private static final File GEBURTSTAGE = new File("Geburtstage.txt");
    private static final File APPOINTMENTS = new File("appointments.json");

    public static void main(final String[] args) {
	final AppointmentReader reader = new AppointmentReader(GEBURTSTAGE);
	new AppointmentStorer(reader.getAdapted(), APPOINTMENTS);
    }

}
