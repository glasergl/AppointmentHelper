package ui;

import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import complex.RowOfJComponent;
import standard.implementations.MyTextButton;
import standard.settings.Colors;

/**
 * Header for the whole frame.
 * 
 * Contains a button which updates the appointment files.
 * 
 * @author Gabriel Glaser
 * @version 28.11.2021
 */
public class Header extends RowOfJComponent {

    private final AllAppointments appointments;
    private final MyTextButton save = new MyTextButton("Save All");

    public Header(final AllAppointments appointments) {
	super();
	this.appointments = appointments;
	setup();
    }

    private void setup() {
	setBackground(Colors.getGray(0));
	save.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	save.addActionListener(click -> {
	    appointments.save();
	});
	addToRight(save);
    }
}
