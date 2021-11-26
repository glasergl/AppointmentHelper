package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.SwingUtilities;
import appointments.AppointmentInteracter;
import standard.settings.Colors;
import ui.EditorMainFrame;

public class AppointmentEditor {

    public static void main(String[] args) {
	Colors.setDarkModeEnabled(false);
	if (!AppointmentHelper.WITH_APPOINTMENTS.exists()) {
	    createEmptyAppointmentsFile();
	}
	SwingUtilities.invokeLater(() -> {
	    new EditorMainFrame(AppointmentInteracter.getAppointments());
	});
    }

    private static void createEmptyAppointmentsFile() {
	try (final BufferedWriter writer = new BufferedWriter(new FileWriter(AppointmentHelper.WITH_APPOINTMENTS))) {
	    AppointmentHelper.WITH_APPOINTMENTS.createNewFile();
	    writer.write("[]");
	} catch (final IOException e) {
	    e.printStackTrace();
	    throw new RuntimeException("Couldn't create new appointments.json");
	}
    }

}
