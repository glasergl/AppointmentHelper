package de.glasergl.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JPanel;

import de.glasergl.appointment.Appointment;
import de.glasergl.standard.swing.settings.Fonts;

/**
 * Class which shows the appointments of today and tomorrow.
 *
 * @author Gabriel Glaser
 * @version 3.4.2022
 */
public final class TodayTomorrowAppointmentMessagePanel extends JPanel {

    private final AppointmentMessagePanel messageOfToday;
    private final AppointmentMessagePanel messageOfTomorrow;

    public TodayTomorrowAppointmentMessagePanel(final List<Appointment> allAppointments) {
	super();
	this.messageOfToday = new AppointmentMessagePanel("Heute", Appointment::isToday, allAppointments);
	this.messageOfTomorrow = new AppointmentMessagePanel("Morgen", Appointment::isTomorrow, allAppointments);
	setup();
    }

    private void setup() {
	setLayout(new BorderLayout());
	setFont(Fonts.big());
	add(messageOfToday, BorderLayout.NORTH);
	add(messageOfTomorrow, BorderLayout.SOUTH);
    }

    @Override
    public void setBackground(final Color newBackground) {
	super.setBackground(newBackground);
	if (messageOfToday != null && messageOfTomorrow != null) {
	    messageOfToday.setBackground(newBackground);
	    messageOfTomorrow.setBackground(newBackground);
	}
    }

    @Override
    public void setForeground(final Color newForeground) {
	super.setForeground(newForeground);
	if (messageOfToday != null && messageOfTomorrow != null) {
	    messageOfToday.setForeground(newForeground);
	    messageOfTomorrow.setForeground(newForeground);
	}
    }

    @Override
    public void setFont(final Font newFontOfOutputTexts) {
	super.setFont(newFontOfOutputTexts);
	if (messageOfToday != null && messageOfTomorrow != null) {
	    messageOfToday.setFont(newFontOfOutputTexts);
	    messageOfTomorrow.setFont(newFontOfOutputTexts);
	}
    }

}
