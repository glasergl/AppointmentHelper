package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.List;
import javax.swing.JPanel;
import appointment.Appointment;
import standard.MyFrame;
import standard.SwingFunctions;
import standard.implementations.MyButton;
import standard.settings.Colors;
import ui.calendar.VisualCalendar;

/**
 * JFrame which depicts the appointments and birthdays of today and tomorrow.
 * Furthermore, there is a visual calendar which shows all entered appointments
 * and birthdays.
 * 
 * @author Gabriel Glaser
 * @version 21.11.2021
 */
public final class VisualizerFrame extends MyFrame {

	private static final Image ICON = SwingFunctions.getImage("Icon.png", VisualizerFrame.class);

	private final AppointmentOutput withAllBirthdays;
	private final VisualCalendar visualisedAppointments;
	private final MyButton visabilityChanger = new MyButton("Kalender anzeigen");

	public VisualizerFrame(final List<Appointment> appointmentsToRespect) {
		super("TerminHelfer", ICON);
		visualisedAppointments = new VisualCalendar(appointmentsToRespect);
		withAllBirthdays = new AppointmentOutput(appointmentsToRespect);
		setupComponents();
		if (appointmentsToRespect.stream().anyMatch((appointment) -> {
			return appointment.isToday() || appointment.isTomorrow();
		})) {
			start();
		}
	}

	private void setupComponents() {
		setupVisabilityButton();
		final JPanel filler = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		filler.setBackground(Colors.getBackground(1));
		filler.add(visabilityChanger);
		add(withAllBirthdays, BorderLayout.NORTH);
		add(filler, BorderLayout.CENTER);
		add(visualisedAppointments, BorderLayout.SOUTH);
	}

	private void setupVisabilityButton() {
		visabilityChanger.addActionListener((click) -> {
			visualisedAppointments.setVisible(!visualisedAppointments.isVisible());
			visabilityChanger.setText(visualisedAppointments.isVisible() ? "Kalender verbergen" : "Kalender anzeigen");
			pack();
			setLocationRelativeTo(null);
		});
	}

}
