package ui.dateField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import date.SimpleDate;
import date.SimpleDates;
import myComponent.MyLabel;
import myComponent.button.MyTextButton;
import settings.Colors;

public final class MonthField extends JPanel {

    private final MySimpleDateField toSetSelectedDateOf;
    private final int monthToDepict;
    private final MyLabel nameOfMonth;
    private final JPanel days = new JPanel(new GridLayout(5, 7, 2, 2));

    public MonthField(final MySimpleDateField toSetSelectedDateOf, final int monthToDepict) {
	super(new BorderLayout());
	this.toSetSelectedDateOf = toSetSelectedDateOf;
	this.monthToDepict = monthToDepict;
	this.nameOfMonth = new MyLabel(getMonthName());
	setup();
    }

    private void setup() {
	days.setBackground(Colors.getGray(1));
	nameOfMonth.setHorizontalAlignment(SwingConstants.CENTER);
	addDays();
	add(nameOfMonth, BorderLayout.NORTH);
	add(days, BorderLayout.CENTER);
    }

    private void addDays() {
	final int dayOfMonth = monthToDepict == 2 ? 29 : SimpleDates.daysOfMonth(monthToDepict);
	for (int day = 1; day <= dayOfMonth; day++) {
	    final MyTextButton visualizedDay = new MyTextButton(String.valueOf(day), false);
	    visualizedDay.setPreferredSize(new Dimension(40, 40));
	    visualizedDay.addActionListener(new DayButtonController(new SimpleDate(day, monthToDepict)));
	    visualizedDay.setBackground(Colors.getGray(2));
	    visualizedDay.setBackgroundWhileMouseHovered(Colors.getGray(3));
	    days.add(visualizedDay);
	}
	for (int filler = dayOfMonth + 1; filler <= 35; filler++) {
	    days.add(new MyLabel());
	}
    }

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

    private class DayButtonController implements ActionListener {

	private final SimpleDate representedByTheButton;

	public DayButtonController(final SimpleDate representedByTheButton) {
	    super();
	    this.representedByTheButton = representedByTheButton;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    toSetSelectedDateOf.getMonthPopUp().setVisible(false);
	    toSetSelectedDateOf.setDate(representedByTheButton);
	}

    }

}
