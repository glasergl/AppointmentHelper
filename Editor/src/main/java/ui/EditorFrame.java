package ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.util.List;
import javax.swing.JOptionPane;
import appointment.Appointment;
import container.MyFrame;
import general.SwingFunctions;
import ui.appointmentField.AllAppointments;

/**
 * Main-Frame for the Editor.
 * 
 * @author Gabriel Glaser
 * @version 7.1.2022
 */
public final class EditorFrame extends MyFrame {

    private static final Image ICON = SwingFunctions.getImage("EditorIcon.png", EditorFrame.class);

    private final AllAppointments appointmentsFields;
    private final Header header;
    private final Footer footer;

    public EditorFrame(final List<Appointment> initialAppointments) {
	super("TerminEditor", ICON);
	setLayout(new BorderLayout(0, 2));
	appointmentsFields = new AllAppointments(initialAppointments);
	header = new Header(appointmentsFields);
	footer = new Footer(appointmentsFields);
	addComponents();
	start();
	footer.requestFocusForAddButton();
    }

    private void addComponents() {
	final Container contentPane = getContentPane();
	contentPane.add(header, BorderLayout.NORTH);
	contentPane.add(appointmentsFields, BorderLayout.CENTER);
	contentPane.add(footer, BorderLayout.SOUTH);
    }

    @Override
    public void dispose() {
	if (!appointmentsFields.isSaved()) {
	    final int result = JOptionPane.showConfirmDialog(this, "Es gibt ungespeicherte Termine.\nSpeichern?", "Ungespeicherte Termine", JOptionPane.YES_NO_CANCEL_OPTION,
		    JOptionPane.INFORMATION_MESSAGE);
	    if (result == JOptionPane.YES_OPTION) {
		appointmentsFields.saveAll();
		super.dispose();
	    } else if (result == JOptionPane.NO_OPTION) {
		appointmentsFields.cancelAll();
		super.dispose();
	    }
	} else {
	    super.dispose();
	}
    }

}
