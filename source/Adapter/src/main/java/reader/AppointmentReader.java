package reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import appointment.Appointment;
import simpleDate.SimpleDate;

/**
 * Class to extract the appointments from an older version.
 * 
 * @author Gabriel Glaser
 * @version 29.3.2022
 */
public final class AppointmentReader {

    private static final int LINE_START_INDEX = 1;

    private final File fileWithOldBirthdays;
    private final List<Appointment> adaptedAppointments;

    /**
     * Calculates the Appointments in the given File. They are available with
     * getAdapted().
     * 
     * The File should contain the format of the old version. A line should be
     * "name"."day"."month"."anything", empty or begin with "#".
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
     * Reads through the whole File with the old birthdays and returns the given
     * birthdays as Appointments in a List.
     * 
     * @return The List of the calculated Appointments.
     * @throws IllegalFileFormatException If a line in the File doesn't have the
     *                                    format of the old version.
     */
    private List<Appointment> calculateAdapted() throws IllegalFileFormatException {
	final List<Appointment> adaptedAppointments = new LinkedList<>();
	try (final BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileWithOldBirthdays), StandardCharsets.UTF_8));) {
	    String currentLine = reader.readLine();
	    int lineIndex = LINE_START_INDEX;
	    while (currentLine != null) {
		handleLine(adaptedAppointments, currentLine, lineIndex);
		lineIndex++;
		currentLine = reader.readLine();
	    }
	} catch (final IOException e) {
	    throw new RuntimeException("Couldn't read Geburtstage.txt");
	}
	return adaptedAppointments;
    }

    /**
     * Adds the Appointment represented by the given String, if the String isn't
     * empty or starts with a '#'.
     * 
     * An extracted Appointment is only added if it hasn't been added, yet.
     * 
     * @param adaptedAppointments
     * @param line
     * @param lineIndex
     * @throws IllegalFileFormatException If the line doesn't have the format of the
     *                                    old version.
     */
    private void handleLine(final List<Appointment> adaptedAppointments, final String line, final int lineIndex) throws IllegalFileFormatException {
	if (!line.equals("") && line.charAt(0) != '#') {
	    final Appointment extracted = extractAppointmentFromLine(line, lineIndex);
	    if (!adaptedAppointments.contains(extracted)) {
		adaptedAppointments.add(extracted);
	    }
	}
    }

    /**
     * Calculates the Appointment represented by the given line. The line needs to
     * be in the old format of ("name"."day"."month"."anything")
     * 
     * @param line
     * @param lineIndex
     * @return The Appointment represented by the line in old format.
     * @throws IllegalFileFormatException If the line doesn't have the format of the
     *                                    old version.
     */
    private Appointment extractAppointmentFromLine(final String line, final int lineIndex) throws IllegalFileFormatException {
	try {
	    final String[] appointmentComponents = line.split("\\.");
	    final String name = appointmentComponents[0];
	    final SimpleDate date = new SimpleDate(Integer.valueOf(appointmentComponents[1]), Integer.valueOf(appointmentComponents[2]));
	    return new Appointment(date, name);
	} catch (final Throwable e) {
	    throw new IllegalFileFormatException(fileWithOldBirthdays, lineIndex);
	}
    }

    /**
     * @return The previously calculated adapted Appointments.
     */
    public List<Appointment> getAdaptedAppointments() {
	return adaptedAppointments;
    }

}
