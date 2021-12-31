package appointment;

/**
 * Immutable class which contains all necessary attributes for an appointment.
 * 
 * @author Gabriel Glaser
 * @version 31.12.2021
 */
public class Appointment implements Comparable<Appointment> {

    private static final String DEFAULT_DESCRIPTION = "";
    private static final SimpleDate DEFAULT_DATE = SimpleDate.getToday();
    private static final boolean DEFAULT_IS_A_BIRTHDAY = true;

    private final SimpleDate date;
    private final String name;
    private final String description;
    private final boolean isABirthday;

    /**
     * @param date
     * @param name
     * @param description
     * @param isABirthday
     * @throws IllegalArgumentException - If the name is an empty String.
     */
    public Appointment(final SimpleDate date, final String name, final String description, final boolean isABirthday) throws IllegalArgumentException {
	if (name.length() == 0) {
	    throw new IllegalArgumentException("The name has to contain atleast one character.");
	}
	this.date = date;
	this.name = name;
	this.description = description;
	this.isABirthday = isABirthday;
    }

    /**
     * Constructor with default empty description.
     * 
     * @param date
     * @param name
     * @param isABirthday
     * @throws IllegalArgumentException - If the name is an empty String.
     */
    public Appointment(final SimpleDate date, final String name, final boolean isABirthday) throws IllegalArgumentException {
	this(date, name, DEFAULT_DESCRIPTION, isABirthday);
    }

    /**
     * Constructor with default date (today) and empty description.
     * 
     * @param name
     * @param isABirthday
     * @throws IllegalArgumentException - If the name is an empty String.
     */
    public Appointment(final String name, final boolean isABirthday) throws IllegalArgumentException {
	this(DEFAULT_DATE, name, isABirthday);
    }

    /**
     * Constructor with default date (today), empty description and is a Birthday.
     * 
     * @param name
     * @throws IllegalArgumentException - If the given name is an empty String.
     */
    public Appointment(final String name) throws IllegalArgumentException {
	this(name, DEFAULT_IS_A_BIRTHDAY);
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
    public boolean equals(final Object objectToCompare) {
	if (!(objectToCompare instanceof Appointment)) {
	    return false;
	}
	final Appointment appointmentToCompare = (Appointment) objectToCompare;
	return date.equals(appointmentToCompare.date) && name.equals(appointmentToCompare.name) && description.equals(appointmentToCompare.description)
		&& isABirthday == appointmentToCompare.isABirthday;
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
