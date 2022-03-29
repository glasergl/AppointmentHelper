package calendar;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JPanel;
import appointment.Appointment;

/**
 * Representation of a visual calendar which depicts all days of the current
 * year. Also, all appointments which should be respected are entered.
 * 
 * @author Gabriel Glaser
 * @version 6.1.2022
 */
public final class VisualCalendar extends JPanel {
    
    private final MonthRow months = new MonthRow();
    private final AllCalendarCells cells;

    public VisualCalendar(final List<Appointment> toRespect) {
	super();
	cells = new AllCalendarCells(toRespect);
	setup();

    }

    private void setup() {
	setLayout(new BorderLayout());
	setVisible(false);
	add(months, BorderLayout.NORTH);
	add(cells, BorderLayout.CENTER);
    }

    @Override
    public void setVisible(final boolean shouldBeVisible) {
	super.setVisible(shouldBeVisible);
	months.setVisible(shouldBeVisible);
	cells.setVisible(shouldBeVisible);
    }

}
