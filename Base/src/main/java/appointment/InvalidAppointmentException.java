package appointment;

/**
 * RuntimeException to indicate that an Appointment is not valid.
 * 
 * An Appointment is considered as invalid if the name has a length of zero.
 * 
 * @author Gabriel Glaser
 * @version 14.03.2022
 */
public class InvalidAppointmentException extends RuntimeException {

    public InvalidAppointmentException() {
	super();
    }

}
