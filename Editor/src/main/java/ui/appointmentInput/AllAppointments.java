package ui.appointmentInput;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import appointment.Appointment;
import general.SwingFunctions;
import settings.Colors;

public class AllAppointments extends JPanel {

    private final AppointmentFieldPanel appointmentInputFields;
    private final JScrollPane jScrollPane;

    public AllAppointments(final List<Appointment> initialAppointments) {
	super(new BorderLayout());
	appointmentInputFields = new AppointmentFieldPanel(initialAppointments);
	jScrollPane = new JScrollPane(appointmentInputFields, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	setupJScrollPane();
    }

    private void setupJScrollPane() {
	jScrollPane.getViewport().setBackground(Colors.getGray(0));
	jScrollPane.getVerticalScrollBar().setUnitIncrement(20);
	jScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
	add(jScrollPane, BorderLayout.CENTER);
    }

    public void addEmptyAppointmentInputField() {
	appointmentInputFields.addEmptyAppointmentInputField();
	jScrollPane.validate();
	final JScrollBar verticalScrollBar = jScrollPane.getVerticalScrollBar();
	verticalScrollBar.setValue(verticalScrollBar.getMaximum());
	SwingFunctions.updateJComponent(jScrollPane);
    }

    public void saveAll() {
	appointmentInputFields.saveAll();
    }

}
