package ui.appointmentField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.border.EmptyBorder;
import appointment.Appointment;
import myComponent.MyLabel;
import settings.Colors;

/**
 * Top-Level Parent for all AppointmentFields which controls the JScrollPane and
 * the header.
 * 
 * @author Gabriel Glaser
 * @version 3.1.2022
 */
public class AllAppointments extends JPanel {

    private static final Color BACKGROUND = Colors.isDarkMode() ? Color.BLACK : Color.WHITE;
    private static final int SCROLL_SPEED = 20;

    private final AppointmentFieldPanel appointmentFields;
    private final ColumnDescription columnDescription = new ColumnDescription();
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
	add(columnDescription, BorderLayout.NORTH);
    }

    private void setupJScrollPane() {
	jScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
	final JViewport viewport = jScrollPane.getViewport();
	viewport.setBackground(BACKGROUND);
	final JScrollBar verticalScrollBar = jScrollPane.getVerticalScrollBar();
	verticalScrollBar.setUnitIncrement(SCROLL_SPEED);
	add(jScrollPane, BorderLayout.CENTER);
    }

    private final class ColumnDescription extends JPanel {
	private final MyLabel date = new MyLabel("Datum");
	private final MyLabel name = new MyLabel("Name");
	private final MyLabel description = new MyLabel("Beschreibung");

	public ColumnDescription() {
	    super(new FlowLayout(FlowLayout.LEFT, 0, 0));
	    setBackground(BACKGROUND);
	    date.setOpaque(false);
	    name.setOpaque(false);
	    description.setOpaque(false);
	    date.setBorder(new EmptyBorder(0, 70, 0, 0));
	    name.setBorder(new EmptyBorder(0, 10, 0, 0));
	    description.setBorder(new EmptyBorder(0, 238, 0, 0));
	    add(date);
	    add(name);
	    add(description);
	}
    }

}
