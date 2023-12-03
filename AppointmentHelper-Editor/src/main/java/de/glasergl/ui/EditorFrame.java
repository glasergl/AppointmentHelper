package de.glasergl.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;

import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import de.glasergl.fileConfiguration.AppointmentsConfigurationHandler;
import de.glasergl.standard.swing.container.MyFrame;
import de.glasergl.standard.swing.general.SwingFunctions;
import de.glasergl.ui.appointmentField.AppointmentFieldWrapperList;

/**
 * Main-Frame for the Editor.
 *
 * @author Gabriel Glaser
 */
public final class EditorFrame extends MyFrame {

	private static final String FRAME_NAME = "TerminEditor";
	private static final Image ICON = SwingFunctions.getImage("EditorIcon.png", EditorFrame.class);

	private final AppointmentFieldWrapperList appointmentsFields;
	private final Header header;
	private final Footer footer;

	public EditorFrame(final AppointmentsConfigurationHandler handler) {
		super(FRAME_NAME, ICON);
		this.appointmentsFields = new AppointmentFieldWrapperList(handler);
		this.header = new Header(appointmentsFields);
		this.footer = new Footer(appointmentsFields);
		setup();
		start();
		footer.requestFocusForAddButton();
	}

	public void requestFocusForRestoreDeletedButtonButton() {
		header.requestFocusForRestoreDeletedButton();
	}

	private void setup() {
		final Container contentPane = getContentPane();
		final JScrollPane scrollWrapper = wrapInJScrollPane(appointmentsFields);
		contentPane.add(header, BorderLayout.NORTH);
		contentPane.add(scrollWrapper, BorderLayout.CENTER);
		contentPane.add(footer, BorderLayout.SOUTH);
	}

	private JScrollPane wrapInJScrollPane(final AppointmentFieldWrapperList appointmentsFields) {
		final JScrollPane scrollWrapper = new JScrollPane(appointmentsFields, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		appointmentsFields.setParentScrollWrapper(scrollWrapper);
		scrollWrapper.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollWrapper.setPreferredSize(appointmentsFields.getPreferredViewSize());
		final JScrollBar verticalScrollBar = scrollWrapper.getVerticalScrollBar();
		verticalScrollBar.setUnitIncrement(10);
		return scrollWrapper;
	}

	/**
	 * If every depicted Appointment is stored, this Frame is normally disposed.
	 * Else, the user gets asked whether he wants to save or discard the current
	 * appointments or cancel the dispose-operation via a JOptionPane. If the
	 * appointments cannot be saved, the error with another JOptionPane is showed.
	 */
	@Override
	public void dispose() {
		if (appointmentsFields.matchesConfiguration()) {
			super.dispose();
		} else {
			final String title = "Zustand nicht gespeichert";
			final String message = "Der aktuelle Zustand der Termine ist nicht gespeichert.\nSpeichern?";
			final int answerOfUser = JOptionPane.showConfirmDialog(this, message, title,
					JOptionPane.YES_NO_CANCEL_OPTION);
			if (answerOfUser == JOptionPane.YES_OPTION) {
				if (appointmentsFields.allRepresentValidAppointments()) {
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
