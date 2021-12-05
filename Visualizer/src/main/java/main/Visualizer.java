package main;

import java.util.List;
import javax.swing.SwingUtilities;
import appointment.Appointment;
import fileInteraction.AppointmentInteracter;
import standard.settings.Colors;
import ui.VisualErrors;
import ui.VisualizerMainFrame;

/**
 * @author Gabriel Glaser
 * @version 11.9.2021
 */
public final class Visualizer {

	public static void main(final String[] args) {
		Colors.setDarkModeEnabled(false);
		if (!AppointmentInteracter.WITH_APPOINTMENTS.exists()) {
			VisualErrors.showCouldntFindAppointmentFileError();
		} else {
			createAndShowGUI(AppointmentInteracter.getAppointments());
		}
	}

	private static void createAndShowGUI(final List<Appointment> toConsider) {
		SwingUtilities.invokeLater(() -> {
			new VisualizerMainFrame(toConsider);
		});
	}

}
