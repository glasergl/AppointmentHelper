package de.glasergl.main;

import java.util.List;

import javax.swing.SwingUtilities;

import de.glasergl.standard.errors.DefaultErrorHandling;
import de.glasergl.appointment.Appointment;
import de.glasergl.fileConfiguration.AppointmentsConfigurationHandler;
import de.glasergl.ui.CalendarFrame;

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
