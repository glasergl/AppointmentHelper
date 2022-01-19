package main;

import java.io.File;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import appointment.Appointment;
import ui.EditorFrame;
import fileInteraction.AppointmentFileInteracter;
import settings.Colors;

/**
 * Entry-Point for the Editor
 * 
 * @author Gabriel Glaser
 * @version 7.1.2022
 */
public class Editor {

    public static void main(String[] args) {
	Colors.setDarkModeEnabled(false);
	final File appointmentFile = AppointmentFileInteracter.getDefaultAppointmentFile();
	if (!appointmentFile.exists()) {
	    final boolean userCreatedNewAppointmentFile = askUserToCreateNewAppointmentFile();
	    if (userCreatedNewAppointmentFile) {
		createAndShowGUI();
	    }
	} else {
	    createAndShowGUI();
	}
    }

    private static boolean askUserToCreateNewAppointmentFile() {
	final String title = "\"appointments.json\" nicht gefunden";
	final String message = "Die Datei \"appointments.json\" konnte nicht gefunden werden. Sie sollte im selben Verzeichnis wie die .jar liegen.\n" + "Möchten Sie eine neue, leere erstellen?";
	final int answer = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
	if (answer == JOptionPane.YES_OPTION) {
	    AppointmentFileInteracter.createDefaultAppointmentFile();
	    return true;
	} else {
	    return false;
	}
    }

    private static void createAndShowGUI() {
	final List<Appointment> initialAppointments = AppointmentFileInteracter.getAppointments();
	SwingUtilities.invokeLater(() -> {
	    new EditorFrame(initialAppointments);
	});
    }

}
