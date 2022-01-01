package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import appointment.Appointment;
import appointment.SimpleDate;

/**
 * Class to extract the appointments from an older version.
 * 
 * @author Gabriel Glaser
 * @version 1.1.2022
 */
public final class AppointmentReader {

    private final File fileToRead;
    private final List<Appointment> adaptedAppointments;

    public AppointmentReader(final File fileWithOldAppointments) {
	super();
	this.fileToRead = fileWithOldAppointments;
	this.adaptedAppointments = calculateAdapted();
    }

    private List<Appointment> calculateAdapted() {
	final List<Appointment> adapted = new ArrayList<>();
	try (final BufferedReader reader = new BufferedReader(new FileReader(fileToRead))) {
	    String currentLine;
	    while ((currentLine = reader.readLine()) != null) {
		if (!currentLine.equals("") && currentLine.charAt(0) != '#') {
		    adapted.add(extractAppointmentFromLine(currentLine));
		}
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return adapted;
    }

    private Appointment extractAppointmentFromLine(final String line) {
	final String[] appointmentComponents = line.split("\\.");
	final String name = appointmentComponents[0];
	final SimpleDate date = new SimpleDate(Integer.valueOf(appointmentComponents[1]), Integer.valueOf(appointmentComponents[2]));
	return new Appointment(date, name);
    }

    public List<Appointment> getAdapted() {
	return adaptedAppointments;
    }

}
