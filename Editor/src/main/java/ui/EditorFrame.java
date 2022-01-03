package ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.util.List;
import appointment.Appointment;
import container.MyFrame;
import general.SwingFunctions;
import ui.appointmentField.AllAppointments;

/**
 * Main-Frame for the Editor.
 * 
 * @author Gabriel Glaser
 * @version 1.1.2022
 */
public class EditorFrame extends MyFrame {

    private static final Image ICON = SwingFunctions.getImage("Icon.png", EditorFrame.class);

    private final AllAppointments appointmentsInputFields;
    private final Header header;
    private final Footer footer;

    public EditorFrame(final List<Appointment> initialAppointments) {
	super("TerminEditor", ICON);
	setLayout(new BorderLayout(0, 2));
	appointmentsInputFields = new AllAppointments(initialAppointments);
	header = new Header(appointmentsInputFields);
	footer = new Footer(appointmentsInputFields);
	addComponents();
	start();
	footer.requestFocusForAddButton();
    }

    private void addComponents() {
	final Container contentPane = getContentPane();
	contentPane.add(header, BorderLayout.NORTH);
	contentPane.add(appointmentsInputFields, BorderLayout.CENTER);
	contentPane.add(footer, BorderLayout.SOUTH);
    }

}
