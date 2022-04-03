package main;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import appointment.Appointment;
import commonErrors.CommonErrors;
import fileInteraction.AppointmentAlreadyAddedException;
import fileInteraction.AppointmentFileInteracter;
import reader.AppointmentReader;
import reader.IllegalFileFormatException;

/**
 * Entry-Point of the Adapter program which transforms the File of the older
 * version "Geburtstage.txt" to the new version "appointments.json".
 * 
 * @author Gabriel Glaser
 * @version 29.3.2022
 */
public final class Adapter {

    private static final File GEBURTSTAGE = new File("Geburtstage.txt");

    /**
     * Reads all birthdays from "Geburtstage.txt", adapts them to Appointments and
     * stores them in "appointments.json".
     * 
     * @param args - unused
     */
    public static void main(final String[] args) {
	if (!GEBURTSTAGE.exists()) {
	    final String errorTitle = "\"Geburtstage.txt\" nicht gefunden";
	    final String errorMessage = "\"Geburtstage.txt\" konnte nicht gefunden werden. Die Datei sollte im selben Verzeichnis wie die .jar liegen.";
	    JOptionPane.showMessageDialog(null, errorMessage, errorTitle, JOptionPane.ERROR_MESSAGE);
	} else {
	    adaptBirthdays();
	}
    }

    /**
     * Adapts the entries of "Geburtstage.txt".
     * 
     * Visually depicts the errors if "Geburtstage.txt" doesn't exist,
     * "appointments.json" already exists, "Geburtstage.txt" doesn't have the
     * correct format and "Geburtstage.txt" contains two identical birthdays. If
     * there is an unexpected exception its content gets shown and the program
     * terminates.
     */
    private static void adaptBirthdays() {
	try {
	    final AppointmentReader reader = new AppointmentReader(GEBURTSTAGE);
	    AppointmentFileInteracter.createDefaultAppointmentFile();
	    for (final Appointment appointmentToStore : reader.getAdaptedAppointments()) {
		AppointmentFileInteracter.add(appointmentToStore);
	    }
	    showSuccessfulAdaption();
	} catch (final IllegalArgumentException e) {
	    showErrorOfAlreadyExistingAppointmentFile();
	} catch (final IllegalFileFormatException e) {
	    showErrorOfIllegalFileFormat(e);
	} catch (final AppointmentAlreadyAddedException e) {
	    showErrorOfDuplicateAppointment();
	} catch (final Exception e) {
	    CommonErrors.showUnexpectedExceptionAndExitProgram(e);
	}
    }

    private static void showSuccessfulAdaption() throws IOException {
	final File appointmentFile = AppointmentFileInteracter.getDefaultAppointmentFile();
	final String title = "Adaption erfolgreich";
	final String message = "Die Geburtstage aus \"" + GEBURTSTAGE.getAbsolutePath() + "\"\nkonnten erfolgreich zu \"" + appointmentFile.getAbsolutePath() + "\"\nkonvertiert werden.";
	final Icon checkIcon = new ImageIcon(ImageIO.read(Adapter.class.getClassLoader().getResourceAsStream("CheckIcon.png")).getScaledInstance(50, 50, Image.SCALE_SMOOTH));
	JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE, checkIcon);
    }

    private static void showErrorOfIllegalFileFormat(final IllegalFileFormatException e) {
	final String errorTitle = "Format-Fehler in \"Geburtstage.txt\"";
	JOptionPane.showMessageDialog(null, e.getMessage(), errorTitle, JOptionPane.ERROR_MESSAGE);
    }

    private static void showErrorOfAlreadyExistingAppointmentFile() {
	final String errorTitle = "\"appointments.json\" existiert bereits";
	final String errorMessage = "Die Datei \"appointments.json\" existiert bereits und wird deswegen nicht überschrieben.";
	JOptionPane.showMessageDialog(null, errorMessage, errorTitle, JOptionPane.INFORMATION_MESSAGE);
    }

    private static void showErrorOfDuplicateAppointment() {
	final String errorTitle = "Duplikate in \"Geburtstage.txt\"";
	final String errorMessage = "Die Datei \"Geburtstage.txt\" enthält mindestens zwei identische Geburtstage. Das ist in der neuen Version nicht erlaubt.\nBevor Sie das Programm erneut ausführen können, müssen alle Duplikate entfernt werden.";
	JOptionPane.showMessageDialog(null, errorMessage, errorTitle, JOptionPane.ERROR_MESSAGE);
    }

}
