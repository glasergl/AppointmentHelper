package fileInteraction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import appointment.Appointment;

/**
 * Class which contains functions to interact with a File of Appointments.
 * 
 * Usually, you shouldn't give a File to the functions as an argument. The
 * overloaded function exists for testing.
 * 
 * @author Gabriel Glaser
 * @version 19.1.2022
 */
public final class AppointmentFileInteracter {

    private static final String DEFAULT_APPOINTMENT_FILE_PATH = "appointments.json";
    private static final File DEFAULT_FILE_WITH_APPOINTMENTS = new File(DEFAULT_APPOINTMENT_FILE_PATH);
    private static final int APPOINTMENT_FILE_INDENT_SIZE = 3;

    private AppointmentFileInteracter() {
    }

    /**
     * Adds the given Appointment to the default Appointment-File.
     * 
     * @param appointmentToAdd
     * @throws AppointmentAlreadyAddedException If the default Appointment-File
     *                                          already contains the given
     *                                          Appointment.
     */
    public static void add(final Appointment appointmentToAdd) throws AppointmentAlreadyAddedException {
	add(appointmentToAdd, DEFAULT_FILE_WITH_APPOINTMENTS);
    }

    /**
     * Removes the given Appointment from the default Appointment-File.
     * 
     * @param appointmentToRemove
     * @throws NoSuchElementException If the standard Appointment-File already
     *                                contains the given Appointment.
     */
    public static void remove(final Appointment appointmentToRemove) throws NoSuchElementException {
	remove(appointmentToRemove, DEFAULT_FILE_WITH_APPOINTMENTS);
    }

    /**
     * Tests if the default Appointment-File contains the specified Appointment.
     * 
     * @param appointmentToTest
     * @return True if the standard Appointment-File contains the specified
     *         Appointment.
     */
    public static boolean contains(final Appointment appointmentToTest) {
	return contains(appointmentToTest, DEFAULT_FILE_WITH_APPOINTMENTS);
    }

    /**
     * Calculates the List of Appointments represented by the default
     * Appointment-File.
     * 
     * @return List of all Appointments represented by the default Appointment-File.
     */
    public static List<Appointment> getAppointments() {
	return getAppointments(DEFAULT_FILE_WITH_APPOINTMENTS);
    }

    /**
     * Adds the given Appointment to the given Appointment-File.
     * 
     * @param appointmentToAdd
     * @param fileWithAppointments
     * @throws AppointmentAlreadyAddedException If the given Appointment-File
     *                                          already contains the given
     *                                          Appointment.
     */
    public static void add(final Appointment appointmentToAdd, final File fileWithAppointments) throws AppointmentAlreadyAddedException {
	if (contains(appointmentToAdd, fileWithAppointments)) {
	    throw new AppointmentAlreadyAddedException(appointmentToAdd);
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
     * @throws NoSuchElementException If the given Appointment-File doesn't contain
     *                                the given Appointment
     */
    public static void remove(final Appointment appointmentToRemove, final File fileWithAppointments) throws NoSuchElementException {
	if (!contains(appointmentToRemove, fileWithAppointments)) {
	    throw new NoSuchElementException("The given File doesn't contain the Appointment " + appointmentToRemove);
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
	    appointmentsAsList.add(AppointmentJSONTransformer.jsonToAppointment(appointment));
	}
	return appointmentsAsList;
    }

    /**
     * Sorts and stores the given Appointments to the given File.
     * 
     * @param appointmentsToStore
     * @param fileToStoreAt
     */
    private static void storeAppointments(final List<Appointment> appointmentsToStore, final File fileToStoreAt) {
	Appointment.sortAppointments(appointmentsToStore);
	final JSONArray appointmentsAsJSONArray = new JSONArray();
	for (final Appointment appointment : appointmentsToStore) {
	    appointmentsAsJSONArray.put(AppointmentJSONTransformer.appointmentToJSON(appointment));
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
	if (!fileWithAppointments.exists()) {
	    throw new IllegalArgumentException("The given Appointment-File doesn't exist.");
	}
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
	if (!fileToStoreAt.exists()) {
	    throw new IllegalArgumentException("The given Appointment-File doesn't exist.");
	}
	try (final BufferedWriter writer = new BufferedWriter(new FileWriter(fileToStoreAt))) {
	    final String[] jsonLines = appointmentsToStore.toString(APPOINTMENT_FILE_INDENT_SIZE).split("\n");
	    for (final String line : jsonLines) {
		writer.write(line);
		writer.newLine();
	    }
	} catch (final IOException e) {
	    throw new RuntimeException("Couldn't write to Appointment-File");
	}
    }

    public static File getDefaultAppointmentFile() {
	return DEFAULT_FILE_WITH_APPOINTMENTS;
    }

    /**
     * Creates an empty, syntactically correct Appointment-File at the default path.
     * 
     * @throws IllegalArgumentException If the default path already represents an
     *                                  existing File.
     */
    public static void createDefaultAppointmentFile() throws IllegalArgumentException {
	createEmptyAppointmentFile(DEFAULT_APPOINTMENT_FILE_PATH);
    }

    /**
     * Creates an empty, syntactically correct Appointment-File at the given path.
     * 
     * @param pathOfTheNewAppointmentFile
     * @throws IllegalArgumentException If the given path already represents an
     *                                  existing File.
     * @return The created Appointment-File.
     */
    public static File createEmptyAppointmentFile(final String pathOfTheNewAppointmentFile) throws IllegalArgumentException {
	final File emptyAppointmentFile = new File(pathOfTheNewAppointmentFile);
	if (emptyAppointmentFile.exists()) {
	    throw new IllegalArgumentException("Appointment-File at " + pathOfTheNewAppointmentFile + " already exists.");
	}
	try (final BufferedWriter writer = new BufferedWriter(new FileWriter(emptyAppointmentFile))) {
	    writer.write("[");
	    writer.newLine();
	    writer.newLine();
	    writer.write("]");
	} catch (final IOException e) {
	    throw new RuntimeException("Couldn't write initial text to Appointment-File at " + pathOfTheNewAppointmentFile);
	}
	return emptyAppointmentFile;
    }
}
