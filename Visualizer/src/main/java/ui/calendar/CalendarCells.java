package ui.calendar;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JPanel;
import appointment.Appointment;
import appointment.SimpleDate;
import standard.implementations.MyLabel;
import standard.settings.Colors;

/**
 * Representation of all correct calendar cells.
 *
 * @author Gabriel Glaser
 * @version 28.09.2021
 */
public final class CalendarCells extends JPanel {

    private final List<Appointment> toRepresent;

    public CalendarCells(final List<Appointment> toAdd) {
	super();
	this.toRepresent = toAdd;
	setLayout(new GridLayout(31, 12, 1, 1));
	setBackground(Colors.getGray(1));
	addCells();
    }

    private void addCells() {
	for (int day = 1; day <= 31; day++) {
	    for (int month = 1; month <= 12; month++) {
		if (SimpleDate.isACorrectDate(day, month) && !(day == 29 && month == 2 && !SimpleDate.isSwitchingYear())) {
		    final SimpleDate date = new SimpleDate(day, month);
		    final CalendarCell cell = new CalendarCell(new SimpleDate(day, month), toRepresent);
		    add(cell);
		    if (date.isToday()) {
			cell.setBackground(Colors.getGray(4));
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
