package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.List;
import appointment.Appointment;
import complex.JPanelFactory;
import main.Visualizer;
import standard.MyFrame;
import standard.SwingFunctions;
import standard.implementations.MyTextButton;
import standard.settings.Colors;
import ui.calendar.VisualCalendar;

public class VisualizerMainFrame extends MyFrame {

    private static final Image ICON = SwingFunctions.getImage("Icon.png", Visualizer.class);

    private final MyTextButton visabilityChanger = new MyTextButton("Kalender anzeigen");
    private final AppointmentOutput withAllBirthdays;
    private final VisualCalendar visualisedAppointments;

    public VisualizerMainFrame(final List<Appointment> toConsider) {
	super("TerminHelfer", ICON);
	this.withAllBirthdays = new AppointmentOutput(toConsider);
	this.visualisedAppointments = new VisualCalendar(toConsider);
	addComponents();
	if (toConsider.stream().anyMatch((appointment) -> {
	    return appointment.isToday() || appointment.isTomorrow();
	})) {
	    start();
	}

	visabilityChanger.addActionListener((click) -> {
	    visualisedAppointments.setVisible(!visualisedAppointments.isVisible());
	    visabilityChanger.setText(visualisedAppointments.isVisible() ? "Kalender verbergen" : "Kalender anzeigen");
	    pack();
	    setLocationRelativeTo(null);
	});
    }

    private void addComponents() {
	add(withAllBirthdays, BorderLayout.NORTH);
	add(JPanelFactory.create(new FlowLayout(FlowLayout.RIGHT, 0, 0), Colors.getGray(0), visabilityChanger), BorderLayout.CENTER);
	add(visualisedAppointments, BorderLayout.SOUTH);
    }

}
