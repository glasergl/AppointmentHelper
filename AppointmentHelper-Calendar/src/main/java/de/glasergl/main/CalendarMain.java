package de.glasergl.main;

import java.util.List;

import javax.swing.SwingUtilities;

import de.glasergl.standard.errors.DefaultErrorHandling;
import de.glasergl.appointment.Appointment;
import de.glasergl.configuration.AppointmentsConfigurationHandler;
import de.glasergl.configuration.ConfigurationHandler;
import de.glasergl.ui.CalendarFrame;

/**
 * Entry point for the Calendar.
 *
 * @author Gabriel Glaser
 * @version 18.06.2022
 */
public final class CalendarMain {

    public static final ConfigurationHandler CONFIGURATION_HANDLER = new ConfigurationHandler();

    /**
     * Creates a Window with an overview of all Appointments which are stored in the
     * configurationFile.
     *
     * @param args - unused
     */
    public static void main(final String[] args) {
	DefaultErrorHandling.activateDefaultExceptionHandling();
	createAndShowGUI();
    }

    private static void createAndShowGUI() {
	final AppointmentsConfigurationHandler appointmentsHandler = CONFIGURATION_HANDLER.getAppointmentsHandler();
	final List<Appointment> allAppointments = appointmentsHandler.getAppointments();
	SwingUtilities.invokeLater(() -> {
	    new CalendarFrame(allAppointments);
	});
    }

}
