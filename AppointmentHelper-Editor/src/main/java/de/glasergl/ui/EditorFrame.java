package de.glasergl.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JOptionPane;

import de.glasergl.configuration.ConfigurationHandler;
import de.glasergl.standard.swing.container.MyFrame;
import de.glasergl.standard.swing.general.SwingFunctions;
import de.glasergl.ui.appointmentField.AppointmentFieldControllerListController;

/**
 * Main-Frame for the Editor.
 *
 * @author Gabriel Glaser
 */
public final class EditorFrame extends MyFrame {

	private static final String FRAME_NAME = "TerminEditor";
	private static final Image ICON = SwingFunctions.getImage("EditorIcon.png", EditorFrame.class);

	private final AppointmentFieldControllerListController appointmentsFields;
	private final Header header;
	private final Footer footer;

	public EditorFrame(final ConfigurationHandler configurationHandler) {
		super(FRAME_NAME, ICON);
		appointmentsFields = new AppointmentFieldControllerListController(configurationHandler);
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
	 * If every depicted Appointment is stored, this Frame is normally disposed.
	 * Else, the user gets asked whether he wants to save or discard the current
	 * appointments or cancel the dispose-operation via a JOptionPane. If the
	 * appointments cannot be saved, the error with another JOptionPane is showed.
	 */
	@Override
	public void dispose() {
		if (appointmentsFields.isStored()) {
			super.dispose();
		} else {
			final String title = "Zustand nicht gespeichert";
			final String message = "Der aktuelle Zustand der Termine ist nicht gespeichert.\nSpeichern?";
			final int answerOfUser = JOptionPane.showConfirmDialog(this, message, title,
					JOptionPane.YES_NO_CANCEL_OPTION);
			if (answerOfUser == JOptionPane.YES_OPTION) {
				if (appointmentsFields.allRepresentValidAppointment()) {
					appointmentsFields.storeAll();
					super.dispose();
				} else {
					final String errorTitle = "Speichern nicht möglich";
					final String errorMessage = "Speichern ist aufgrund fehlerhafter Termine nicht möglich.\nBeheben Sie ihre Eingabe und versuchen Sie es erneut.";
					JOptionPane.showMessageDialog(this, errorMessage, errorTitle, JOptionPane.ERROR_MESSAGE);
				}
			} else if (answerOfUser == JOptionPane.NO_OPTION) {
				super.dispose();
			}
		}
	}

}
