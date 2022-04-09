package commonErrors;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Calendar;

import javax.swing.JOptionPane;

/**
 * This class contains functions about common errors and handling in this
 * project.
 *
 * @author Gabriel Glaser
 * @version 4.04.2022
 */
public final class CommonErrors {

    /**
     * Every uncaught Throwable will be visualized in a JOptionPane and logged in a
     * File.
     */
    public static void setDefaultExceptionHandling() {
	Thread.setDefaultUncaughtExceptionHandler((thread, exception) -> {
	    showAndLogUnexpectedExceptionAndExitProgram(exception);
	});
    }

    /**
     * Depicts the given Exception in a JOptionPane and terminates the program after
     * that.
     *
     * @param exception
     */
    private static void showAndLogUnexpectedExceptionAndExitProgram(final Throwable exception) {
	final String title = "Unerwarteter Fehler.";
	final String message = "Es wurde eine Datei \"error.txt\" erstellt.\nBitte senden Sie mir sie bitte zu.\nDas Programm wird nun beendet.";
	JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
	logException(exception);
	System.exit(1);
    }

    /**
     * Prints the stack-trace of the given Exception in a file.
     *
     * @param exception
     */
    private static void logException(final Throwable exception) {
	try {
	    final File errorOutput = new File("error_" + getCurrentDateAndTime() + ".txt");
	    exception.printStackTrace(new PrintStream(errorOutput));
	} catch (final IOException e) {
	    final String title = "Fehler beim Fehler.";
	    final String message = "Der Fehler konnte leider nicht protokolliert werden.";
	    JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
	}
    }

    /**
     * @return String representation of the current time.
     */
    private static String getCurrentDateAndTime() {
	final Calendar calendar = Calendar.getInstance();
	return calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + "_" + calendar.get(Calendar.HOUR_OF_DAY) + "." + calendar.get(Calendar.MINUTE) + "." + calendar.get(Calendar.SECOND) + "." + calendar.get(Calendar.MILLISECOND);
    }

}
