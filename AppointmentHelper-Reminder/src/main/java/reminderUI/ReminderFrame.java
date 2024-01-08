package reminderUI;

import java.awt.BorderLayout;
import java.awt.Image;
import java.util.List;

import javax.swing.JFrame;

import appointment.Appointment;
import standardGlaserGl.swing.general.SwingFunctions;

/**
 * Main Frame for the Reminder.
 *
 * @author Gabriel Glaser
 */
public final class ReminderFrame extends JFrame {
	private static final String FRAME_NAME = "TerminReminder";
	private static final Image ICON = SwingFunctions.getImage("ReminderIcon.png", ReminderFrame.class);

	private final TodayTomorrowAppointmentMessagePanel appointmentMessages;

	public ReminderFrame(final List<Appointment> allAppointments) {
		super(FRAME_NAME);
		setIconImage(ICON);
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
