package de.glasergl.appointment.helper.calendar.ui;

import de.glasergl.appointment.helper.util.entity.Appointment;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * Frame which shows the the list of months on top and below all calendar cells.
 *
 * @author glasergl
 */
public final class CalendarFrame extends JFrame {
    private final MonthRow months = new MonthRow();
    private final AllCalendarCells cells;

    public CalendarFrame(final List<Appointment> allAppointments) throws IOException {
        super("TerminKalender");
        cells = new AllCalendarCells(allAppointments);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setIconImage(ImageIO.read(getClass().getResourceAsStream("/CalendarIcon.png")));
        add(months, BorderLayout.NORTH);
        add(cells, BorderLayout.CENTER);
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
    }
}
