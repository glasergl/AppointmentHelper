package main;

import java.util.Arrays;
import java.util.List;
import javax.swing.SwingUtilities;
import appointment.Appointment;
import fileInteraction.AppointmentFileInteracter;
import settings.Colors;
import ui.VisualErrors;
import ui.VisualizerFrame;

/**
 * @author Gabriel Glaser
 * @version 11.9.2021
 */
public final class Visualizer {

    public static void main(final String[] args) {
	Colors.setDarkModeEnabled(false);
	if (!AppointmentFileInteracter.getAppointmentFile().exists()) {
	    VisualErrors.showCouldntFindAppointmentFileError();
	} else {
	    createAndShowGUI(AppointmentFileInteracter.getAppointments());
	}
    }

    private static void createAndShowGUI(final List<Appointment> toConsider) {
	SwingUtilities.invokeLater(() -> {
	    new VisualizerFrame(toConsider);
	});
    }

    @SafeVarargs
    public static <T> List<T> listOf(final T... elements) {
	return Arrays.asList(elements);
    }

}
