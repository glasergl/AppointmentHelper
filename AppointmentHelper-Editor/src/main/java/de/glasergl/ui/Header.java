package de.glasergl.ui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import de.glasergl.standard.swing.container.RowOfJComponent;
import de.glasergl.swing.DefaultJComponentFactory;
import de.glasergl.ui.appointmentField.AppointmentFieldControllerListController;

/**
 * Header for the whole frame.
 *
 * Contains a button which saves all currently entered appointments and a button
 * to restore the last deleted button.
 *
 * @author Gabriel Glaser
 */
public class Header extends RowOfJComponent {

	private static final int DISTANCE_TO_EDGE = 5;
	private static final Color BACKGROUND = new Color(220, 220, 220);

	private final AppointmentFieldControllerListController appointmentInputFields;

	private final JButton saveButton = DefaultJComponentFactory.getDefaultJButton();
	private final JButton restoreDeletedButton = DefaultJComponentFactory.getDefaultJButton();

	public Header(final AppointmentFieldControllerListController appointmentFields) {
		super(DISTANCE_TO_EDGE, DISTANCE_TO_EDGE);
		this.appointmentInputFields = appointmentFields;
		setup();
	}

	private void setup() {
		setBackground(BACKGROUND);
		setupActionListeners();
		saveButton.setText("Speichern");
		restoreDeletedButton.setText("Zuletzt gelöscht wiederherstellen");
		addToRight(restoreDeletedButton);
		addToRight(saveButton);
	}

	public void requestFocusForRestoreDeletedButton() {
		restoreDeletedButton.requestFocusInWindow();
	}

	private void setupActionListeners() {
		saveButton.addActionListener(click -> {
			if (appointmentInputFields.allRepresentValidAppointment()) {
				appointmentInputFields.storeAll();
			} else {
				final String title = "Ungültiger Termin";
				final String message = "Mindestens ein Termin ist ungültig.\nBitte geben Sie jedem Termin einen Namen und versuchen es erneut.";
				JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
			}
		});
		restoreDeletedButton.addActionListener(click -> {
			appointmentInputFields.restoreLastDeleted();
		});
	}
}
