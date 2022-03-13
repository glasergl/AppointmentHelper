package ui;

import java.awt.Color;
import standardSwing.container.RowOfJComponent;
import standardSwing.myComponent.button.MyJButton;
import standardSwing.settings.Colors;
import ui.appointmentField.AllAppointmentFieldsController;
import ui.appointmentField.AllAppointmentFields;

/**
 * Header for the whole frame.
 * 
 * Contains a button which saves all currently entered appointments and a button
 * to restore the last deleted button.
 * 
 * @author Gabriel Glaser
 * @version 20.1.2022
 */
public class Header extends RowOfJComponent {

    private static final int DISTANCE_TO_EDGE = 5;
    private static final Color BACKGROUND = Colors.getBlue(3);

    private final AllAppointmentFieldsController appointmentInputFields;

    private final MyJButton saveButton = new MyJButton("Alle Speichern");
    private final MyJButton restoreDeletedButton = new MyJButton("Zuletzt gelöscht wiederherstellen");

    public Header(final AllAppointmentFieldsController appointmentFields) {
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

    private void setupActionListeners() {
	saveButton.addActionListener(click -> {
	    appointmentInputFields.saveAll();
	});
	restoreDeletedButton.addActionListener(click -> {
	    final AllAppointmentFields appointmentFieldPanel = appointmentInputFields.getAppointmentFieldPanel();
	    appointmentFieldPanel.restoreLastDeletedAppointmentField();
	});
    }
}
