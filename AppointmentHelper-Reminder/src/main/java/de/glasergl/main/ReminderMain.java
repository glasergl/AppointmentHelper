package de.glasergl.main;

import java.util.List;

import javax.swing.SwingUtilities;

import de.glasergl.standard.errors.DefaultErrorHandling;
import de.glasergl.appointment.Appointment;
import de.glasergl.configuration.AppointmentsConfigurationHandler;
import de.glasergl.configuration.ConfigurationHandler;
import de.glasergl.ui.ReminderFrame;

/**
 * Entry class for the Reminder.
 *
 * @author Gabriel Glaser
 * @version 27.06.2022
 */
public final class ReminderMain {

	public static final ConfigurationHandler CONFIGURATION_HANDLER = new ConfigurationHandler();

	/**
	 * Reminder program which shows the appointments of today and tomorrow.
	 *
	 * @param args - unused.
	 */
	public static void main(final String[] args) {
		DefaultErrorHandling.activateDefaultExceptionHandling();
		final AppointmentsConfigurationHandler appointmentsHandler = CONFIGURATION_HANDLER.getAppointmentsHandler();
		final List<Appointment> allAppointments = appointmentsHandler.getAppointments();
		SwingUtilities.invokeLater(() -> {
			new ReminderFrame(allAppointments);
		});
	}

}
