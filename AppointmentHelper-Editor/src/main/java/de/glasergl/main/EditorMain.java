package de.glasergl.main;

import javax.swing.SwingUtilities;

import de.glasergl.configuration.ConfigurationHandler;
import de.glasergl.standard.errors.DefaultErrorHandling;
import de.glasergl.ui.EditorFrame;

/**
 * Entry-Point for the Editor.
 *
 * @author Gabriel Glaser
 * @version 1.7.2022
 */
public class EditorMain {

	/**
	 * The Editor depicts all stored Appointments of the default Appointment-File
	 * and allows to edit them.
	 *
	 * If the Appointment-File doesn't exist, the user is asked whether he wants to
	 * create a new, empty Appointment-File.
	 *
	 * @param args - unused
	 */
	public static void main(final String[] args) {
		DefaultErrorHandling.activateDefaultExceptionHandling();
		final ConfigurationHandler configurationHandler = new ConfigurationHandler();
		SwingUtilities.invokeLater(() -> {
			new EditorFrame(configurationHandler);
		});
	}

}
