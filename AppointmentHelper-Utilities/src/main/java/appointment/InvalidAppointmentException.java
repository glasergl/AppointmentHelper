package appointment;

/**
 * RuntimeException to indicate that an Appointment is not valid. An appointment
 * is considered invalid if the name has a length of zero.
 *
 * @author Gabriel Glaser
 */
public final class InvalidAppointmentException extends RuntimeException {
	public InvalidAppointmentException() {
		super();
	}
}
