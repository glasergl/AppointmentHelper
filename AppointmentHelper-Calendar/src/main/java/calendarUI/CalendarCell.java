package calendarUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import appointment.Appointment;
import appointment.Appointments;
import simpleDate.SimpleDate;
import swing.CustomizedSwing;

/**
 * Representation of a single calendar cell which represents a date and a
 * summary of a list of appointments at the given date.
 *
 * @author Gabriel Glaser
 */
public final class CalendarCell extends JPanel {
	private final JLabel dayLabel;
	private final AppointmentsSummaryPanel summary;
	private final Dimension preferredSizeOfDayLabel = new Dimension(30, 25);
	private final Dimension preferredSizeOfSummary = new Dimension(110, 25);
	private final int inBetweenMargin = 5;

	public CalendarCell(final SimpleDate date, final List<Appointment> allAppointments) {
		super();
		final int day = date.getDay();
		final String dayAsString = String.valueOf(day) + ".";
		this.dayLabel = CustomizedSwing.getDefaultJLabel(dayAsString);
		this.summary = new AppointmentsSummaryPanel(Appointments.getAppointmentsAtDate(allAppointments, date));

		setLayout(new BorderLayout(inBetweenMargin, 0));
		dayLabel.setPreferredSize(preferredSizeOfDayLabel);
		dayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		summary.setPreferredSize(preferredSizeOfSummary);
		add(dayLabel, BorderLayout.WEST);
		add(summary, BorderLayout.CENTER);
	}

	/**
	 * @param text
	 * @return Whether the given string would fit into a default JLabel with default
	 *         Font.
	 */
	public boolean stringFitsIntoPreferredSize(final String text) {
		final int pixelWidth = pixelWidthOf(text);
		final int preferredWidth = preferredSizeOfSummary.width;
		return pixelWidth <= preferredWidth;
	}

	/**
	 * Calculates the width of the given text in pixel by using the font of the
	 * default JLabel.
	 *
	 * @param text
	 * @return The width of the text in pixel.
	 */
	private static int pixelWidthOf(final String text) {
		final JLabel label = CustomizedSwing.getDefaultJLabel();
		final Font defaultFont = label.getFont();
		final FontMetrics fontMetrics = label.getFontMetrics(defaultFont);
		return fontMetrics.stringWidth(text);
	}

	@Override
	public void setBackground(final Color color) {
		super.setBackground(color);
		if (summary != null) {
			summary.setBackground(color);
		}
	}
}
