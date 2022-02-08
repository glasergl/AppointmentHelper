package appointment;

/**
 * Exception to indicate that an Appointment is not valid.
 * 
 * An Appointment is considered as invalid if the name has a length of zero.
 * 
 * @author Gabriel Glaser
 * @version 08.02.2022
 */
public class InvalidAppointmentException extends Exception {

    public InvalidAppointmentException() {
	super();
    }

}
