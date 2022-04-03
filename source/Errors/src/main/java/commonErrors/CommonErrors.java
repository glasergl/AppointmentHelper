package commonErrors;

import javax.swing.JOptionPane;

/**
 * This class is about common errors and handling in this project.
 * 
 * @author Gabriel Glaser
 * @version 29.03.2022
 */
public class CommonErrors {

    /**
     * Runs the given Runnable and catches an uncaught exception, depicts it in a
     * JOptionPane and terminates the program.
     * 
     * @param program
     */
    public static void startProgramWithErrorHandling(final Runnable program) {
	try {
	    program.run();
	} catch (final Exception exception) {
	    showUnexpectedExceptionAndExitProgram(exception);
	}
    }

    /**
     * Depicts the given Exception in a JOptionPane and terminates the program.
     * 
     * @param exception
     */
    public static void showUnexpectedExceptionAndExitProgram(final Exception exception) {
	final String title = "Eine unerwartete Exception wurde geworfen";
	final String stackTrace = calculateExceptionRepresentation(exception) + "\n\nBitte sende mir einen Screenshot von diesem Fenster per Discord, das Programm wird nun beendet.";
	JOptionPane.showMessageDialog(null, stackTrace, title, JOptionPane.ERROR_MESSAGE);
	System.exit(1);
    }

    /**
     * @param exception
     * @return A String representation of the given Exception.
     */
    private static String calculateExceptionRepresentation(final Exception exception) {
	final StringBuilder exceptionRepresentation = new StringBuilder();
	exceptionRepresentation.append(exception.getClass() + ": \"" + exception.getMessage() + "\"\n");
	final StackTraceElement[] stackTrace = exception.getStackTrace();
	for (int i = 0; i < stackTrace.length; i++) {
	    exceptionRepresentation.append(stackTrace[i].toString());
	    if (i < stackTrace.length - 1) {
		exceptionRepresentation.append("\n");
	    }
	}
	return exceptionRepresentation.toString();
    }

}
