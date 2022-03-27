package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import appointment.Appointment;
import date.SimpleDate;

/**
 * Class to extract the appointments from an older version.
 * 
 * @author Gabriel Glaser
 * @version 19.1.2022
 */
public final class AppointmentReader {

    private final File fileWithOldBirthdays;
    private final List<Appointment> adaptedAppointments;

    /**
     * Calculates the Appointments in the given File. They are available through
     * getAdapted().
     * 
     * The File should contain the format of the old version. ("name"."day"."month"
     * or empty or beginning with "#" at every line)
     * 
     * @param fileWithOldBirthdays
     * @throws IllegalFileFormatException If the given File doesn't have the format
     *                                    of the old version.
     */
    public AppointmentReader(final File fileWithOldBirthdays) throws IllegalFileFormatException {
	super();
	this.fileWithOldBirthdays = fileWithOldBirthdays;
	this.adaptedAppointments = calculateAdapted();
    }

    /**
     * Reads through the whole File with the old birthdays and stores the given
     * birthdays as Appointments in a List.
     * 
     * @return The List of the calculated Appointments.
     * @throws IllegalFileFormatException If a line in the File doesn't have the
     *                                    format of the old version.
     */
    private List<Appointment> calculateAdapted() throws IllegalFileFormatException {
	final List<Appointment> adaptedAppointments = new ArrayList<>();
	try (final BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileWithOldBirthdays), StandardCharsets.UTF_8));) {
	    String currentLine;
	    while ((currentLine = reader.readLine()) != null) {
		if (!currentLine.equals("") && currentLine.charAt(0) != '#') {
		    adaptedAppointments.add(extractAppointmentFromLine(currentLine));
		}
	    }
	} catch (final IOException e) {
	    throw new RuntimeException("Couldn't read Geburtstage.txt");
	}
	return adaptedAppointments;
    }

    /**
     * Calculates the Appointment represented by the given line. The line needs to
     * be in the old format of ("name"."day"."month")
     * 
     * @param line
     * @return The Appointment represented by the line in old format.
     * @throws IllegalFileFormatException If the line doesn't have the format of the
     *                                    old version.
     */
    private Appointment extractAppointmentFromLine(final String line) throws IllegalFileFormatException {
	try {
	    final String[] appointmentComponents = line.split("\\.");
	    final String name = appointmentComponents[0];
	    final SimpleDate date = new SimpleDate(Integer.valueOf(appointmentComponents[1]), Integer.valueOf(appointmentComponents[2]));
	    return new Appointment(date, name);
	} catch (final Throwable e) {
	    throw new IllegalFileFormatException("Geburtstage.txt");
	}
    }

    public List<Appointment> getAdaptedAppointments() {
	return adaptedAppointments;
    }

}
