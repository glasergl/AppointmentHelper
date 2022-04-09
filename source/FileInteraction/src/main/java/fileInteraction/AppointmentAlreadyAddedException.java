package fileInteraction;

import appointment.Appointment;

/**
 * Exception to indicate that an Appointment has already been added to the
 * Appointment-File.
 *
 * @author Gabriel Glaser
 * @version 19.01.2022
 */
public final class AppointmentAlreadyAddedException extends RuntimeException {

    public AppointmentAlreadyAddedException(final Appointment appointmentWhichWasAlreadyAdded) {
	super("Appointment " + appointmentWhichWasAlreadyAdded + " was already added.");
    }

}
