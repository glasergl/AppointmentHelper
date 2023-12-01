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
	 * Constructor with default date (today) and empty description.
	 *
	 * @param name
	 * @param isBirthday
	 * @throws IllegalArgumentException - If name is an empty String.
	 */
	public Appointment(final String name, final boolean isBirthday) throws IllegalArgumentException {
		this(SimpleDates.getToday(), name, isBirthday);
	}

	/**
	 * Constructor with default empty description and is a birthday.
	 *
	 * @param date
	 * @param name
	 * @throws IllegalArgumentException - If name is an empty String.
	 */
	public Appointment(final SimpleDate date, final String name) throws IllegalArgumentException {
		this(date, name, true);
	}

	/**
	 * Constructor with default date (today), empty description and is a birthday.
	 *
	 * @param name
	 * @throws IllegalArgumentException - If name is an empty String.
	 */
	public Appointment(final String name) throws IllegalArgumentException {
		this(name, true);
	}

	public boolean isToday() {
		return date.isToday();
	}

	public boolean isTomorrow() {
		return date.isTomorrow();
	}

	public boolean isAt(final SimpleDate toTest) {
		return date.equals(toTest);
	}

	/**
	 * @return date: name, description
	 */
	@Override
	public String toString() {
		return date + ": " + name;
	}

	@Override
	public boolean equals(final Object objectToCompare) {
		if (!(objectToCompare instanceof Appointment)) {
			return false;
		}
		final Appointment appointmentToCompare = (Appointment) objectToCompare;
		return date.equals(appointmentToCompare.date) && name.equals(appointmentToCompare.name)
				&& isBirthday == appointmentToCompare.isBirthday;
	}

	/**
	 * Calculates the order of this in comparison to toCompare.
	 *
	 * It is sorted by the compareTo() results of date > name > description >
	 * isBirthday
	 *
	 * @param toCompare
	 * @return The value representing the order of this and toCompare.
	 */
	@Override
	public int compareTo(final Appointment toCompare) {
		final int dateCompareValue = date.compareTo(toCompare.date);
		if (dateCompareValue != 0) {
			return dateCompareValue;
		} else {
			final int nameCompareValue = name.compareTo(toCompare.name);
			if (nameCompareValue != 0) {
				return nameCompareValue;
			} else {
				if (isBirthday && !toCompare.isBirthday) {
					return 1;
				} else if (!isBirthday && toCompare.isBirthday) {
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
