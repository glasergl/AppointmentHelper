package ui;

import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import container.RowOfJComponent;
import myComponent.button.MyTextButton;
import settings.Colors;
import settings.Fonts;
import ui.appointmentField.AllAppointments;

/**
 * Footer for the whole frame.
 * 
 * Contains a button to add an empty appointment.
 * 
 * @author Gabriel Glaser
 * @version 1.1.2022
 */
public class Footer extends RowOfJComponent {

    private final AllAppointments appointmentInputFields;

    private final MyTextButton addAppointmentButton = new MyTextButton("+");

    public Footer(final AllAppointments appointmentInputFields) {
	super(5, 5);
	this.appointmentInputFields = appointmentInputFields;
	setup();
	addAppointmentButton.requestFocus();
    }

    private void setup() {
	setBackground(Colors.getGray(0));
	addAppointmentButton.setFont(Fonts.resizedStandard(50.0f));
	addAppointmentButton.setBorder(new CompoundBorder(addAppointmentButton.getBorder(), new EmptyBorder(0, 100, 0, 100)));
	addAppointmentButton.addActionListener((event) -> {
	    appointmentInputFields.addEmptyAppointmentField();
	});
	addToMiddle(addAppointmentButton);
    }

    public void requestFocusForAddButton() {
	addAppointmentButton.requestFocus();
    }

}
