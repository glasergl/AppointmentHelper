package calendarUI;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import simpleDate.SimpleDates;
import swing.DefaultJComponentFactory;

/**
 * Visual representation of all Months in a horizontal row.
 *
 * @author Gabriel Glaser
 */
public final class MonthRow extends JPanel {
	private final int horizontalGap = 1;
	private final List<JLabel> monthLabels = getMonthLabels();

	public MonthRow() {
		super();
		setLayout(new GridLayout(1, monthLabels.size(), horizontalGap, 0));
		for (final JLabel monthLabel : monthLabels) {
			add(monthLabel);
		}
	}

	private List<JLabel> getMonthLabels() {
		final List<JLabel> monthLabels = new ArrayList<>();
		for (final String month : SimpleDates.MONTHS) {
			final JLabel monthLabel = DefaultJComponentFactory.getDefaultJLabel();
			monthLabel.setText(month);
			final Font fontForMonthLabel = DefaultJComponentFactory.DEFAULT_FONT.deriveFont(Font.BOLD);
			monthLabel.setFont(fontForMonthLabel);
			monthLabels.add(monthLabel);
		}
		return monthLabels;
	}
}
