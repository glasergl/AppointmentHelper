package calendarUI;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import appointment.Appointment;
import standardGlaserGl.swing.container.LineOfJComponent;
import standardGlaserGl.swing.entity.Alignment;
import standardGlaserGl.swing.entity.ColorType;
import standardGlaserGl.swing.eventListener.ColorChangerOnHover;
import standardGlaserGl.swing.eventListener.SiblingPopUpDisplayerOnHover;
import standardGlaserGl.swing.settings.Colors;
import swing.CustomizedSwing;

/**
 * Visual representation of the summary of the given Appointments at the given
 * date.
 *
 * @author Gabriel Glaser
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
			final LineOfJComponent names = new LineOfJComponent(Alignment.VERTICAL,
					getAppointmentNamesAsLabels(appointments));
			new SiblingPopUpDisplayerOnHover(names, this);
		} else if (appointments.size() == 1) {
			final Appointment firstAppointment = appointments.get(0);
			final String nameOfFirstAppointment = firstAppointment.getName();
			appointmentsSummaryLabel.setText(nameOfFirstAppointment);
		}
		setup();
	}

	private void setup() {
		setLayout(new BorderLayout());
		setFont(CustomizedSwing.DEFAULT_FONT);
		add(appointmentsSummaryLabel, BorderLayout.CENTER);
		addMouseListener(new ColorChangerOnHover(Colors.ofFocus(), ColorType.BACKGROUND));
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

	/**
	 * @param appointments
	 * @return A List with MyLabels containing the name of the given Appointment as
	 *         text.
	 */
	private List<JLabel> getAppointmentNamesAsLabels(final List<Appointment> appointments) {
		final List<JLabel> appointmentNameLabels = new ArrayList<>(appointments.size());
		for (final Appointment appointment : appointments) {
			final JLabel appointmentNameLabel = CustomizedSwing.getDefaultJLabel();
			appointmentNameLabel.setText(appointment.getName());
			appointmentNameLabels.add(appointmentNameLabel);
		}
		return appointmentNameLabels;
	}

	public String getSummaryString() {
		return appointmentsSummaryLabel.getText();
	}
}
