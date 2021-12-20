package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.SwingUtilities;
import appointment.Appointment;
import ui.EditorFrame;
import fileInteraction.AppointmentInteracter;
import settings.Colors;

public class Editor {

    public static void main(String[] args) {
	Colors.setDarkModeEnabled(false);
	if (!AppointmentInteracter.WITH_APPOINTMENTS.exists()) {
	    createEmptyAppointmentsFile();
	}
	createAndShowGUI(AppointmentInteracter.getAppointments());
    }

    private static void createAndShowGUI(final List<Appointment> initial) {
	SwingUtilities.invokeLater(() -> {
	    new EditorFrame(initial);
	});
    }

    private static void createEmptyAppointmentsFile() {
	try (final BufferedWriter writer = new BufferedWriter(new FileWriter(AppointmentInteracter.WITH_APPOINTMENTS))) {
	    AppointmentInteracter.WITH_APPOINTMENTS.createNewFile();
	    writer.write("[]");
	} catch (final IOException e) {
	    e.printStackTrace();
	    throw new RuntimeException("Couldn't create new appointments.json");
	}
    }

}
