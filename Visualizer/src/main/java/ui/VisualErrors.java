package ui;

import javax.swing.JOptionPane;

/**
 * Class to depict errors as JOptionPanes.
 * 
 * @author Gabriel Glaser
 * @version 10.9.2021
 */
public final class VisualErrors {

    public static void showCouldntFindAppointmentFileError() {
	final String title = "appointments.json nicht gefunden";
	final String message = "\"appointments.json\" konnte nicht gefunden werden. " + "Sie sollte im selben Verzeichnis wie die .jar liegen.\n"
		+ "Verschieben Sie die Datei in den Ordner der .jar oder öffnen Sie den TerminEditor.\nDaraufhin können Sie das Programm erneut starten.";
	JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

}
