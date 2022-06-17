package de.glasergl.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.Point;
import java.util.List;

import javax.swing.JOptionPane;

import de.glasergl.appointment.Appointment;
import de.glasergl.appointmentField.AllAppointmentFieldsController;
import de.glasergl.standard.swing.container.MyFrame;
import de.glasergl.standard.swing.general.SwingFunctions;

/**
 * Main-Frame for the Editor.
 *
 * @author Gabriel Glaser
 * @version 30.3.2022
 */
public final class EditorFrame extends MyFrame {

    private static final String FRAME_NAME = "TerminEditor";
    private static final Image ICON = SwingFunctions.getImage("EditorIcon.png", EditorFrame.class);

    private final AllAppointmentFieldsController appointmentsFields;
    private final Header header;
    private final Footer footer;

    public EditorFrame(final List<Appointment> initialAppointments) {
	super(FRAME_NAME, ICON);
	appointmentsFields = new AllAppointmentFieldsController(initialAppointments);
	header = new Header(appointmentsFields);
	footer = new Footer(appointmentsFields);
	setup();
	start();
	final Point currentLocation = getLocation();
	setLocation(new Point(currentLocation.x, currentLocation.y - 90));
	footer.requestFocusForAddButton();
    }

    public void requestFocusForRestoreDeletedButtonButton() {
	header.requestFocusForRestoreDeletedButton();
    }

    private void setup() {
	final Container contentPane = getContentPane();
	contentPane.add(header, BorderLayout.NORTH);
	contentPane.add(appointmentsFields, BorderLayout.CENTER);
	contentPane.add(footer, BorderLayout.SOUTH);
    }

    /**
     * If every depicted Appointment is saved this Frame is normally disposed. Else,
     * the user gets asked whether he wants to save, discard all Appointments or
     * cancel the whole dispose-Operation via a JOptionPane.
     */
    @Override
    public void dispose() {
	if (appointmentsFields.isSaved()) {
	    super.dispose();
	} else {
	    final String title = "Ungespeicherte Termine";
	    final String message = "Es gibt ungespeicherte Termine.\nSpeichern?";
	    final int answerOfUser = JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_CANCEL_OPTION);
	    if (answerOfUser == JOptionPane.YES_OPTION) {
		appointmentsFields.saveAll();
		if (appointmentsFields.allRepresentCorrectAppointment()) {
		    super.dispose();
		}
	    } else if (answerOfUser == JOptionPane.NO_OPTION) {
		appointmentsFields.cancelAll();
		super.dispose();
	    }
	}
    }

}
