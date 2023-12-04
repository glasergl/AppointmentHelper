package de.glasergl.calendar;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import de.glasergl.simpleDate.SimpleDates;
import de.glasergl.swing.DefaultJComponentFactory;

/**
 * Visual representation of all Months in a horizontal row.
 *
 * @author Gabriel Glaser
 */
public final class MonthRow extends JPanel {
	private final List<JLabel> monthLabels = getMonthLabels();

	public MonthRow() {
		super();
		setup();
	}

	private void setup() {
		setLayout(new GridLayout(1, 12, 1, 1));
		setBackground(CalendarAttributes.CALENDAR_BACKGROUND);
		for (final JLabel monthLabel : monthLabels) {
			add(monthLabel);
		}
	}

	private List<JLabel> getMonthLabels() {
		final List<JLabel> monthLabels = new ArrayList<>(12);
		for (final String month : SimpleDates.MONTHS) {
			final JLabel monthLabel = DefaultJComponentFactory.getDefaultJLabel();
			monthLabel.setText(month);
			monthLabel.setBackground(CalendarAttributes.CALENDAR_BACKGROUND);
			monthLabel.setFont(CalendarAttributes.FONT.deriveFont(Font.BOLD));
			monthLabel.setHorizontalAlignment(SwingConstants.CENTER);
			monthLabels.add(monthLabel);
		}
		return monthLabels;
	}
}
