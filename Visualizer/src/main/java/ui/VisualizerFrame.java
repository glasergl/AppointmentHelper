package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.List;
import appointment.Appointment;
import container.JPanelFactory;
import container.MyFrame;
import general.SwingFunctions;
import main.Visualizer;
import myComponent.MyTextButton;
import ui.calendar.VisualCalendar;
import settings.Colors;

public class VisualizerFrame extends MyFrame {

    private static final Image ICON = SwingFunctions.getImage("Icon.png", Visualizer.class);

    private final MyTextButton visabilityChanger = new MyTextButton("Kalender anzeigen");
    private final AppointmentOutput withAllBirthdays;
    private final VisualCalendar visualisedAppointments;

    public VisualizerFrame(final List<Appointment> toConsider) {
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
