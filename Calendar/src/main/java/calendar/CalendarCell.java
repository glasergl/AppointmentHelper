package calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.JPanel;
import appointment.Appointment;
import simpleDate.SimpleDate;
import standardSwing.settings.Colors;
import standardSwing.settings.Fonts;

/**
 * Representation of a single calendar cell which represents a date and a
 * collection of appointments which are at the specified date.
 *
 * @author Gabriel Glaser
 * @version 30.3.2022
 */
public final class CalendarCell extends JPanel {

    private static final Color BACKGROUND_COLOR = Colors.getBlue(2);
    private static final int MARGIN = 5;

    private final CalendarCellDate dateRepresentation;
    private final CalendarCellAppointmentsSummary message;

    public CalendarCell(final SimpleDate date, final List<Appointment> toRespect) {
	super();
	dateRepresentation = new CalendarCellDate(date);
	message = new CalendarCellAppointmentsSummary(date, toRespect);
	setLayout(new BorderLayout(MARGIN, 0));
	setBackground(BACKGROUND_COLOR);
	setFont(Fonts.mediumSmall());
	add(dateRepresentation, BorderLayout.WEST);
	add(message, BorderLayout.CENTER);
    }

    @Override
    public void setBackground(final Color newBackground) {
	super.setBackground(newBackground);
	if (dateRepresentation != null && message != null) {
	    dateRepresentation.setBackground(newBackground);
	    message.setBackground(newBackground);
	}
    }

    @Override
    public void setFont(final Font newFont) {
	super.setFont(newFont);
	if (dateRepresentation != null && message != null) {
	    dateRepresentation.setFont(newFont);
	    message.setFont(newFont);
	}
    }

}
