package de.glasergl.ui;

import java.awt.BorderLayout;
import java.awt.Image;
import java.util.List;

import de.glasergl.appointment.Appointment;
import de.glasergl.calendar.CalendarPanel;
import de.glasergl.standard.swing.container.MyFrame;
import de.glasergl.standard.swing.general.SwingFunctions;

/**
 * Frame which shows the CalendarPanel containing all Appointments.
 *
 * @author Gabriel Glaser
 * @version 30.03.2022
 */
public final class CalendarFrame extends MyFrame {

	private static final String FRAME_NAME = "TerminKalender";
	private static final Image ICON = SwingFunctions.getImage("CalendarIcon.png", CalendarFrame.class);

	private final CalendarPanel calendar;

	public CalendarFrame(final List<Appointment> allAppointments) {
		super(FRAME_NAME, ICON);
		calendar = new CalendarPanel(allAppointments);
		add(calendar, BorderLayout.CENTER);
		calendar.setVisible(true);
		start();
	}
}
