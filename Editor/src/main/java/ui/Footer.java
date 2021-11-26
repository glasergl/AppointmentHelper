package ui;

import java.awt.Dimension;

import complex.LineOfJComponent;
import standard.implementations.MyButton;
import standard.settings.Colors;
import standard.settings.Fonts;

public class Footer extends LineOfJComponent {

    private final AllAppointments allAppointments;

    private final MyButton addAppointments = new MyButton("+");

    public Footer(final AllAppointments allAppointments) {
	super();
	this.allAppointments = allAppointments;
	setup();
    }

    private void setup() {
	setBackground(Colors.getBackground(2));
	addAppointments.setFont(Fonts.resizedStandard(50.0f));
	addAppointments.setPreferredSize(new Dimension(100, (int) addAppointments.getPreferredSize().getHeight()));
	addAppointments.addActionListener((event) -> {
	    allAppointments.addEmpty();
	});
	addToRight(addAppointments);
    }

}
