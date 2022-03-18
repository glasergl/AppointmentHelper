package calendar;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JPanel;
import appointment.Appointment;
import date.SimpleDate;
import date.SimpleDates;
import standardSwing.myComponent.MyLabel;
import standardSwing.settings.Colors;

/**
 * Representation of all correct calendar cells.
 *
 * @author Gabriel Glaser
 * @version 28.09.2021
 */
public final class CalendarCells extends JPanel {
    private static final Color BACKGROUND_COLOR = Colors.getBlue(1);
    private static final Color BACKGROUND_COLOR_OF_TODAY = Colors.getBlue(3);
    
    private final List<Appointment> toRepresent;

    public CalendarCells(final List<Appointment> toAdd) {
	super();
	this.toRepresent = toAdd;
	setLayout(new GridLayout(31, 12, 1, 1));
	setBackground(BACKGROUND_COLOR);
	addCells();
    }

    private void addCells() {
	for (int day = 1; day <= 31; day++) {
	    for (int month = 1; month <= 12; month++) {
		if (SimpleDates.isValidDate(day, month) && !(day == 29 && month == 2 && !SimpleDates.isSwitchingYear())) {
		    final SimpleDate date = new SimpleDate(day, month);
		    final CalendarCell cell = new CalendarCell(new SimpleDate(day, month), toRepresent);
		    add(cell);
		    if (date.isToday()) {
			cell.setBackground(BACKGROUND_COLOR_OF_TODAY);
		    }
		} else {
		    final MyLabel empty = new MyLabel();
		    empty.setBackground(new Color(0, true));
		    add(empty);
		}
	    }
	}
    }

}