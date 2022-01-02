package ui;

import container.RowOfJComponent;
import myComponent.button.MyTextButton;
import settings.Colors;
import ui.appointmentInput.AllAppointments;

/**
 * Header for the whole frame.
 * 
 * Contains a button which saves all currently entered appointmetns.
 * 
 * @author Gabriel Glaser
 * @version 1.1.2022
 */
public class Header extends RowOfJComponent {

    private final AllAppointments appointmentInputFields;

    private final MyTextButton saveButton = new MyTextButton("Save All");

    public Header(final AllAppointments appointmentInputFields) {
	super(5, 5);
	this.appointmentInputFields = appointmentInputFields;
	setup();
    }

    private void setup() {
	setBackground(Colors.getGray(0));
	saveButton.addActionListener(click -> {
	    appointmentInputFields.saveAll();
	});
	addToRight(saveButton);
    }
}
