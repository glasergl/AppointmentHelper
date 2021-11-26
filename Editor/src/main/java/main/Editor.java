package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.SwingUtilities;
import standard.settings.Colors;
import ui.EditorMainFrame;

public class Editor {

	public static final File WITH_APPOINTMENTS = new File("appointments.json");
	
	public static void main(String[] args) {
		Colors.setDarkModeEnabled(false);
		if (!WITH_APPOINTMENTS.exists()) {
			createEmptyAppointmentsFile();
		}
		SwingUtilities.invokeLater(() -> {
			new EditorMainFrame(AppointmentInteracter.getAppointments());
		});
	}

	private static void createEmptyAppointmentsFile() {
		try (final BufferedWriter writer = new BufferedWriter(new FileWriter(WITH_APPOINTMENTS))) {
			WITH_APPOINTMENTS.createNewFile();
			writer.write("[]");
		} catch (final IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Couldn't create new appointments.json");
		}
	}

}
