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
 * Entry class for the Reminder.
 * 
 * @author Gabriel Glaser
 * @version 4.4.2022
 */
public final class Reminder {

    /**
     * Reminder program which shows the appointments of today and tomorrow. Also, a
     * calendar is depicted to provide an overview of all appointments.
     * 
     * @param args - unused.
     */
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
	final List<Appointment> allAppointments = AppointmentFileInteracter.getAppointments();
	SwingUtilities.invokeLater(() -> {
	    new ReminderFrame(allAppointments);
	});
    }

}
