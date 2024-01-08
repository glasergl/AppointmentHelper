package editorMain;

import javax.swing.SwingUtilities;

import standardGlaserGl.errors.DefaultErrorHandling;
import editorUI.EditorFrame;
import fileConfiguration.AppointmentsConfigurationHandler;

/**
 * Entry-Point for the Editor.
 *
 * @author Gabriel Glaser
 */
public final class EditorMain {
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
