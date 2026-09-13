package de.glasergl.appointment.helper.calendar;

import de.glasergl.appointment.helper.calendar.ui.CalendarFrame;
import de.glasergl.appointment.helper.util.entity.Appointment;
import de.glasergl.appointment.helper.util.file.AppointmentsConfigurationHandler;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

/**
 * Entry point for the Calendar.
 *
 * @author glasergl
 */
public final class Main {
    /**
     * Creates a Window with an overview of all Appointments which are stored in the
     * configurationFile.
     *
     * @param args - unused
     */
    public static void main(final String[] args) {
        final AppointmentsConfigurationHandler appointmentsHandler = new AppointmentsConfigurationHandler();
        final List<Appointment> allAppointments = appointmentsHandler.getAppointments();
        SwingUtilities.invokeLater(() -> {
            try {
                new CalendarFrame(allAppointments);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
