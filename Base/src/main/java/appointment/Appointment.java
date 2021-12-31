package appointment;

/**
 * Immutable class which contains all necessary attributes for an appointment.
 * 
 * @author Gabriel Glaser
 * @version 14.11.2021
 */
public class Appointment implements Comparable<Appointment> {

    private final SimpleDate date;
    private final String name;
    private final String description;
    private final boolean isABirthday;

    public Appointment(final SimpleDate date, final String name, final String description, final boolean isABirthday) throws IllegalArgumentException {
	if (name.length() == 0) {
	    throw new IllegalArgumentException("The name has to contain atleast one character.");
	}
	this.date = date;
	this.name = name;
	this.description = description;
	this.isABirthday = isABirthday;
    }

    public Appointment(final SimpleDate date, final String name, final boolean isABirthday) throws IllegalArgumentException {
	this(date, name, "", isABirthday);
    }

    public Appointment(final String name, final boolean isABirthday) throws IllegalArgumentException {
	this(SimpleDate.getToday(), name, isABirthday);
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

    @Override
    public int compareTo(final Appointment toCompare) {
	return date.compareTo(toCompare.date);
    }

    @Override
    public String toString() {
	return date + ": " + name + ", " + description;
    }

    @Override
    public boolean equals(final Object toCompare) {
	if (!(toCompare instanceof Appointment)) {
	    return false;
	} else {
	    final Appointment appointmentToCompare = (Appointment) toCompare;
	    return date.equals(appointmentToCompare.date) && name.equals(appointmentToCompare.name) && description.equals(appointmentToCompare.description)
		    && isABirthday == appointmentToCompare.isABirthday;
	}
    }

    public SimpleDate getDate() {
	return date;
    }

    public String getName() {
	return name;
    }

    public String getDescription() {
	return description;
    }

    public boolean isABirthday() {
	return isABirthday;
    }

}
