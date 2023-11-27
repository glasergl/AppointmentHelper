package de.glasergl.ui;

import java.awt.Color;

import javax.swing.JOptionPane;

import de.glasergl.standard.swing.container.RowOfJComponent;
import de.glasergl.standard.swing.myComponent.button.CustomTextButton;
import de.glasergl.standard.swing.settings.Colors;
import de.glasergl.ui.appointmentField.AppointmentFieldControllerListController;

/**
 * Header for the whole frame.
 *
 * Contains a button which saves all currently entered appointments and a button
 * to restore the last deleted button.
 *
 * @author Gabriel Glaser
 * @version 30.3.2022
 */
public class Header extends RowOfJComponent {

	private static final int DISTANCE_TO_EDGE = 5;
	private static final Color BACKGROUND = new Color(220, 220, 220);

	private final AppointmentFieldControllerListController appointmentInputFields;

	private final CustomTextButton saveButton = new CustomTextButton("Alle Speichern", Colors.getBlue(3), Color.WHITE);
	private final CustomTextButton restoreDeletedButton = new CustomTextButton("Zuletzt gelöscht wiederherstellen",
			Colors.getBlue(3), Color.WHITE);

	public Header(final AppointmentFieldControllerListController appointmentFields) {
		super(DISTANCE_TO_EDGE, DISTANCE_TO_EDGE);
		this.appointmentInputFields = appointmentFields;
		setup();
	}

	private void setup() {
		setBackground(BACKGROUND);
		setupActionListeners();
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
