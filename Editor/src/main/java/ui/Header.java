package ui;

import java.util.Stack;
import appointment.Appointment;
import container.RowOfJComponent;
import myComponent.button.MyTextButton;
import settings.Colors;
import ui.appointmentField.AllAppointments;

/**
 * Header for the whole frame.
 * 
 * Contains a button which saves all currently entered appointments.
 * 
 * @author Gabriel Glaser
 * @version 1.1.2022
 */
public class Header extends RowOfJComponent {

    private final AllAppointments appointmentInputFields;

    private final MyTextButton saveButton = new MyTextButton("Save All");
    private final Stack<Appointment> lastDeleted = new Stack<>();
    private final MyTextButton restoreDeletedButton = new MyTextButton("Restore Last Deleted");

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
	restoreDeletedButton.addActionListener(click -> {
	    final Appointment lastDeleted = this.lastDeleted.pop();
	    appointmentInputFields.addAppointmentField(lastDeleted);
	    if (this.lastDeleted.size() == 0) {
		restoreDeletedButton.setVisible(false);
	    }
	});
	restoreDeletedButton.setVisible(false);
	addToRight(restoreDeletedButton);
	addToRight(saveButton);
    }
}
