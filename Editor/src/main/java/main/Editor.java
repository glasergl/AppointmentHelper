package main;

import java.util.List;
import javax.swing.SwingUtilities;
import appointment.Appointment;
import ui.EditorFrame;
import fileInteraction.AppointmentInteracter;
import settings.Colors;

public class Editor {

    public static void main(String[] args) {
	Colors.setDarkModeEnabled(false);
	if (!AppointmentInteracter.getAppointmentFile().exists()) {
	    AppointmentInteracter.createAppointmentFile();
	}
	createAndShowGUI(AppointmentInteracter.getAppointments());
    }

    private static void createAndShowGUI(final List<Appointment> initial) {
	SwingUtilities.invokeLater(() -> {
	    new EditorFrame(initial);
	});
    }

}
