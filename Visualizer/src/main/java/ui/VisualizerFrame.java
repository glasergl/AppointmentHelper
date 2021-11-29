package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import appointment.Appointment;
import standard.MyFrame;
import standard.SwingFunctions;
import standard.implementations.MyTextButton;
import standard.settings.Colors;
import ui.calendar.VisualCalendar;

/**
 * Frame which depicts the appointments and birthdays of today and tomorrow.
 * Furthermore, there is a visual calendar which shows all entered appointments
 * and birthdays.
 * 
 * @author Gabriel Glaser
 * @version 28.11.2021
 */
public final class VisualizerFrame extends MyFrame {

	private static final Image ICON = SwingFunctions.getImage("Icon.png", VisualizerFrame.class);

	private final AppointmentOutput withAllBirthdays;
	private final VisualCalendar visualisedAppointments;
	private final MyTextButton visabilityChanger = new MyTextButton("Kalender anzeigen");

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
		filler.setBackground(Colors.getGray(0));
		filler.add(visabilityChanger);
		add(withAllBirthdays, BorderLayout.NORTH);
		add(filler, BorderLayout.CENTER);
		add(visualisedAppointments, BorderLayout.SOUTH);
	}

	private void setupVisabilityButton() {
		visabilityChanger.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		visabilityChanger.addActionListener((click) -> {
			visualisedAppointments.setVisible(!visualisedAppointments.isVisible());
			visabilityChanger.setText(visualisedAppointments.isVisible() ? "Kalender verbergen" : "Kalender anzeigen");
			pack();
			setLocationRelativeTo(null);
		});
	}

}
