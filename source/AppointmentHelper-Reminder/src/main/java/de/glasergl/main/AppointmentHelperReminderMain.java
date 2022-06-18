package de.glasergl.main;

import java.io.File;
import java.util.List;

import javax.swing.SwingUtilities;

import de.glasergl.standard.errors.DefaultErrorHandling;
import de.glasergl.appointment.Appointment;
import de.glasergl.appointmentFileErrors.AppointmentFileErrors;
import de.glasergl.configuration.ConfigurationHandler;
import de.glasergl.ui.ReminderFrame;

/**
 * Entry class for the Reminder.
 *
 * @author Gabriel Glaser
 * @version 4.4.2022
 */
public final class AppointmentHelperReminderMain {

    /**
     * Reminder program which shows the appointments of today and tomorrow. Also, a
     * calendar is depicted to provide an overview of all appointments.
     *
     * @param args - unused.
     */
    public static void main(final String[] args) {
	DefaultErrorHandling.activateDefaultExceptionHandling();
	final File appointmentFile = ConfigurationHandler.getDefaultAppointmentFile();
	if (!appointmentFile.exists()) {
	    AppointmentFileErrors.showCouldntFindAppointmentFile();
	} else {
	    createAndShowGUI();
	}
    }

    private static void createAndShowGUI() {
	final List<Appointment> allAppointments = ConfigurationHandler.getAppointments();
	SwingUtilities.invokeLater(() -> {
	    new ReminderFrame(allAppointments);
	});
    }

}
