package ui;

import complex.LineOfJComponent;
import standard.implementations.MyButton;
import standard.settings.Colors;

/**
 * Header for the whole frame.
 * 
 * Contains a button which updates the appointment files.
 * 
 * @author Gabriel Glaser
 * @version 28.11.2021
 */
public class Header extends LineOfJComponent {

	private final AllAppointments appointments;
	private final MyButton save = new MyButton("Save All");

	public Header(final AllAppointments appointments) {
		super();
		this.appointments = appointments;
		setup();
	}

	private void setup() {
		setBackground(Colors.getGray(0));
		save.addActionListener(click -> {
			appointments.save();
		});
		addToRight(save);
	}
}
