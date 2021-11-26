package appointments;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import main.AppointmentHelper;

/**
 * Class which contains methods to interact with a File of appointments.
 * 
 * @author Gabriel Glaser
 * @version 10.9.2021
 */
public final class AppointmentInteracter {

	public static List<Appointment> getAppointments() {
		return getAppointments(AppointmentHelper.WITH_APPOINTMENTS);
	}

	public static void add(final Appointment toAdd) {
		add(toAdd, AppointmentHelper.WITH_APPOINTMENTS);
	}

	public static void remove(final Appointment toAdd) {
		remove(toAdd, AppointmentHelper.WITH_APPOINTMENTS);
	}

	public static boolean contains(final Appointment toTest) {
		return contains(toTest, AppointmentHelper.WITH_APPOINTMENTS);
	}

	public static void updateAppointment(final Appointment toUpdate) {
		updateAppointment(toUpdate, AppointmentHelper.WITH_APPOINTMENTS);
	}

	public static List<Appointment> getAppointments(final File withAppointments) {
		final JSONArray appointmentsAsJSON = getJSONArrayOfAppointments(withAppointments);
		final List<Appointment> appointments = new ArrayList<>();
		for (int i = 0; i < appointmentsAsJSON.length(); i++) {
			final JSONObject appointment = appointmentsAsJSON.getJSONObject(i);
			appointments.add(Appointment.jsonToAppointment(appointment));
		}
		return appointments;
	}

	public static void add(final Appointment toAdd, final File withAppointments) {
		final List<Appointment> appointments = getAppointments(withAppointments);
		appointments.add(toAdd);
		updateAppointments(appointments, withAppointments);
	}

	public static void remove(final Appointment toAdd, final File withAppointments) {
		final List<Appointment> appointments = getAppointments(withAppointments);
		appointments.remove(toAdd);
		updateAppointments(appointments, withAppointments);
	}

	public static boolean contains(final Appointment toTest, final File withAppointments) {
		final List<Appointment> appointments = getAppointments(withAppointments);
		return appointments.contains(toTest);
	}

	public static void updateAppointment(final Appointment toUpdate, final File withAppointments) {
		final List<Appointment> appointments = getAppointments(withAppointments);
		for (int i = 0; i < appointments.size(); i++) {
			final Appointment appointment = appointments.get(i);
			if (appointment.getID() == toUpdate.getID()) {
				appointments.remove(appointment);
				appointments.add(toUpdate);
			}
		}
		updateAppointments(appointments, withAppointments);
	}

	private static void updateAppointments(final List<Appointment> newListOfAppointmentsToStore,
			final File withAppointments) {
		try (final BufferedWriter writer = new BufferedWriter(new FileWriter(withAppointments));) {
			withAppointments.delete();
			Collections.sort(newListOfAppointmentsToStore);
			final JSONArray appointmentsAsJSON = new JSONArray();
			for (final Appointment toAdd : newListOfAppointmentsToStore) {
				appointmentsAsJSON.put(toAdd.toJSON());
			}
			writer.write(appointmentsAsJSON.toString(1));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static JSONArray getJSONArrayOfAppointments(final File withAppointments) {
		try (final FileReader reader = new FileReader(withAppointments);) {
			final JSONTokener parser = new JSONTokener(reader);
			return new JSONArray(parser);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
