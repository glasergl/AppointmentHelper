package de.glasergl.main;

import java.util.List;

import javax.swing.SwingUtilities;

import de.glasergl.standard.errors.DefaultErrorHandling;
import de.glasergl.appointment.Appointment;
import de.glasergl.configuration.AppointmentsConfigurationHandler;
import de.glasergl.configuration.ConfigurationHandler;
import de.glasergl.ui.EditorFrame;

/**
 * Entry-Point for the Editor.
 *
 * @author Gabriel Glaser
 * @version 11.2.2022
 */
public class EditorMain {

    public static final ConfigurationHandler CONFIGURATION_HANDLER = new ConfigurationHandler();
    public static final AppointmentsConfigurationHandler APPOINTMENTS_HANDLER = CONFIGURATION_HANDLER.getAppointmentsHandler();

    /**
     * The Editor depicts all stored Appointments of the default Appointment-File
     * and allows to edit them.
     *
     * If the Appointment-File doesn't exist, the user is asked whether he wants to
     * create a new, empty Appointment-File.
     *
     * @param args - unused
     */
    public static void main(final String[] args) {
	DefaultErrorHandling.activateDefaultExceptionHandling();
	createAndShowGUI();
    }

    private static void createAndShowGUI() {
	final List<Appointment> initialAppointments = APPOINTMENTS_HANDLER.getAppointments();
	SwingUtilities.invokeLater(() -> {
	    new EditorFrame(initialAppointments);
	});
    }

}
