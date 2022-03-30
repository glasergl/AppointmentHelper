package appointmentField;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import appointment.Appointment;
import standardSwing.myComponent.MyLabel;
import standardSwing.settings.Colors;
import standardSwing.settings.Fonts;

/**
 * Top-Level Parent for all AppointmentFields which controls the JScrollPane and
 * the header.
 * 
 * @author Gabriel Glaser
 * @version 13.3.2022
 */
public class AllAppointmentFieldsController extends JPanel {

    private final AllAppointmentFields appointmentFields;
    private final ColumnDescription columnDescription = new ColumnDescription();
    private final JScrollPane jScrollPane;

    public AllAppointmentFieldsController(final List<Appointment> initialAppointments) {
	super();
	appointmentFields = new AllAppointmentFields(initialAppointments);
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

    public void saveAll() {
	appointmentFields.saveAll();
    }

    /**
     * @return True, if the input of every AppointmentField this contains represents
     *         a valid Appointment, else false.
     */
    public boolean allRepresentCorrectAppointment() {
	return appointmentFields.allRepresentCorrectAppointment();
    }

    public boolean isSaved() {
	return appointmentFields.isSaved();
    }

    public void cancelAll() {
	appointmentFields.cancelAll();
    }

    private void setup() {
	setLayout(new BorderLayout());
	setupJScrollPane();
	add(columnDescription, BorderLayout.NORTH);
	add(jScrollPane, BorderLayout.CENTER);
    }

    private void setupJScrollPane() {
	jScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
    }

    public AllAppointmentFields getAppointmentFieldPanel() {
	return appointmentFields;
    }

    private final class ColumnDescription extends JPanel {
	private final MyLabel date = new MyLabel("Datum");
	private final MyLabel name = new MyLabel("Name");
	private final MyLabel description = new MyLabel("Beschreibung");

	public ColumnDescription() {
	    super(new FlowLayout(FlowLayout.LEFT, 0, 0));
	    setBackground(Colors.getBlue(2));
	    date.setOpaque(false);
	    name.setOpaque(false);
	    description.setOpaque(false);
	    date.setFont(Fonts.resizedStandard(16.0f));
	    name.setFont(Fonts.resizedStandard(16.0f));
	    description.setFont(Fonts.resizedStandard(16.0f));
	    date.setBorder(new EmptyBorder(0, 37, 0, 0));
	    name.setBorder(new EmptyBorder(0, 27, 0, 0));
	    description.setBorder(new EmptyBorder(0, 249, 0, 0));
	    add(date);
	    add(name);
	    add(description);
	}
    }

}
