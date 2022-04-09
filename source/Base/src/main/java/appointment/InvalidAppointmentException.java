package appointment;

/**
 * RuntimeException to indicate that an Appointment is not valid.
 *
 * An Appointment is considered as invalid if the name has a length of zero.
 *
 * @author Gabriel Glaser
 * @version 29.03.2022
 */
public final class InvalidAppointmentException extends RuntimeException {

    public InvalidAppointmentException() {
	super();
    }

}
