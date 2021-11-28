package main;

import javax.swing.SwingUtilities;
import standard.settings.Colors;
import ui.VisualizerFrame;
import ui.VisualErrors;

/**
 * @author Gabriel Glaser
 * @version 11.9.2021
 */
public final class Visualizer {

	public static void main(final String[] args) {
		Colors.setDarkModeEnabled(false);
		if (!Editor.WITH_APPOINTMENTS.exists()) {
			VisualErrors.showCouldntFindAppointmentFileError();
		} else {
			SwingUtilities.invokeLater(() -> {
				new VisualizerFrame(AppointmentInteracter.getAppointments());
			});
		}
	}

}
