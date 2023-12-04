package de.glasergl.calendar.cell;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.glasergl.appointment.Appointment;
import de.glasergl.calendar.CalendarAttributes;
import de.glasergl.standard.swing.border.LeftRightRoundBorder;
import de.glasergl.standard.swing.container.LineOfJComponent;
import de.glasergl.standard.swing.entity.Alignment;
import de.glasergl.standard.swing.entity.ColorType;
import de.glasergl.standard.swing.eventListener.ColorChangerOnHover;
import de.glasergl.standard.swing.eventListener.SiblingPopUpDisplayerOnHover;
import de.glasergl.standard.swing.settings.Colors;
import de.glasergl.swing.DefaultJComponentFactory;

/**
 * Visual representation of the summary of the given Appointments at the given
 * date.
 *
 * @author Gabriel Glaser
 */
public final class AppointmentSummaryPanel extends JPanel {
	private final JLabel appointmentSummary = DefaultJComponentFactory.getDefaultJLabel();

	/**
	 * @param appointments
	 */
	public AppointmentSummaryPanel(final List<Appointment> appointments) {
		super();
		appointmentSummary.setText(AppointmentSummary.getSummary(appointments));
		setup();
		final boolean singleNameTooLong = appointments.size() == 1
				&& !AppointmentSummary.isValidName(appointments.get(0).getName());
		if (appointments.size() > 1 || singleNameTooLong) {
			final LineOfJComponent names = new LineOfJComponent(Alignment.VERTICAL,
					getAppointmentNamesAsLabels(appointments));
			names.setBackground(CalendarAttributes.BACKGROUND_COLOR_OF_POP_UP);
			names.setBackgroundOfChildren(CalendarAttributes.BACKGROUND_COLOR_OF_POP_UP);
			new SiblingPopUpDisplayerOnHover(names, this);
		}
	}

	private void setup() {
		setLayout(new BorderLayout());
		setFont(CalendarAttributes.FONT);
		setBorder(new LeftRightRoundBorder(8, 20));
		add(appointmentSummary, BorderLayout.CENTER);
		addMouseListener(new ColorChangerOnHover(Colors.ofFocus(), ColorType.BACKGROUND));
	}

	@Override
	public void setBackground(final Color newBackground) {
		super.setBackground(newBackground);
		if (appointmentSummary != null) {
			appointmentSummary.setBackground(newBackground);
		}
	}

	@Override
	public void setForeground(final Color newForeground) {
		super.setForeground(newForeground);
		if (appointmentSummary != null) {
			appointmentSummary.setForeground(newForeground);
		}
	}

	@Override
	public void setFont(final Font newFont) {
		super.setFont(newFont);
		if (appointmentSummary != null) {
			appointmentSummary.setFont(newFont);
		}
	}

	/**
	 * @param appointments
	 * @return A List with MyLabels containing the name of the given Appointment as
	 *         text.
	 */
	private static List<JLabel> getAppointmentNamesAsLabels(final List<Appointment> appointments) {
		final List<JLabel> names = new ArrayList<>(appointments.size());
		for (final Appointment appointment : appointments) {
			final JLabel withAppointmentName = DefaultJComponentFactory.getDefaultJLabel();
			withAppointmentName.setText(appointment.getName());
			names.add(withAppointmentName);
		}
		return names;
	}

}
