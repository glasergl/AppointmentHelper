package de.glasergl.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.json.JSONArray;
import org.json.JSONObject;

import de.glasergl.appointment.Appointment;
import de.glasergl.appointment.Appointments;

/**
 * Handler for the appointments of the AppointmentHelper stored in the
 * configurationFile. An instance is retrievable with the getter of
 * ConfigurationHandler.
 * 
 * @author Gabriel Glaser
 * @version 01.07.2022
 */
public final class AppointmentsConfigurationHandler {

	public static final String JSON_KEY = "appointments";

	private final JSONArray appointmentsAsJSON;

	/**
	 * Sorts the input appointments at instantiation.
	 * 
	 * @param configurationHandler
	 * @param configuration
	 */
	protected AppointmentsConfigurationHandler(final ConfigurationHandler configurationHandler,
			final JSONObject configuration) {
		super();
		this.appointmentsAsJSON = configuration.getJSONArray(JSON_KEY);
		final List<Appointment> initialAppointments = getAppointments();
		Appointments.sortAppointments(initialAppointments);
		updateAppointments(initialAppointments);
	}

	/**
	 * Replaces the existing appointments with the given.
	 * 
	 * @param appointments
	 */
	public void updateAppointments(final List<Appointment> appointments) {
		appointmentsAsJSON.clear();
		for (final Appointment appointment : appointments) {
			appointmentsAsJSON.put(AppointmentJSONTransformer.appointmentToJSON(appointment));
		}
	}

	/**
	 * @param i
	 * @return The appointment at the given index.
	 * @throws NoSuchElementException If there is no element at the given index.
	 */
	public Appointment getAppointment(final int i) throws NoSuchElementException {
		throwExceptionIfOutOfBounds(i);
		final JSONObject appointmentAsJSON = appointmentsAsJSON.getJSONObject(i);
		return AppointmentJSONTransformer.jsonToAppointment(appointmentAsJSON);
	}

	/**
	 * @return List of all Appointments represented by the current state of the
	 *         configuration.
	 * @throws NoSuchElementException If there is no element at the given index.
	 */
	public List<Appointment> getAppointments() {
		final List<Appointment> appointmentsAsList = new ArrayList<>(appointmentsAsJSON.length());
		for (int i = 0; i < appointmentsAsJSON.length(); i++) {
			appointmentsAsList.add(getAppointment(i));
		}
		return appointmentsAsList;
	}

	/**
	 * Removes the Appointment at the given index.
	 *
	 * @param i
	 * @throws NoSuchElementException If there is no element at the given index.
	 */
	public void delete(final int i) throws NoSuchElementException {
		throwExceptionIfOutOfBounds(i);
		appointmentsAsJSON.remove(i);
	}

	/**
	 * @return Number of Appointments stored in the current configuration.
	 */
	public int getSize() {
		return appointmentsAsJSON.length();
	}

	/**
	 * Throws a runtime exception if the given integer is not a valid index for the
	 * JSONArray of appointments.
	 * 
	 * @param i
	 */
	private void throwExceptionIfOutOfBounds(final int i) {
		if (i >= appointmentsAsJSON.length() || i < 0) {
			throw new NoSuchElementException(
					"There is no element at " + i + " at the array of length " + appointmentsAsJSON.length());
		}
	}

}
