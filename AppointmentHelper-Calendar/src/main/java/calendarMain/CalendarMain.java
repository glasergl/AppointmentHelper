package calendarMain;

import java.util.List;

import javax.swing.SwingUtilities;

import appointment.Appointment;
import calendarUI.CalendarFrame;
import standardGlaserGl.errors.DefaultErrorHandling;
import fileConfiguration.AppointmentsConfigurationHandler;

/**
 * Entry point for the Calendar.
 *
 * @author Gabriel Glaser
 */
public final class CalendarMain {
	/**
	 * Creates a Window with an overview of all Appointments which are stored in the
	 * configurationFile.
	 *
	 * @param args - unused
	 */
	public static void main(final String[] args) {
		DefaultErrorHandling.activateDefaultExceptionHandling();
		final AppointmentsConfigurationHandler appointmentsHandler = new AppointmentsConfigurationHandler();
		final List<Appointment> allAppointments = appointmentsHandler.getAppointments();
		SwingUtilities.invokeLater(() -> {
			new CalendarFrame(allAppointments);
		});
	}
}
