package main;

import java.io.File;
import javax.swing.JOptionPane;
import appointment.Appointment;
import fileInteraction.AppointmentAlreadyAddedException;
import fileInteraction.AppointmentFileInteracter;

/**
 * Entry-Point of the Adapter program which transforms the File of the older
 * version "Geburtstage.txt" to the new version "appointments.json".
 * 
 * @author Gabriel Glaser
 * @version 19.1.2022
 */
public final class Adapter {

    private static final File GEBURTSTAGE = new File("Geburtstage.txt");

    /**
     * Reads all birthdays from "Geburtstage.txt", adapts them to Appointments and
     * stores them in "appointments.json".
     * 
     * Visually depicts the errors if "Geburtstage.txt" doesn't exist,
     * "appointments.json" already exists, "Geburtstage.txt" doesn't have the
     * correct format and "Geburtstage.txt" contains two identical birthdays.
     * 
     * @param args - unused
     */
    public static void main(final String[] args) {
	if (!GEBURTSTAGE.exists()) {
	    final String errorTitle = "\"Geburtstage.txt\" nicht gefunden";
	    final String errorMessage = "\"Geburtstage.txt\" konte nicht gefunden werden. Die Datei sollte im selben Verzeichnis wie die .jar liegen.";
	    JOptionPane.showMessageDialog(null, errorMessage, errorTitle, JOptionPane.ERROR_MESSAGE);
	} else {
	    try {
		final AppointmentReader reader = new AppointmentReader(GEBURTSTAGE);
		AppointmentFileInteracter.createDefaultAppointmentFile();
		for (final Appointment appointmentToStore : reader.getAdaptedAppointments()) {
		    AppointmentFileInteracter.add(appointmentToStore);
		}
		final File appointmentFile = AppointmentFileInteracter.getDefaultAppointmentFile();
		final String title = "Adaption erfolgreich";
		final String message = "Die Geburtstage aus \"" + GEBURTSTAGE.getAbsolutePath() + "\"\nkonnten erfolgreich zu \"" + appointmentFile.getAbsolutePath() + "\"\nkonvertiert werden.";
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	    } catch (final IllegalFileFormatException e) {
		final String errorTitle = "Format-Fehler in \"Geburtstage.txt\"";
		final String errorMessage = "\"Geburtstage.txt\" hat nicht das richtige Format. Sie kann nicht zu JSON adaptiert werden.";
		JOptionPane.showMessageDialog(null, errorMessage, errorTitle, JOptionPane.ERROR_MESSAGE);
	    } catch (final IllegalArgumentException e) {
		final String errorTitle = "\"appointments.json\" existiert bereits";
		final String errorMessage = "Die Datei \"appointments.json\" existiert bereits und wird deswegen nicht überschrieben.";
		JOptionPane.showMessageDialog(null, errorMessage, errorTitle, JOptionPane.INFORMATION_MESSAGE);
	    } catch (final AppointmentAlreadyAddedException e) {
		AppointmentFileInteracter.getDefaultAppointmentFile().delete();
		final String errorTitle = "Duplikate in \"Geburtstage.txt\"";
		final String errorMessage = "Die Datei \"Geburtstage.txt\" enthält mindestens zwei identische Geburtstage. Das ist in der neuen Version nicht erlaubt.\n"
			+ "Bevor Sie das Programm erneut ausführen können, müssen alle Duplikate entfernt worden sein.";
		JOptionPane.showMessageDialog(null, errorMessage, errorTitle, JOptionPane.ERROR_MESSAGE);
	    }
	}
    }

}
