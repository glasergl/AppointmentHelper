package de.glasergl.appointment.helper.calendar.ui;

import de.glasergl.appointment.helper.util.entity.Appointment;
import de.glasergl.appointment.helper.util.swing.CustomizedSwing;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Visual representation of the summary of the given Appointments at the given
 * date.
 *
 * @author glasergl
 */
public final class AppointmentsSummaryPanel extends JPanel {
    private final JLabel appointmentsSummaryLabel = CustomizedSwing.getDefaultJLabel();

    /**
     * @param appointments
     */
    public AppointmentsSummaryPanel(final List<Appointment> appointments) {
        super();
        if (appointments.size() > 1) {
            final String summary = getSummary(appointments);
            appointmentsSummaryLabel.setText(summary);
            final JPanel names = getAppointmentNamesAsLabels(appointments);
        } else if (appointments.size() == 1) {
            final Appointment firstAppointment = appointments.get(0);
            final String nameOfFirstAppointment = firstAppointment.name();
            appointmentsSummaryLabel.setText(nameOfFirstAppointment);
        }
        setup();
    }

    private void setup() {
        setLayout(new BorderLayout());
        setFont(CustomizedSwing.DEFAULT_FONT);
        add(appointmentsSummaryLabel, BorderLayout.CENTER);
    }

    /**
     * Summarizes the given appointments by the number of them and classifying them
     * as birthdays, depending whether all of them are birthdays.
     *
     * @param appointments
     * @return Summary as String.
     */
    private String getSummary(final List<Appointment> appointments) {
        final boolean allAppointmentsAreBirthdays = allAppointmentsAreBirthdays(appointments);
        final int numberOfAppointments = appointments.size();
        if (allAppointmentsAreBirthdays) {
            return numberOfAppointments + " " + "Geburtstage";
        } else {
            return numberOfAppointments + " " + "Termine";
        }
    }

    private boolean allAppointmentsAreBirthdays(final List<Appointment> appointments) {
        for (final Appointment appointment : appointments) {
            if (!appointment.isBirthday()) {
                return false;
            }
        }
        return true;
    }

    private JPanel getAppointmentNamesAsLabels(final List<Appointment> appointments) {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (final Appointment appointment : appointments) {
            final JLabel appointmentNameLabel = CustomizedSwing.getDefaultJLabel();
            appointmentNameLabel.setText(appointment.name());
            panel.add(appointmentNameLabel);
        }
        return panel;
    }

    public String getSummaryString() {
        return appointmentsSummaryLabel.getText();
    }
}
