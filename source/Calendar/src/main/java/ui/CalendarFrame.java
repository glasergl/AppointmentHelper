package ui;

import java.awt.BorderLayout;
import java.awt.Image;
import java.util.List;
import appointment.Appointment;
import calendar.CalendarPanel;
import standardSwing.container.MyFrame;
import standardSwing.general.SwingFunctions;

/**
 * Frame which shows the VisualCalendar containing all Appointments.
 * 
 * @author Gabriel Glaser
 * @version 30.03.2022
 */
public final class CalendarFrame extends MyFrame {

    private static final Image ICON = SwingFunctions.getImage("CalendarIcon.png", CalendarFrame.class);

    private final CalendarPanel calendar;

    public CalendarFrame(final List<Appointment> allAppointments) {
	super("TerminKalender", ICON);
	calendar = new CalendarPanel(allAppointments);
	add(calendar, BorderLayout.CENTER);
	calendar.setVisible(true);
	start();
    }
}
