package de.glasergl.appointment.helper.util.file;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.json.JSONArray;
import org.json.JSONObject;

import de.glasergl.appointment.helper.util.entity.Appointment;

/**
 * Handler for the appointments of the AppointmentHelper stored in the
 * configurationFile. An instance is retrievable with the getter of
 * ConfigurationHandler.
 * 
 * @author glasergl
 */
public final class AppointmentsConfigurationHandler extends ConfigurationHandler {
	private static final String APPOINTMENTS_FILE_PATH_ENVIRONMENT_VARIABLE_KEY = "APPOINTMENTS_FILE_PATH";
	public static final String APPOINTMENTS_JSON_KEY = "appointments";

	private final JSONArray appointmentsAsJSON;

	/**
	 * Sorts the input appointments at instantiation.
	 * 
	 * @param configurationHandler
	 * @param configuration
	 */
	public AppointmentsConfigurationHandler(final String configurationFilePath) {
		super(configurationFilePath);
		this.appointmentsAsJSON = configuration.getJSONArray(APPOINTMENTS_JSON_KEY);
	}

	/**
	 * Creates an AppointmentsConfigurationHandler which accesses the file
	 * configured via an environment variable.
	 */
	public AppointmentsConfigurationHandler() {
		this(getAppointmentsConfigFilePath());

	}

	/**
	 * @return Content of environment variable
	 *         APPOINTMENTS_FILE_PATH_ENVIRONMENT_VARIABLE_KEY
	 * @throws IllegalStateException If this environment variable is not set
	 */
	private static String getAppointmentsConfigFilePath() {
		final Map<String, String> environmentVariables = System.getenv();
		if (environmentVariables.containsKey(APPOINTMENTS_FILE_PATH_ENVIRONMENT_VARIABLE_KEY)) {
			return environmentVariables.get(APPOINTMENTS_FILE_PATH_ENVIRONMENT_VARIABLE_KEY);
		} else {
			throw new IllegalStateException(String.format("Need environment variable %s with path to config file",
					APPOINTMENTS_FILE_PATH_ENVIRONMENT_VARIABLE_KEY));
		}
	}

	@Override
	protected JSONObject createEmptyJSONConfiguration() {
		final JSONObject emptyAppointmentsConfiguration = new JSONObject();
		final JSONArray appointments = new JSONArray();
		emptyAppointmentsConfiguration.put(APPOINTMENTS_JSON_KEY, appointments);
		return emptyAppointmentsConfiguration;
	}

	/**
	 * Replaces the existing appointments with the given.
	 * 
	 * @param appointments
	 */
	public void updateAppointments(final List<Appointment> appointments) {
		Collections.sort(appointments);
		appointmentsAsJSON.clear();
		for (final Appointment appointment : appointments) {
			appointmentsAsJSON.put(AppointmentJSONTransformer.appointmentToJSON(appointment));
		}
		storeConfiguration();
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
