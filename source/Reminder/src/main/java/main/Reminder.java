package main;

import java.io.File;
import java.util.List;
import javax.swing.SwingUtilities;
import appointment.Appointment;
import appointmentFileErrors.AppointmentFileErrors;
import commonErrors.CommonErrors;
import fileInteraction.AppointmentFileInteracter;
import ui.ReminderFrame;

/**
 * Entry point for the Reminder program which shows the appointments of today
 * and tomorrow at the time when the program is executed.
 * 
 * @author Gabriel Glaser
 * @version 4.4.2022
 */
public final class Reminder {

    public static void main(final String[] args) {
	CommonErrors.setDefaultExceptionHandling();
	final File appointmentFile = AppointmentFileInteracter.getDefaultAppointmentFile();
	if (!appointmentFile.exists()) {
	    AppointmentFileErrors.showCouldntFindAppointmentFile();
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
