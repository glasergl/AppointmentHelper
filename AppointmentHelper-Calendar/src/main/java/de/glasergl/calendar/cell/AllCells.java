package de.glasergl.calendar.cell;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.glasergl.appointment.Appointment;
import de.glasergl.calendar.CalendarAttributes;
import de.glasergl.simpleDate.SimpleDate;
import de.glasergl.simpleDate.SimpleDates;

/**
 * Representation of all possible dates.
 *
 * Only contains the 29th of February if constructed in a switching year.
 * Appointments at the 29th of February occur on the first of March in non
 * switching years.
 *
 * @author Gabriel Glaser
 */
public final class AllCells extends JPanel {

	private final List<Appointment> allAppointments;

	/**
	 * Constructs a grid of Cells which contains all valid dates of the year the
	 * constructor is called.
	 *
	 * @param allAppointments
	 */
	public AllCells(final List<Appointment> allAppointments) {
		super();
		this.allAppointments = allAppointments;
		setLayout(new GridLayout(31, 12, CalendarAttributes.MARGIN_BETWEEN_CELLS,
				CalendarAttributes.MARGIN_BETWEEN_CELLS));
		setBackground(CalendarAttributes.CALENDAR_BACKGROUND);
		addCells();
	}

	/**
	 * Adds a cell for each possible combination of day and month.
	 */
	private void addCells() {
		for (int day = 1; day <= 31; day++) {
			for (int month = 1; month <= 12; month++) {
				addCell(day, month);
			}
		}
	}

	/**
	 * Adds a Cell if day.month is a valid date, else, adds an empty, invisible
	 * JLabel.
	 *
	 * Furthermore, the background of a Cell which represents the day this is
	 * constructed is emphasized.
	 *
	 * @param day
	 * @param month
	 */
	private void addCell(int day, int month) {
		if (SimpleDates.isValidDate(day, month) && !(day == 29 && month == 2 && !SimpleDates.isSwitchingYear())) {
			final SimpleDate date = new SimpleDate(day, month);
			final Cell cell = new Cell(new SimpleDate(day, month), allAppointments);
			if (date.isToday()) {
				cell.setBackground(CalendarAttributes.BACKGROUND_COLOR_OF_TODAY);
			}
			add(cell);
		} else {
			final JLabel empty = new JLabel();
			empty.setBackground(new Color(0, 0, 0, 0));
			add(empty);
		}
	}

}
