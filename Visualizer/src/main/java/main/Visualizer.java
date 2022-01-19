package main;

import java.io.File;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import appointment.Appointment;
import fileInteraction.AppointmentFileInteracter;
import settings.Colors;
import ui.VisualizerFrame;

/**
 * Entry point for the Visualizer program which visualizes the appointments of
 * today and tomorrow in relation to the date when the program is started.
 * 
 * @author Gabriel Glaser
 * @version 6.1.2022
 */
public final class Visualizer {

    public static void main(final String[] args) {
	Colors.setDarkModeEnabled(false);
	final File appointmentFile = AppointmentFileInteracter.getDefaultAppointmentFile();
	if (!appointmentFile.exists()) {
	    showCouldntFindAppointmentFileError();
	} else {
	    createAndShowGUI();
	}
    }

    private static void createAndShowGUI() {
	final List<Appointment> appointmentsToConsider = AppointmentFileInteracter.getAppointments();
	SwingUtilities.invokeLater(() -> {
	    new VisualizerFrame(appointmentsToConsider);
	});
    }

    private static void showCouldntFindAppointmentFileError() {
	final String title = "\"appointments.json\" nicht gefunden";
	final String message = "Die Datei \"appointments.json\" konnte nicht gefunden werden. Sie sollte im selben Verzeichnis wie die .jar liegen.\n"
		+ "Verschieben Sie die Datei in den Ordner der .jar oder öffnen Sie den TerminEditor.\n" + "Daraufhin können Sie das Programm erneut starten.";
	JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

}
