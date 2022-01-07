package ui;

import container.RowOfJComponent;
import myComponent.button.MyTextButton;
import settings.Colors;
import ui.appointmentField.AllAppointments;
import ui.appointmentField.AppointmentFieldController;

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

    private final MyTextButton saveButton = new MyTextButton("Alle Speichern");
    private final MyTextButton restoreDeletedButton = new MyTextButton("Zuletzt gelöscht wiederherstellen");

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
	    AppointmentFieldController.restoreLastDeleted();
	});
	addToRight(restoreDeletedButton);
	addToRight(saveButton);
    }
}
