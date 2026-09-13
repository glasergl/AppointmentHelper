package de.glasergl.appointment.helper.reminder.ui;

import de.glasergl.appointment.helper.util.entity.Appointment;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Class which shows the appointments of today and tomorrow.
 *
 * @author glasergl
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
