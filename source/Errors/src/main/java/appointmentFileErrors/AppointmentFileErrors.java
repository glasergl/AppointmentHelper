package appointmentFileErrors;

import javax.swing.JOptionPane;

/**
 * Class which contains function which depict errors with the appointment-File.
 * 
 * @author Gabriel Glaser
 * @version 29.03.2022
 */
public class AppointmentFileErrors {

    public static void showCouldntFindAppointmentFile() {
	final String title = "\"appointments.json\" nicht gefunden";
	final String message = "Die Datei \"appointments.json\" konnte nicht gefunden werden. Sie sollte im selben Verzeichnis wie die .jar liegen.\n"
		+ "Verschieben Sie die Datei in den Ordner der .jar oder öffnen Sie den TerminEditor.\n" + "Daraufhin können Sie das Programm erneut starten.";
	JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

}
