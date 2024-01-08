package calendarUI;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import appointment.Appointment;
import de.glasergl.standard.swing.container.LineOfJComponent;
import de.glasergl.standard.swing.entity.Alignment;
import de.glasergl.standard.swing.entity.ColorType;
import de.glasergl.standard.swing.eventListener.ColorChangerOnHover;
import de.glasergl.standard.swing.eventListener.SiblingPopUpDisplayerOnHover;
import de.glasergl.standard.swing.settings.Colors;
import swing.DefaultJComponentFactory;

/**
 * Visual representation of the summary of the given Appointments at the given
 * date.
 *
 * @author Gabriel Glaser
 */
public final class AppointmentsSummaryPanel extends JPanel {
	private final JLabel appointmentsSummaryLabel = DefaultJComponentFactory.getDefaultJLabel();

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
		setFont(DefaultJComponentFactory.DEFAULT_FONT);
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
			final JLabel appointmentNameLabel = DefaultJComponentFactory.getDefaultJLabel();
			appointmentNameLabel.setText(appointment.getName());
			appointmentNameLabels.add(appointmentNameLabel);
		}
		return appointmentNameLabels;
	}

	public String getSummaryString() {
		return appointmentsSummaryLabel.getText();
	}
}
