package de.glasergl.calendar.cell;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JPanel;

import de.glasergl.appointment.Appointment;
import de.glasergl.appointment.Appointments;
import de.glasergl.calendar.CalendarAttributes;
import de.glasergl.simpleDate.SimpleDate;

/**
 * Representation of a single calendar cell which represents a date and a
 * summary of a list of appointments at the given date.
 *
 * @author Gabriel Glaser
 * @version 30.3.2022
 */
public final class Cell extends JPanel {

    private final SimpleDateDayPanel date;
    private final AppointmentSummaryPanel summary;

    public Cell(final SimpleDate date, final List<Appointment> allAppointments) {
	super();
	this.date = new SimpleDateDayPanel(date);
	this.summary = new AppointmentSummaryPanel(Appointments.getAppointmentsAtDate(allAppointments, date));
	setup();
    }

    private void setup() {
	setLayout(new BorderLayout(CalendarAttributes.MARGIN_BETWEEN_CELL_COMPONENTS, 0));
	setBackground(CalendarAttributes.CELL_BACKGROUND_COLOR);
	setForeground(CalendarAttributes.CALENDAR_FOREGROUND);
	setFont(CalendarAttributes.FONT);
	add(date, BorderLayout.WEST);
	add(summary, BorderLayout.CENTER);
    }

    @Override
    public void setBackground(final Color newBackground) {
	super.setBackground(newBackground);
	if (date != null && summary != null) {
	    date.setBackground(newBackground);
	    summary.setBackground(newBackground);
	}
    }

    @Override
    public void setForeground(final Color newForeground) {
	super.setForeground(newForeground);
	if (date != null && summary != null) {
	    date.setForeground(newForeground);
	    summary.setForeground(newForeground);
	}
    }

    @Override
    public void setFont(final Font newFont) {
	super.setFont(newFont);
	if (date != null && summary != null) {
	    date.setFont(newFont);
	    summary.setFont(newFont);
	}
    }

}
