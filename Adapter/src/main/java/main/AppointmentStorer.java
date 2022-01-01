package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.json.JSONArray;
import appointment.Appointment;
import fileInteraction.JSONTransformer;

/**
 * Class which stores the given Appointments at the given File.
 * 
 * @author Gabriel Glaser
 * @version 1.1.2022
 */
public final class AppointmentStorer {

    public AppointmentStorer(final List<Appointment> appointmentsToStore, final File fileToStoreAppointmentsAt) {
	super();
	storeAt(appointmentsToStore, fileToStoreAppointmentsAt);
    }

    private void storeAt(final List<Appointment> appointmentsToStore, final File fileToStoreAppointmentsAt) {
	final JSONArray appointmentsAsJSON = new JSONArray();
	for (final Appointment appointment : appointmentsToStore) {
	    appointmentsAsJSON.put(JSONTransformer.appointmentToJSON(appointment));
	}
	try (final BufferedWriter writer = new BufferedWriter(new FileWriter(fileToStoreAppointmentsAt))) {
	    writer.write(appointmentsAsJSON.toString());
	} catch (final IOException e) {
	    e.printStackTrace();
	}
    }
}
