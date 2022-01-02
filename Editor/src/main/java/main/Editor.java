package main;

import java.util.List;
import javax.swing.SwingUtilities;
import appointment.Appointment;
import ui.EditorFrame;
import fileInteraction.AppointmentFileInteracter;
import settings.Colors;

/**
 * Entry-Point for the Editor
 * 
 * @author Gabriel Glaser
 * @version 1.1.2022
 */
public class Editor {

    public static void main(String[] args) {
	Colors.setDarkModeEnabled(false);
	createAndShowGUI(AppointmentFileInteracter.getAppointments());
    }

    private static void createAndShowGUI(final List<Appointment> initialAppointments) {
	SwingUtilities.invokeLater(() -> {
	    new EditorFrame(initialAppointments);
	});
    }

}
