package calendar;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;

import appointment.Appointment;
import calendar.cell.AllCells;

/**
 * Representation of a visual calendar which depicts all days of the current
 * year. Furthermore, Appointments on a given day are visualized, too.
 *
 * @author Gabriel Glaser
 * @version 30.3.2022
 */
public final class CalendarPanel extends JPanel {

    private final MonthRow months = new MonthRow();
    private final AllCells cells;

    public CalendarPanel(final List<Appointment> allAppointments) {
	super();
	cells = new AllCells(allAppointments);
	setup();

    }

    private void setup() {
	setLayout(new BorderLayout());
	add(months, BorderLayout.NORTH);
	add(cells, BorderLayout.CENTER);
    }

}
