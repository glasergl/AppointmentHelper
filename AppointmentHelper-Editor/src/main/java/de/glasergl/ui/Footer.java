package de.glasergl.ui;

import java.awt.Color;
import java.awt.Dimension;

import de.glasergl.standard.swing.container.RowOfJComponent;
import de.glasergl.standard.swing.myComponent.button.CustomTextButton;
import de.glasergl.standard.swing.settings.Colors;
import de.glasergl.standard.swing.settings.Fonts;
import de.glasergl.ui.appointmentField.AppointmentFieldControllerListController;

/**
 * Footer for the whole frame.
 *
 * Contains a button to add an empty appointment.
 *
 * @author Gabriel Glaser
 * @version 30.3.2022
 */
public class Footer extends RowOfJComponent {

    private static final int DISTANCE_TO_EDGE = 5;
    private static final Color BACKGROUND = new Color(220, 220, 220);
    private static final float ADD_BUTTON_PLUS_SIZE = 80.0f;

    private final AppointmentFieldControllerListController appointmentInputFields;
    private final CustomTextButton addAppointmentButton = new CustomTextButton("+", Colors.getBlue(3), Color.WHITE);

    public Footer(final AppointmentFieldControllerListController appointmentInputFields) {
	super(DISTANCE_TO_EDGE, DISTANCE_TO_EDGE);
	this.appointmentInputFields = appointmentInputFields;
	setup();
	addAppointmentButton.requestFocus();
    }

    private void setup() {
	setBackground(BACKGROUND);
	setupAddButton();
	addToMiddle(addAppointmentButton);
    }

    private void setupAddButton() {
	addAppointmentButton.setFont(Fonts.resizedStandard(ADD_BUTTON_PLUS_SIZE));
	addAppointmentButton.setPreferredSize(new Dimension(300, 100));
	addAppointmentButton.addActionListener((event) -> {
	    appointmentInputFields.addEmptyAppointmentField();
	});
    }

    public void requestFocusForAddButton() {
	addAppointmentButton.requestFocusInWindow();
    }

}
