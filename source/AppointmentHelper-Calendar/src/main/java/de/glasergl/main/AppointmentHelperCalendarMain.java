package de.glasergl.main;

import java.io.File;
import java.util.List;

import javax.swing.SwingUtilities;

import de.glasergl.standard.errors.DefaultErrorHandling;
import de.glasergl.appointment.Appointment;
import de.glasergl.appointmentFileErrors.AppointmentFileErrors;
import de.glasergl.fileInteraction.AppointmentFileInteracter;
import de.glasergl.ui.CalendarFrame;

/**
 * Entry point for the Calendar.
 *
 * @author Gabriel Glaser
 * @version 4.04.2022
 */
public final class AppointmentHelperCalendarMain {

    /**
     * Creates a Frame with an overview of all Appointments which are stored in the
     * default Appointment-File.
     *
     * @param args - unused
     */
    public static void main(final String[] args) {
	DefaultErrorHandling.activateDefaultExceptionHandling();
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
