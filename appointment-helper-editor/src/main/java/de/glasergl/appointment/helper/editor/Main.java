package de.glasergl.appointment.helper.editor;

import java.io.IOException;

import javax.swing.SwingUtilities;

import de.glasergl.appointment.helper.editor.ui.EditorFrame;
import de.glasergl.appointment.helper.util.file.AppointmentsConfigurationHandler;

/**
 * Entry-Point for the Editor.
 *
 * @author glasergl
 */
public final class Main {
	/**
	 * The Editor depicts all currently stored Appointments of the default
	 * Appointment-File and allows to edit them.
	 *
	 * @param args - unused
	 */
	public static void main(final String[] args) {
		final AppointmentsConfigurationHandler handler = new AppointmentsConfigurationHandler();
		SwingUtilities.invokeLater(() -> {
			try {
				new EditorFrame(handler);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
}
