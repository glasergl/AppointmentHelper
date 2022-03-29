package calendar;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import appointment.Appointment;
import simpleDate.SimpleDate;
import simpleDate.SimpleDates;
import standardSwing.settings.Colors;

/**
 * Representation of all possible dates.
 * 
 * Only contains the 29th of February when called in a switching year.
 * Appointments at this special date occur on the first of March in non
 * switching years.
 *
 * @author Gabriel Glaser
 * @version 30.03.2022
 */
public final class AllCalendarCells extends JPanel {

    private static final Color BACKGROUND_COLOR = Colors.getBlue(1);
    private static final Color BACKGROUND_COLOR_OF_TODAY = Colors.getBlue(3);
    private static final int CELL_MARGIN = 1;

    private final List<Appointment> appointments;

    /**
     * Constructs a grid of Cells which contain valid dates of the year the
     * constructor is called.
     * 
     * @param appointments
     */
    public AllCalendarCells(final List<Appointment> appointments) {
	super();
	this.appointments = appointments;
	setLayout(new GridLayout(31, 12, CELL_MARGIN, CELL_MARGIN));
	setBackground(BACKGROUND_COLOR);
	addCells();
    }

    /**
     * Adds a cell for each possible combination of day and month.
     * 
     * For a valid combination of day and month, a CalendarCell is added. Else, an
     * invisible JLabel is added. The invisible JLabel is necessary due to the
     * nature of the GridLayout.
     */
    private void addCells() {
	for (int day = 1; day <= 31; day++) {
	    for (int month = 1; month <= 12; month++) {
		addCell(day, month);
	    }
	}
    }

    private void addCell(int day, int month) {
	if (SimpleDates.isValidDate(day, month) && !(day == 29 && month == 2 && !SimpleDates.isSwitchingYear())) {
	    final SimpleDate date = new SimpleDate(day, month);
	    final CalendarCell cell = new CalendarCell(new SimpleDate(day, month), appointments);
	    add(cell);
	    if (date.isToday()) {
		cell.setBackground(BACKGROUND_COLOR_OF_TODAY);
	    }
	} else {
	    final JLabel empty = new JLabel();
	    empty.setBackground(new Color(0, 0, 0, 0));
	    add(empty);
	}
    }

}
