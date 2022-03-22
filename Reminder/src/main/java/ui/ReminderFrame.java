package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.List;
import appointment.Appointment;
import calendar.VisualCalendar;
import standardSwing.container.JPanelFactory;
import standardSwing.container.MyFrame;
import standardSwing.general.SwingFunctions;
import standardSwing.myComponent.button.MyJButton;
import standardSwing.settings.Colors;

/**
 * Main Frame for the Visualizer.
 * 
 * @author Gabriel Glaser
 * @version 16.3.2022
 */
public final class ReminderFrame extends MyFrame {

    private static final Image ICON = SwingFunctions.getImage("ReminderIcon.png", ReminderFrame.class);

    private final MyJButton calendarVisabilityChangerButton = new MyJButton("Kalender anzeigen");
    private final AppointmentOutput appointmentOutput;
    private final VisualCalendar calendarWithAppointments;

    public ReminderFrame(final List<Appointment> appointmentsToConsider) {
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
	appointmentOutput.setBackground(Colors.getBlue(0));
	addComponents();
    }

    private void addComponents() {
	add(appointmentOutput, BorderLayout.NORTH);
	add(JPanelFactory.create(new FlowLayout(FlowLayout.RIGHT, 0, 0), Colors.getBlue(0), calendarVisabilityChangerButton), BorderLayout.CENTER);
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