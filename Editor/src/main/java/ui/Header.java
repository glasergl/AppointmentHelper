package ui;

import complex.LineOfJComponent;
import standard.implementations.MyButton;
import standard.settings.Colors;

public class Header extends LineOfJComponent {

    private final AllAppointments appointments;

    private final MyButton save = new MyButton("Save All");

    public Header(final AllAppointments appointments) {
	super();
	this.appointments = appointments;
	setup();
    }

    private void setup() {
	setBackground(Colors.getBackground(1));
	save.addActionListener(click -> {
	    appointments.save();
	});
	addToRight(save);
    }
}
