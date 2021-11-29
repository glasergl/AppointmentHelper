package ui;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import complex.LineOfJComponent;
import standard.implementations.MyTextButton;
import standard.settings.Colors;
import standard.settings.Fonts;

/**
 * Footer for the whole frame.
 * 
 * Contains a button to add an empty appointment.
 * 
 * @author Gabriel Glaser
 * @version 28.11.2021
 */
public class Footer extends LineOfJComponent {

	private final AllAppointments allAppointments;

	private final MyTextButton addAppointments = new MyTextButton("+");

	public Footer(final AllAppointments allAppointments) {
		super();
		this.allAppointments = allAppointments;
		setup();
	}

	private void setup() {
		setBackground(Colors.getGray(1));
		addAppointments.setFont(Fonts.resizedStandard(50.0f));
		addAppointments.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		addAppointments.setPreferredSize(new Dimension(100, (int) addAppointments.getPreferredSize().getHeight()));
		addAppointments.addActionListener((event) -> {
			allAppointments.addEmpty();
		});
		addToRight(addAppointments);
	}

}
