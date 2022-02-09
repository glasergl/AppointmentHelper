package ui;

import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import standardSwing.container.RowOfJComponent;
import standardSwing.myComponent.button.MyTextButton;
import standardSwing.settings.Colors;
import standardSwing.settings.Fonts;
import ui.appointmentField.AllAppointmentFieldsController;

/**
 * Footer for the whole frame.
 * 
 * Contains a button to add an empty appointment.
 * 
 * @author Gabriel Glaser
 * @version 20.1.2022
 */
public class Footer extends RowOfJComponent {

    private static final int DISTANCE_TO_EDGE = 5;
    private static final Color BACKGROUND = Colors.getGray(3);
    private static final float ADD_BUTTON_PLUS_SIZE = 65.0f;

    private final AllAppointmentFieldsController appointmentInputFields;
    private final MyTextButton addAppointmentButton = new MyTextButton("+");

    public Footer(final AllAppointmentFieldsController appointmentInputFields) {
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
	addAppointmentButton.setBorder(new CompoundBorder(addAppointmentButton.getBorder(), new EmptyBorder(0, 100, 0, 100)));
	addAppointmentButton.addActionListener((event) -> {
	    appointmentInputFields.addEmptyAppointmentField();
	});
    }

    public void requestFocusForAddButton() {
	addAppointmentButton.requestFocus();
    }

}
