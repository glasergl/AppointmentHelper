package de.glasergl.appointment;

import de.glasergl.simpleDate.SimpleDate;
import de.glasergl.simpleDate.SimpleDates;

/**
 * Immutable class which contains all necessary attributes for an appointment.
 *
 * @author Gabriel Glaser
 */
public final class Appointment implements Comparable<Appointment> {
	private final SimpleDate date;
	private final String name;
	private final boolean isBirthday;

	/**
	 * @param date
	 * @param name
	 * @param isBirthday
	 * @throws IllegalArgumentException If "name" is an empty String.
	 */
	public Appointment(final SimpleDate date, final String name, final boolean isBirthday)
			throws IllegalArgumentException {
		if (name.length() == 0) {
			throw new IllegalArgumentException("The name has to contain atleast one character.");
		}
		this.date = date;
		this.name = name;
		this.isBirthday = isBirthday;
	}

	/**
	 * Constructor with date as the date the constructor is called.
	 * 
	 * @param name
	 * @param isBirthday
	 * @throws IllegalArgumentException If "name" is an empty String.
	 */
	public Appointment(final String name, final boolean isBirthday) throws IllegalArgumentException {
		this(SimpleDates.getToday(), name, isBirthday);
	}

	/**
	 * Constructor which sets "isBirthday" to true.
	 *
	 * @param date
	 * @param name
	 * @throws IllegalArgumentException If "name" is an empty String.
	 */
	public Appointment(final SimpleDate date, final String name) throws IllegalArgumentException {
		this(date, name, true);
	}

	/**
	 * Constructor which sets "isBirthday" to true and uses the date of today (at
	 * the time the constructor is called).
	 * 
	 * @param name
	 * @throws IllegalArgumentException If "name" is an empty String.
	 */
	public Appointment(final String name) {
		this(name, true);
	}

	public boolean isToday() {
		return date.isToday();
	}

	public boolean isTomorrow() {
		return date.isTomorrow();
	}

	public boolean occursAt(final SimpleDate date) {
		return this.date.equals(date);
	}

	/**
	 * @return date: name, description
	 */
	@Override
	public String toString() {
		return date + ": " + name;
	}

	@Override
	public boolean equals(final Object object) {
		if (!(object instanceof Appointment)) {
			return false;
		}
		final Appointment appointment = (Appointment) object;
		return date.equals(appointment.date) && name.equals(appointment.name) && isBirthday == appointment.isBirthday;
	}

	/**
	 * Calculates the order of this in comparison to toCompare.
	 *
	 * It is sorted by the compareTo() results of date > name > isBirthday
	 *
	 * @param appointment
	 * @return The value representing the order of this and the other given
	 *         appointment.
	 */
	@Override
	public int compareTo(final Appointment appointment) {
		final int dateCompareValue = date.compareTo(appointment.date);
		if (dateCompareValue != 0) {
			return dateCompareValue;
		} else {
			final int nameCompareValue = name.compareTo(appointment.name);
			if (nameCompareValue != 0) {
				return nameCompareValue;
			} else {
				if (isBirthday && !appointment.isBirthday) {
					return 1;
				} else if (!isBirthday && appointment.isBirthday) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

	public SimpleDate getDate() {
		return date;
	}

	public String getName() {
		return name;
	}

	public boolean isBirthday() {
		return isBirthday;
	}
}
