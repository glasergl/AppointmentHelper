package main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.SwingUtilities;
import appointment.Appointment;
import standard.MyFrame;
import standard.SwingFunctions;
import standard.settings.Colors;
import ui.AllAppointments;
import ui.Footer;
import ui.Header;
import fileInteraction.AppointmentInteracter;

public class Editor {

	private static final Image ICON = SwingFunctions.getImage("Icon.png", Editor.class);

	private static MyFrame frame;

	public static void main(String[] args) {
		Colors.setDarkModeEnabled(false);
		if (!AppointmentInteracter.WITH_APPOINTMENTS.exists()) {
			createEmptyAppointmentsFile();
		}
		createAndShowGUI();
	}

	private static void createAndShowGUI() {
		SwingUtilities.invokeLater(() -> {
			frame = new MyFrame("TerminEditor", ICON);
			List<Appointment> appointments = AppointmentInteracter.getAppointments();
			AllAppointments allAppointments = new AllAppointments(appointments);
			Header header = new Header(allAppointments);
			Footer footer = new Footer(allAppointments);
			final Container contentPane = frame.getContentPane();
			contentPane.add(header, BorderLayout.NORTH);
			contentPane.add(allAppointments, BorderLayout.CENTER);
			contentPane.add(footer, BorderLayout.SOUTH);
			frame.start();
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

	public static MyFrame getFrame() {
		return frame;
	}

}
