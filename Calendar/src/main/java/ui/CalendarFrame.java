package ui;

import java.awt.BorderLayout;
import java.awt.Image;
import java.util.List;
import appointment.Appointment;
import calendar.VisualCalendar;
import standardSwing.container.MyFrame;
import standardSwing.general.SwingFunctions;

/**
 * Frame which shows the VisualCalendar containing all Appointments in the
 * center.
 * 
 * @author Gabriel Glaser
 * @version 16.03.2022
 */
public class CalendarFrame extends MyFrame {

    private static final Image ICON = SwingFunctions.getImage("CalendarIcon.png", CalendarFrame.class);

    private final VisualCalendar calendar;

    public CalendarFrame(final List<Appointment> toRespect) {
	super("TerminKalender", ICON);
	calendar = new VisualCalendar(toRespect);
	add(calendar, BorderLayout.CENTER);
	calendar.setVisible(true);
	start();
    }
}
