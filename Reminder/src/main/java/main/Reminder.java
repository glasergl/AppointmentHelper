package main;

import java.io.File;
import java.util.List;
import javax.swing.SwingUtilities;
import appointment.Appointment;
import appointmentFileErrors.AppointmentFileError;
import fileInteraction.AppointmentFileInteracter;
import ui.ReminderFrame;

/**
 * Entry point for the Visualizer program which visualizes the appointments of
 * today and tomorrow in relation to the date when the program is started.
 * 
 * @author Gabriel Glaser
 * @version 29.3.2022
 */
public final class Reminder {

    public static void main(final String[] args) {
	final File appointmentFile = AppointmentFileInteracter.getDefaultAppointmentFile();
	if (!appointmentFile.exists()) {
	    AppointmentFileError.showCouldntFindAppointmentFile();
	} else {
	    createAndShowGUI();
	}
    }

    private static void createAndShowGUI() {
	final List<Appointment> appointmentsToConsider = AppointmentFileInteracter.getAppointments();
	SwingUtilities.invokeLater(() -> {
	    new ReminderFrame(appointmentsToConsider);
	});
    }

}
