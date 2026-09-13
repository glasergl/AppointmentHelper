package de.glasergl.appointment.helper.reminder;

import de.glasergl.appointment.helper.reminder.ui.ReminderFrame;
import de.glasergl.appointment.helper.util.entity.Appointment;
import de.glasergl.appointment.helper.util.file.AppointmentsConfigurationHandler;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

/**
 * Entry class for the Reminder.
 *
 * @author glasergl
 */
public final class Main {
    /**
     * Reminder program which retrieves all appointments from the file and shows the
     * appointments of today and tomorrow. If no appointments occur today or
     * tomorrow, then the program will exit and the user sees nothing.
     *
     * @param args - unused.
     */
    public static void main(final String[] args) {
        final AppointmentsConfigurationHandler appointmentsHandler = new AppointmentsConfigurationHandler();
        final List<Appointment> allAppointments = appointmentsHandler.getAppointments();
        SwingUtilities.invokeLater(() -> {
            try {
                new ReminderFrame(allAppointments);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
