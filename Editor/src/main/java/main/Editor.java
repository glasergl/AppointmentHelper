package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.SwingUtilities;
import standard.settings.Colors;
import ui.EditorFrame;
import fileInteraction.AppointmentInteracter;

public class Editor {

	public static void main(String[] args) {
		Colors.setDarkModeEnabled(false);
		if (!AppointmentInteracter.WITH_APPOINTMENTS.exists()) {
			createEmptyAppointmentsFile();
		}
		SwingUtilities.invokeLater(() -> {
			new EditorFrame(AppointmentInteracter.getAppointments());
		});
	}

	private static void createEmptyAppointmentsFile() {
		try (final BufferedWriter writer = new BufferedWriter(
				new FileWriter(AppointmentInteracter.WITH_APPOINTMENTS))) {
			AppointmentInteracter.WITH_APPOINTMENTS.createNewFile();
			writer.write("[]");
		} catch (final IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Couldn't create new appointments.json");
		}
	}

}
