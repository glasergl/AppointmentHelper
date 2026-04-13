package de.glasergl.appointment.helper.util.entity;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Class which contains generally applicable functions about appointments.
 *
 * @author glasergl
 */
public final class Appointments {
	/**
	 * @param appointments
	 * @param date
	 * @return A list of all appointments which are given and occur at the given
	 *         date.
	 */
	public static List<Appointment> getAppointmentsAtDate(final Collection<Appointment> appointments,
			final SimpleDate date) {
		final List<Appointment> collection = new LinkedList<>();
		for (final Appointment appointment : appointments) {
			if (appointment.occursAt(date)) {
				collection.add(appointment);
			}
		}
		return collection;
	}
}
