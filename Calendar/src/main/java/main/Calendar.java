package main;

import java.io.File;
import java.util.List;
import javax.swing.SwingUtilities;
import appointment.Appointment;
import appointmentFileErrors.AppointmentFileError;
import fileInteraction.AppointmentFileInteracter;
import ui.CalendarFrame;

public class Calendar {

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
	    new CalendarFrame(appointmentsToConsider);
	});
    }

}
