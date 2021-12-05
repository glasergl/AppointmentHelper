package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import appointment.Appointment;
import fileInteraction.AppointmentInteracter;
import standard.MyFrame;
import standard.SwingFunctions;
import standard.implementations.MyTextButton;
import standard.settings.Colors;
import ui.calendar.VisualCalendar;
import ui.AppointmentOutput;
import ui.VisualErrors;

/**
 * @author Gabriel Glaser
 * @version 11.9.2021
 */
public final class Visualizer {

	private static final Image ICON = SwingFunctions.getImage("Icon.png", Visualizer.class);

	private static MyFrame frame;
	
	public static void main(final String[] args) {
		Colors.setDarkModeEnabled(false);
		if (!AppointmentInteracter.WITH_APPOINTMENTS.exists()) {
			VisualErrors.showCouldntFindAppointmentFileError();
		} else {
			SwingUtilities.invokeLater(() -> {
				createAndShowGUI();
			});
		}
	}
	
	private static void createAndShowGUI() {
		SwingUtilities.invokeLater(() -> {
			frame = new MyFrame("TerminHelfer", ICON);
			List<Appointment> appointments = AppointmentInteracter.getAppointments();
			final MyTextButton visabilityChanger = new MyTextButton("Kalender anzeigen");
			VisualCalendar visualisedAppointments = new VisualCalendar(appointments);
			AppointmentOutput withAllBirthdays = new AppointmentOutput(appointments);
			final JPanel filler = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
			filler.setBackground(Colors.getGray(0));
			filler.add(visabilityChanger);
			frame.add(withAllBirthdays, BorderLayout.NORTH);
			frame.add(filler, BorderLayout.CENTER);
			frame.add(visualisedAppointments, BorderLayout.SOUTH);
			visabilityChanger.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			visabilityChanger.addActionListener((click) -> {
				visualisedAppointments.setVisible(!visualisedAppointments.isVisible());
				visabilityChanger.setText(visualisedAppointments.isVisible() ? "Kalender verbergen" : "Kalender anzeigen");
				frame.pack();
				frame.setLocationRelativeTo(null);
			});
			if (appointments.stream().anyMatch((appointment) -> {
				return appointment.isToday() || appointment.isTomorrow();
			})) {
				frame.start();
			}
		});
	}
	
	public static MyFrame getFrame() {
		return frame;
	}

}
