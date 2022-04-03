package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.List;
import appointment.Appointment;
import appointmentMessage.TodayTomorrowAppointmentMessagePanel;
import calendar.CalendarPanel;
import standardSwing.container.JPanelFactory;
import standardSwing.container.MyFrame;
import standardSwing.general.SwingFunctions;
import standardSwing.myComponent.button.MyJButton;
import standardSwing.settings.Colors;

/**
 * Main Frame for the Reminder.
 * 
 * @author Gabriel Glaser
 * @version 3.4.2022
 */
public final class ReminderFrame extends MyFrame {

    private static final Image ICON = SwingFunctions.getImage("ReminderIcon.png", ReminderFrame.class);
    private static final Color BACKGROUND_COLOR_OF_APPOINTMENT_MESSAGES = Colors.getBlue(0);

    private final MyJButton calendarVisabilityChangerButton = new MyJButton("Kalender anzeigen");
    private final TodayTomorrowAppointmentMessagePanel appointmentMessages;
    private final CalendarPanel calendarWithAppointments;

    public ReminderFrame(final List<Appointment> allAppointments) {
	super("TerminHelfer", ICON);
	this.appointmentMessages = new TodayTomorrowAppointmentMessagePanel(allAppointments);
	this.calendarWithAppointments = new CalendarPanel(allAppointments);
	setup();
	if (anyIsTodayOrTomorrow(allAppointments)) {
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
	appointmentMessages.setBackground(BACKGROUND_COLOR_OF_APPOINTMENT_MESSAGES);
	add(appointmentMessages, BorderLayout.NORTH);
	add(JPanelFactory.create(new FlowLayout(FlowLayout.RIGHT, 0, 0), Colors.getBlue(0), calendarVisabilityChangerButton), BorderLayout.CENTER);
	add(calendarWithAppointments, BorderLayout.SOUTH);
    }

    private boolean anyIsTodayOrTomorrow(final List<Appointment> appointmentsToTest) {
	for (final Appointment appointment : appointmentsToTest) {
	    if (appointment.isToday() || appointment.isTomorrow()) {
		return true;
	    }
	}
	return false;
    }

}
