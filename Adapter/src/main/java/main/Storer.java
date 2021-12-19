package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.json.JSONArray;
import appointment.Appointment;

public final class Storer {

    private final JSONArray appointments = new JSONArray();

    public Storer(final List<Appointment> toStore) {
	super();
	for (final Appointment appointment : toStore) {
	    appointments.put(appointment.toJSON());
	}
    }

    public void storeAppointmentsAt(final File toStoreAt) {
	try (final BufferedWriter writer = new BufferedWriter(new FileWriter(toStoreAt))) {
	    writer.write(appointments.toString(1));
	} catch (final IOException e) {
	    e.printStackTrace();
	}
    }
}
