package calendar;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import simpleDate.SimpleDates;
import standardSwing.myComponent.MyLabel;

/**
 * Visual representation of all Months in a horizontal row.
 * 
 * @author Gabriel Glaser
 * @version 30.03.2022
 */
public final class MonthRow extends JPanel {

    private final List<MyLabel> monthLabels = getMonthLabels();

    public MonthRow() {
	super();
	setup();
    }

    private void setup() {
	setLayout(new GridLayout(1, 12, 1, 1));
	setBackground(CalendarAttributes.CALENDAR_BACKGROUND);
	for (final MyLabel monthLabel : monthLabels) {
	    add(monthLabel);
	}
    }

    private List<MyLabel> getMonthLabels() {
	final List<MyLabel> monthLabels = new ArrayList<>(12);
	for (final String month : SimpleDates.MONTHS) {
	    final MyLabel monthLabel = new MyLabel(month);
	    monthLabel.setBackground(CalendarAttributes.CALENDAR_BACKGROUND);
	    monthLabel.setFont(CalendarAttributes.FONT.deriveFont(Font.BOLD));
	    monthLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    monthLabels.add(monthLabel);
	}
	return monthLabels;
    }
}
