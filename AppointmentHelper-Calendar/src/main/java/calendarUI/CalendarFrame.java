package calendarUI;

import java.awt.BorderLayout;
import java.awt.Image;
import java.util.List;

import javax.swing.JFrame;

import appointment.Appointment;
import de.glasergl.standard.swing.general.SwingFunctions;

/**
 * Frame which shows the the list of months on top and below all calendar cells.
 *
 * @author Gabriel Glaser
 */
public final class CalendarFrame extends JFrame {
	private static final String FRAME_NAME = "TerminKalender";
	private static final Image ICON = SwingFunctions.getImage("CalendarIcon.png", CalendarFrame.class);

	private final MonthRow months = new MonthRow();
	private final AllCalendarCells cells;

	public CalendarFrame(final List<Appointment> allAppointments) {
		super(FRAME_NAME);
		cells = new AllCalendarCells(allAppointments);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setIconImage(ICON);
		add(months, BorderLayout.NORTH);
		add(cells, BorderLayout.CENTER);
		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);
	}
}
