package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.List;
import appointment.Appointment;
import standardSwing.container.JPanelFactory;
import standardSwing.container.MyFrame;
import standardSwing.general.SwingFunctions;
import standardSwing.myComponent.button.MyTextButton;
import ui.calendar.VisualCalendar;
import standardSwing.settings.Colors;

/**
 * Main Frame for the Visualizer.
 * 
 * @author Gabriel Glaser
 * @version 6.1.2022
 */
public final class VisualizerFrame extends MyFrame {

    private static final Image ICON = SwingFunctions.getImage("VisualizerIcon.png", VisualizerFrame.class);

    private final MyTextButton calendarVisabilityChangerButton = new MyTextButton("Kalender anzeigen");
    private final AppointmentOutput appointmentOutput;
    private final VisualCalendar calendarWithAppointments;

    public VisualizerFrame(final List<Appointment> appointmentsToConsider) {
	super("TerminHelfer", ICON);
	this.appointmentOutput = new AppointmentOutput(appointmentsToConsider);
	this.calendarWithAppointments = new VisualCalendar(appointmentsToConsider);
	setup();
	if (anyIsTodayOrTomorrow(appointmentsToConsider)) {
	    start();
	}
    }

    private void setup() {
	calendarVisabilityChangerButton.addActionListener(click -> {
	    calendarWithAppointments.setVisible(!calendarWithAppointments.isVisible());
	    calendarVisabilityChangerButton.setText(calendarWithAppointments.isVisible() ? "Kalender verbergen" : "Kalender anzeigen");
	    pack();
	    setLocationRelativeTo(null);
	});
	addComponents();
    }

    private void addComponents() {
	add(appointmentOutput, BorderLayout.NORTH);
	add(JPanelFactory.create(new FlowLayout(FlowLayout.RIGHT, 0, 0), Colors.getGray(0), calendarVisabilityChangerButton), BorderLayout.CENTER);
	add(calendarWithAppointments, BorderLayout.SOUTH);
    }

    private boolean anyIsTodayOrTomorrow(final List<Appointment> appointmentsToTest) {
	for (final Appointment toTest : appointmentsToTest) {
	    if (toTest.isToday() || toTest.isTomorrow()) {
		return true;
	    }
	}
	return false;
    }

}
