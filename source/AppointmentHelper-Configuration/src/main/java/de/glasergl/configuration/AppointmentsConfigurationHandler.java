package de.glasergl.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.json.JSONArray;
import org.json.JSONObject;

import de.glasergl.appointment.Appointment;
import de.glasergl.jsonTransformer.AppointmentJSONTransformer;

/**
 * Handler for the appointments of the AppointmentHelper stored in the
 * configurationFile.
 * 
 * @author Gabriel Glaser
 * @version 18.06.2022
 */
public final class AppointmentsConfigurationHandler {

    public static final String JSON_KEY = "appointments";

    private final JSONArray appointmentsAsJSON;

    public AppointmentsConfigurationHandler(final ConfigurationHandler configurationHandler, final JSONObject configuration) {
	super();
	this.appointmentsAsJSON = configuration.getJSONArray(JSON_KEY);
    }

    /**
     * @return List of all Appointments represented by the current state of the
     *         configuration.
     */
    public List<Appointment> getAppointments() {
	final List<Appointment> appointmentsAsList = new ArrayList<>();
	for (int i = 0; i < appointmentsAsJSON.length(); i++) {
	    final JSONObject appointmentAsJSON = appointmentsAsJSON.getJSONObject(i);
	    appointmentsAsList.add(AppointmentJSONTransformer.jsonToAppointment(appointmentAsJSON));
	}
	return appointmentsAsList;
    }

    /**
     * Adds the given Appointment.
     * 
     * @param appointment
     */
    public void add(final Appointment appointment) {
	final JSONObject appointmentAsJSON = AppointmentJSONTransformer.appointmentToJSON(appointment);
	appointmentsAsJSON.put(appointmentAsJSON);
    }

    /**
     * Removes the Appointment at the given index.
     *
     * @param index
     * @throws NoSuchElementException If there is not element at the given index.
     */
    public void remove(final int index) throws NoSuchElementException {
	if (index >= appointmentsAsJSON.length()) {
	    throw new NoSuchElementException();
	}
	appointmentsAsJSON.remove(index);
    }

    /**
     * @param appointment
     * @return Whether the current configuration contains the given Appointment.
     */
    public boolean contains(final Appointment appointment) {
	final List<Appointment> appointments = getAppointments();
	return appointments.contains(appointment);
    }

    /**
     * @return Number of Appointments stored in the current configuration.
     */
    public int size() {
	final List<Appointment> appointments = getAppointments();
	return appointments.size();
    }

}
