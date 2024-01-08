package reminderMain;

import java.util.List;

import javax.swing.SwingUtilities;

import appointment.Appointment;
import de.glasergl.standard.errors.DefaultErrorHandling;
import fileConfiguration.AppointmentsConfigurationHandler;
import reminderUI.ReminderFrame;

/**
 * Entry class for the Reminder.
 *
 * @author Gabriel Glaser
 */
public final class ReminderMain {
	/**
	 * Reminder program which retrieves all appointments from the file and shows the
	 * appointments of today and tomorrow. If no appointments occur today or
	 * tomorrow, then the program will exit and the user sees nothing.
	 *
	 * @param args - unused.
	 */
	public static void main(final String[] args) {
		DefaultErrorHandling.activateDefaultExceptionHandling();
		final AppointmentsConfigurationHandler appointmentsHandler = new AppointmentsConfigurationHandler();
		final List<Appointment> allAppointments = appointmentsHandler.getAppointments();
		SwingUtilities.invokeLater(() -> {
			new ReminderFrame(allAppointments);
		});
	}
}
