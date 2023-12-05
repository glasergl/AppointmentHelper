package de.glasergl.main;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (final ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(() -> {
			new EditorFrame(handler);
		});
	}
}
