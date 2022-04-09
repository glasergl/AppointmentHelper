package main;

import java.io.File;
import java.util.List;

import javax.swing.SwingUtilities;

import appointment.Appointment;
import appointmentFileErrors.AppointmentFileErrors;
import commonErrors.CommonErrors;
import fileInteraction.AppointmentFileInteracter;
import ui.CalendarFrame;

/**
 * Entry point for the Calendar.
 *
 * @author Gabriel Glaser
 * @version 4.04.2022
 */
public final class Calendar {

    /**
     * Creates a Frame with an overview of all Appointments which are stored in the
     * default Appointment-File.
     *
     * @param args - unused
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
	    new CalendarFrame(allAppointments);
	});
    }

}
