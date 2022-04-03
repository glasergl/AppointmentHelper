package appointmentField.dateField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import simpleDate.SimpleDate;
import simpleDate.SimpleDates;
import standardSwing.myComponent.MyLabel;
import standardSwing.myComponent.button.CustomTextButton;
import standardSwing.settings.Colors;

/**
 * Representation of a Month with each respective day.
 * 
 * @author Gabriel Glaser
 * @version 20.01.2022
 */
public final class MonthField extends JPanel {

    private static final Color BACKGROUND_COLOR = Colors.getBlue(1);
    private static final Color BACKGROUND_COLOR_OF_DAY = Colors.getBlue(2);
    private static final int WIDTH_BETWEEN_DAY_LABELS = 2;
    private static final int SIZE_OF_DAY_LABEL = 40;

    private final MySimpleDateField toSetSelectedDateOf;
    private final int monthToDepict;
    private final MyLabel nameOfMonth;
    private final JPanel days = new JPanel(new GridLayout(5, 7, WIDTH_BETWEEN_DAY_LABELS, WIDTH_BETWEEN_DAY_LABELS));

    public MonthField(final MySimpleDateField toSetSelectedDateOf, final int monthToDepict) {
	super(new BorderLayout());
	this.toSetSelectedDateOf = toSetSelectedDateOf;
	this.monthToDepict = monthToDepict;
	this.nameOfMonth = new MyLabel(getMonthName());
	setup();
    }

    private void setup() {
	days.setBackground(BACKGROUND_COLOR);
	nameOfMonth.setHorizontalAlignment(SwingConstants.CENTER);
	nameOfMonth.setBackground(BACKGROUND_COLOR);
	addDays();
	add(nameOfMonth, BorderLayout.NORTH);
	add(days, BorderLayout.CENTER);
    }

    /**
     * Adds the number of days of the current month to depict. To fulfill exact 35
     * added Labels, empty ones are added after the days.
     */
    private void addDays() {
	final int daysOfMonth = SimpleDates.daysOfMonth(monthToDepict);
	for (int day = 1; day <= SimpleDates.daysOfMonth(monthToDepict); day++) {
	    addDayLabel(day);
	}
	for (int filler = daysOfMonth + 1; filler <= 35; filler++) {
	    final MyLabel empty = new MyLabel();
	    empty.setBackground(BACKGROUND_COLOR);
	    days.add(empty);
	}
    }

    private void addDayLabel(int day) {
	final SimpleDate date = new SimpleDate(day, monthToDepict);
	final CustomTextButton visualizedDay = new CustomTextButton(String.valueOf(day), BACKGROUND_COLOR_OF_DAY, Colors.ofText());
	visualizedDay.setPreferredSize(new Dimension(SIZE_OF_DAY_LABEL, SIZE_OF_DAY_LABEL));
	visualizedDay.addActionListener(click -> {
	    toSetSelectedDateOf.setDateInputVisible(false);
	    toSetSelectedDateOf.setDate(date);
	});
	days.add(visualizedDay);
    }

    /**
     * @return German String representation of the month this depicts.
     */
    public String getMonthName() {
	if (monthToDepict == 1) {
	    return "Januar";
	} else if (monthToDepict == 2) {
	    return "Februar";
	} else if (monthToDepict == 3) {
	    return "März";
	} else if (monthToDepict == 4) {
	    return "April";
	} else if (monthToDepict == 5) {
	    return "Mai";
	} else if (monthToDepict == 6) {
	    return "Juni";
	} else if (monthToDepict == 7) {
	    return "Juli";
	} else if (monthToDepict == 8) {
	    return "August";
	} else if (monthToDepict == 9) {
	    return "September";
	} else if (monthToDepict == 10) {
	    return "Oktober";
	} else if (monthToDepict == 11) {
	    return "November";
	} else if (monthToDepict == 12) {
	    return "Dezember";
	} else {
	    throw new RuntimeException(monthToDepict + " is not a month.");
	}
    }

}
