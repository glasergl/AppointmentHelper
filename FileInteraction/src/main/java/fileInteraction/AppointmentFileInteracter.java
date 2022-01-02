package fileInteraction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import appointment.Appointment;

/**
 * Class which contains methods to interact with a File of appointments.
 * 
 * @author Gabriel Glaser
 * @version 31.12.2021
 */
public final class AppointmentFileInteracter {

    private static final String DEFAULT_APPOINTMENT_FILE_PATH = "appointments.json";
    private static final File FILE_WITH_APPOINTMENTS = new File(DEFAULT_APPOINTMENT_FILE_PATH);

    private AppointmentFileInteracter() {
    }

    /**
     * Adds the given Appointment to the standard Appointment-File.
     * 
     * @param appointmentToAdd
     * @throws IllegalArgumentException - if the standard Appointment-File already
     *                                  contains the given Appointment.
     */
    public static void add(final Appointment appointmentToAdd) throws IllegalArgumentException {
	add(appointmentToAdd, FILE_WITH_APPOINTMENTS);
    }

    /**
     * Removes the given Appointment from the standard Appointment-File.
     * 
     * @param appointmentToRemove
     * @throws IllegalArgumentException - if the standard Appointment-File already
     *                                  contains the given Appointment.
     */
    public static void remove(final Appointment appointmentToRemove) throws IllegalArgumentException {
	remove(appointmentToRemove, FILE_WITH_APPOINTMENTS);
    }

    /**
     * Tests if the standard Appointment-File contains the specified Appointment.
     * 
     * @param appointmentToTest
     * @return True if the standard Appointment-File contains the specified
     *         Appointment.
     */
    public static boolean contains(final Appointment appointmentToTest) {
	return contains(appointmentToTest, FILE_WITH_APPOINTMENTS);
    }

    /**
     * Adds the given Appointment to the given Appointment-File.
     * 
     * @param appointmentToAdd
     * @param fileWithAppointments
     * @throws IllegalArgumentException - if the given Appointment-File already
     *                                  contains the given Appointment.
     */
    public static void add(final Appointment appointmentToAdd, final File fileWithAppointments) throws IllegalArgumentException {
	if (contains(appointmentToAdd, fileWithAppointments)) {
	    throw new IllegalArgumentException("Appointment " + appointmentToAdd + " already exists.");
	}
	final List<Appointment> appointments = getAppointments(fileWithAppointments);
	appointments.add(appointmentToAdd);
	storeAppointments(appointments, fileWithAppointments);
    }

    /**
     * Removes the given Appointment from the given Appointment-File.
     * 
     * @param appointmentToRemove
     * @param fileWithAppointments
     * @throws IllegalArgumentException - if the given Appointment-File doesn't
     *                                  contain the given Appointment
     */
    public static void remove(final Appointment appointmentToRemove, final File fileWithAppointments) throws IllegalArgumentException {
	if (!contains(appointmentToRemove, fileWithAppointments)) {
	    throw new IllegalArgumentException("The given File doesn't contain the Appointment " + appointmentToRemove);
	}
	final List<Appointment> appointments = getAppointments(fileWithAppointments);
	appointments.remove(appointmentToRemove);
	storeAppointments(appointments, fileWithAppointments);
    }

    /**
     * Tests if the given Appointment-File contains the given Appointment.
     * 
     * @param appointment
     * @param fileWithAppointments
     * @return True, if the given appointment is already stored in the given File.
     */
    public static boolean contains(final Appointment appointment, final File fileWithAppointments) {
	final List<Appointment> appointments = getAppointments(fileWithAppointments);
	return appointments.contains(appointment);
    }

    public static List<Appointment> getAppointments() {
	return getAppointments(FILE_WITH_APPOINTMENTS);
    }

    /**
     * Calculates the List of Appointments represented by the given
     * Appointment-File.
     * 
     * @param fileWithAppointments
     * @return List of all Appointments represented by the given Appointment-File.
     */
    public static List<Appointment> getAppointments(final File fileWithAppointments) {
	final List<Appointment> appointmentsAsList = new ArrayList<>();
	final JSONArray appointmentsAsJSONArray = getJSONArrayOfAppointments(fileWithAppointments);
	for (int i = 0; i < appointmentsAsJSONArray.length(); i++) {
	    final JSONObject appointment = appointmentsAsJSONArray.getJSONObject(i);
	    appointmentsAsList.add(JSONTransformer.jsonToAppointment(appointment));
	}
	return appointmentsAsList;
    }

    /**
     * Stores and sorts the given Appointments to the given File.
     * 
     * @param appointmentsToStore
     * @param fileToStoreAt
     */
    private static void storeAppointments(final List<Appointment> appointmentsToStore, final File fileToStoreAt) {
	appointmentsToStore.sort((a1, a2) -> {
	    return a1.compareTo(a2);
	});
	final JSONArray appointmentsAsJSONArray = new JSONArray();
	for (final Appointment appointment : appointmentsToStore) {
	    appointmentsAsJSONArray.put(JSONTransformer.appointmentToJSON(appointment));
	}
	storeJSONArrayOfAppointments(appointmentsAsJSONArray, fileToStoreAt);
    }

    /**
     * Calculates the JSONArray represented by the given File.
     * 
     * @param fileWithAppointments
     * @return JSONArray represented by the content of the given File.
     */
    private static JSONArray getJSONArrayOfAppointments(final File fileWithAppointments) {
	try (final FileReader reader = new FileReader(fileWithAppointments);) {
	    final JSONTokener parser = new JSONTokener(reader);
	    return new JSONArray(parser);
	} catch (final IOException e) {
	    throw new RuntimeException("Couldn't read Appointment-File");
	}
    }

    /**
     * Stores the given JSONArray in the given File.
     * 
     * @param appointmentsToStore
     * @param fileToStoreAt
     */
    private static void storeJSONArrayOfAppointments(final JSONArray appointmentsToStore, final File fileToStoreAt) {
	try (final BufferedWriter writer = new BufferedWriter(new FileWriter(fileToStoreAt))) {
	    writer.write(appointmentsToStore.toString());
	} catch (IOException e) {
	    throw new RuntimeException("Couldn't write to Appointment-File");
	}
    }

    /**
     * Creates an empty, syntactically correct Appointment-File at the default path.
     */
    public static void createAppointmentFile() {
	createAppointmentFile(DEFAULT_APPOINTMENT_FILE_PATH);
    }

    /**
     * Creates an empty, syntactically correct Appointment-File at the given path.
     * 
     * @param path
     * @return The created Appointment-File.
     */
    public static File createAppointmentFile(final String path) {
	final File emptyAppointmentFile = new File(path);
	try (final BufferedWriter writer = new BufferedWriter(new FileWriter(emptyAppointmentFile))) {
	    writer.write("[]");
	} catch (IOException e) {
	    throw new RuntimeException("Couldn't create empty Appointment-File at" + path);
	}
	return emptyAppointmentFile;
    }

    public static File getAppointmentFile() {
	return FILE_WITH_APPOINTMENTS;
    }
}
