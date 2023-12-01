package de.glasergl.appointment;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import de.glasergl.simpleDate.SimpleDate;

/**
 * Class which contains functions about Appointments.
 *
 * @author Gabriel Glaser
 */
public final class Appointments {

	/**
	 * Sorts the given appointments by the standard comparator.
	 *
	 * @param appointmentsToSort
	 */
	public static void sortAppointments(final List<Appointment> appointmentsToSort) {
		appointmentsToSort.sort((appointment1, appointment2) -> {
			return appointment1.compareTo(appointment2);
		});
	}

	/**
	 * @param appointments
	 * @param date
	 * @return A Collection so that for each Appointment a out of appointments:
	 *         a.isAt(date).
	 */
	public static List<Appointment> getAppointmentsAtDate(final Collection<Appointment> appointments,
			final SimpleDate date) {
		final List<Appointment> collection = new LinkedList<>();
		for (final Appointment appointment : appointments) {
			if (appointment.isAt(date)) {
				collection.add(appointment);
			}
		}
		return collection;
	}
}
