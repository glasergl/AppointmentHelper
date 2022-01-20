package main;

import java.io.File;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import appointment.Appointment;
import ui.EditorFrame;
import fileInteraction.AppointmentFileInteracter;

/**
 * Entry-Point for the Editor.
 * 
 * @author Gabriel Glaser
 * @version 20.1.2022
 */
public class Editor {

    /**
     * The Editor depicts all stored Appointments of the default Appointment-File
     * and allows to edit them.
     * 
     * If the Appointment-File doesn't exist, the user is asked whether he wants to
     * create a new, empty Appointment-File.
     * 
     * @param args - unused
     */
    public static void main(String[] args) {
	final File appointmentFile = AppointmentFileInteracter.getDefaultAppointmentFile();
	if (!appointmentFile.exists()) {
	    final boolean userWantsNewAppointmentFile = askUserToCreateNewAppointmentFile();
	    if (userWantsNewAppointmentFile) {
		AppointmentFileInteracter.createDefaultAppointmentFile();
		createAndShowGUI();
	    }
	} else {
	    createAndShowGUI();
	}
    }

    /**
     * Asks the user whether he wants to create a new Appointment-File with a
     * ownerless JOptionPane.
     * 
     * @return True, if the User wants to create a new Appointment-File, else,
     *         false.
     */
    private static boolean askUserToCreateNewAppointmentFile() {
	final String title = "\"appointments.json\" nicht gefunden";
	final String message = "Die Datei \"appointments.json\" konnte nicht gefunden werden. Sie sollte im selben Verzeichnis wie die .jar liegen.\n" + "Möchten Sie eine neue, leere erstellen?";
	final int answer = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
	return answer == JOptionPane.YES_OPTION;
    }

    /**
     * Retrieves all Appointments out of the Appointment-File and creates, shows the
     * EditorFrame on the EDT.
     */
    private static void createAndShowGUI() {
	final List<Appointment> initialAppointments = AppointmentFileInteracter.getAppointments();
	SwingUtilities.invokeLater(() -> {
	    new EditorFrame(initialAppointments);
	});
    }

}
