package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import appointment.Appointment;
import standard.settings.Colors;

public class AllAppointments extends JPanel {

	private final List<AppointmentRepresentation> appointments = new ArrayList<>();
	private final JPanel allAppointments = new JPanel();
	private final JScrollPane whichContainsAllAppointments;

	private static final int MAX_NUMBER_OF_SHOWN_APPOINTMENTS = 10;

	public AllAppointments(final List<Appointment> toDepict) {
		super(new BorderLayout());
		this.whichContainsAllAppointments = new JScrollPane(allAppointments);
		setupAllAppointments(toDepict);
		setupScrollPane();
		updatePreferredSize();
		add(whichContainsAllAppointments);
	}

	public AllAppointments() {
		this(List.of());
	}

	private void setupAllAppointments(final List<Appointment> toDepict) {
		allAppointments.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		allAppointments.setBackground(Colors.getGray(1));
		for (final Appointment toAdd : toDepict) {
			final AppointmentRepresentation appointment = new AppointmentRepresentation(this, toAdd);
			appointments.add(appointment);
			allAppointments.add(appointment);
		}
	}

	private void setupScrollPane() {
		whichContainsAllAppointments.setBorder(BorderFactory.createEmptyBorder());
		whichContainsAllAppointments.getVerticalScrollBar().setUnitIncrement(10);
	}

	private void updatePreferredSize() {
		final int widthOfVerticalScrollBar = (int) whichContainsAllAppointments.getVerticalScrollBar()
				.getPreferredSize().getWidth();
		final int heightOffset = 3;
		final AppointmentRepresentation toCalculatePreferredSize = new AppointmentRepresentation(this);
		final int preferredWidth = (int) toCalculatePreferredSize.getPreferredSize().getWidth();
		final int preferredHeight = (int) toCalculatePreferredSize.getPreferredSize().getHeight();
		allAppointments.setPreferredSize(new Dimension(preferredWidth, appointments.size() * preferredHeight));
		final int preferredWidthOfScrollPane = preferredWidth + widthOfVerticalScrollBar;
		final int preferredHeightOfScrollPane = MAX_NUMBER_OF_SHOWN_APPOINTMENTS * preferredHeight + heightOffset;
		whichContainsAllAppointments
				.setPreferredSize(new Dimension(preferredWidthOfScrollPane, preferredHeightOfScrollPane));
	}

	public void addEmpty() {
		final AppointmentRepresentation empty = new AppointmentRepresentation(this);
		appointments.add(empty);
		allAppointments.add(empty);
		updatePreferredSize();
		validate();
		final JScrollBar vertical = whichContainsAllAppointments.getVerticalScrollBar();
		vertical.setValue(vertical.getMaximum());
		allAppointments.revalidate();
	}

	public void removeAppointment(final AppointmentRepresentation toRemove) {
		appointments.remove(toRemove);
		allAppointments.remove(toRemove);
		allAppointments.revalidate();
		repaint();
	}

	public void save() {
		for (final AppointmentRepresentation appointment : appointments) {
			appointment.save();
		}
	}

}
