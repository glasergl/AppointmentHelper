package de.glasergl.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.glasergl.appointment.Appointment;
import de.glasergl.model.AppointmentMessageModel;
import de.glasergl.swing.DefaultJComponentFactory;

/**
 * Class which calculates the sentence which contains the appointments of today
 * and tomorrow.
 *
 * @author Gabriel Glaser
 */
public final class AppointmentMessagePanel extends JPanel {
	private final List<JLabel> allLabels = new ArrayList<>();
	private final AppointmentMessageModel appointmentMessage;

	public AppointmentMessagePanel(final String timeDescription, final Predicate<Appointment> timeCondition,
			final List<Appointment> allAppointments) {
		super();
		this.appointmentMessage = new AppointmentMessageModel(timeDescription, timeCondition, allAppointments);
		setup();
	}

	/**
	 * Adds all components of the AppointmentMessage as MyLabels to this.
	 *
	 * If a component is a name, it gets set up further.
	 */
	private void setup() {
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		final List<String> messageComponents = appointmentMessage.getComponents();
		for (int i = 0; i < messageComponents.size(); i++) {
			final JLabel component = DefaultJComponentFactory.getDefaultJLabel();
			component.setText(messageComponents.get(i));
			allLabels.add(component);
			add(component);
		}
	}

	@Override
	public void setBackground(final Color newBackground) {
		if (allLabels != null) {
			for (final JLabel label : allLabels) {
				label.setBackground(newBackground);
			}
		}
	}

	@Override
	public void setForeground(final Color newForeground) {
		super.setForeground(newForeground);
		if (allLabels != null) {
			for (final JLabel label : allLabels) {
				label.setForeground(newForeground);
			}
		}
	}

	@Override
	public void setFont(final Font newFont) {
		super.setFont(newFont);
		if (allLabels != null) {
			for (final JLabel label : allLabels) {
				label.setFont(newFont);
			}
		}
	}
}
