package de.glasergl.main;

import javax.swing.SwingUtilities;

import de.glasergl.fileConfiguration.AppointmentsConfigurationHandler;
import de.glasergl.standard.errors.DefaultErrorHandling;
import de.glasergl.ui.EditorFrame;

/**
 * Entry-Point for the Editor.
 *
 * @author Gabriel Glaser
 */
public class EditorMain {
	/**
	 * The Editor depicts all currently stored Appointments of the default
	 * Appointment-File and allows to edit them.
	 *
	 * @param args - unused
	 */
	public static void main(final String[] args) {
		DefaultErrorHandling.activateDefaultExceptionHandling();
		final AppointmentsConfigurationHandler handler = new AppointmentsConfigurationHandler();
		SwingUtilities.invokeLater(() -> {
			new EditorFrame(handler);
		});
	}
}
