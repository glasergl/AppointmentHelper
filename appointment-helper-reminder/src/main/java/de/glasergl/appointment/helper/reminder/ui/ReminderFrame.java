package de.glasergl.appointment.helper.reminder.ui;

import de.glasergl.appointment.helper.util.entity.Appointment;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * Main Frame for the Reminder.
 *
 * @author glasergl
 */
public final class ReminderFrame extends JFrame {
    private final TodayTomorrowAppointmentMessagePanel appointmentMessages;

    public ReminderFrame(final List<Appointment> allAppointments) throws IOException {
        super("TerminReminder");
        setIconImage(ImageIO.read(getClass().getResourceAsStream("/ReminderIcon.png")));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.appointmentMessages = new TodayTomorrowAppointmentMessagePanel(allAppointments);
        add(appointmentMessages, BorderLayout.CENTER);
        if (anyIsTodayOrTomorrow(allAppointments)) {
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
        }
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
