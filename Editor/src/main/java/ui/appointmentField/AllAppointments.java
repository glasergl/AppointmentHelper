package ui.appointmentField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.border.EmptyBorder;
import appointment.Appointment;
import settings.Colors;

/**
 * Top-Level Parent for all AppointmentFields which controls the JScrollPane.
 * 
 * @author Gabriel Glaser
 * @version 3.1.2022
 */
public class AllAppointments extends JPanel {

    private static final Color BACKGROUND = Colors.isDarkMode() ? Color.BLACK : Color.WHITE;
    private static final int SCROLL_SPEED = 20;

    private final AppointmentFieldPanel appointmentFields;
    private final JScrollPane jScrollPane;

    public AllAppointments(final List<Appointment> initialAppointments) {
	super();
	appointmentFields = new AppointmentFieldPanel(initialAppointments);
	jScrollPane = new JScrollPane(appointmentFields, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	setup();
    }

    public void addEmptyAppointmentField() {
	appointmentFields.addEmptyAppointmentField();
	jScrollPane.validate();
	final JScrollBar verticalScrollBar = jScrollPane.getVerticalScrollBar();
	verticalScrollBar.setValue(verticalScrollBar.getMaximum());
	jScrollPane.revalidate();
	jScrollPane.repaint();
    }

    public void addAppointmentField(final Appointment appointmentToDisplay) {
	appointmentFields.addAppointmentField(appointmentToDisplay);
	jScrollPane.validate();
	final JScrollBar verticalScrollBar = jScrollPane.getVerticalScrollBar();
	verticalScrollBar.setValue(verticalScrollBar.getMaximum());
	jScrollPane.revalidate();
	jScrollPane.repaint();
    }

    public void saveAll() {
	appointmentFields.saveAll();
    }

    private void setup() {
	setLayout(new BorderLayout());
	setupJScrollPane();
    }

    private void setupJScrollPane() {
	jScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
	final JViewport viewport = jScrollPane.getViewport();
	viewport.setBackground(BACKGROUND);
	final JScrollBar verticalScrollBar = jScrollPane.getVerticalScrollBar();
	verticalScrollBar.setUnitIncrement(SCROLL_SPEED);
	add(jScrollPane, BorderLayout.CENTER);
    }

}
