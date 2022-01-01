package main;

import java.util.List;
import javax.swing.SwingUtilities;
import appointment.Appointment;
import ui.EditorFrame;
import fileInteraction.AppointmentFileInteracter;
import settings.Colors;

public class Editor {

    public static void main(String[] args) {
	Colors.setDarkModeEnabled(false);
	if (!AppointmentFileInteracter.getAppointmentFile().exists()) {
	    AppointmentFileInteracter.createAppointmentFile();
	}
	createAndShowGUI(AppointmentFileInteracter.getAppointments());
    }

    private static void createAndShowGUI(final List<Appointment> initial) {
	SwingUtilities.invokeLater(() -> {
	    new EditorFrame(initial);
	});
    }

}
