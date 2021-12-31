package ui;

import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import container.RowOfJComponent;
import myComponent.button.MyTextButton;
import settings.Colors;
import settings.Fonts;

/**
 * Footer for the whole frame.
 * 
 * Contains a button to add an empty appointment.
 * 
 * @author Gabriel Glaser
 * @version 28.11.2021
 */
public class Footer extends RowOfJComponent {

    private final AllAppointments allAppointments;

    private final MyTextButton addAppointments = new MyTextButton("+");

    public Footer(final AllAppointments allAppointments) {
	super(5, 5);
	this.allAppointments = allAppointments;
	setup();
	addAppointments.requestFocus();
    }

    private void setup() {
	setBackground(Colors.getGray(0));
	addAppointments.setFont(Fonts.resizedStandard(50.0f));
	addAppointments.setBorder(new CompoundBorder(addAppointments.getBorder(), new EmptyBorder(0, 100, 0, 100)));
	addAppointments.addActionListener((event) -> {
	    allAppointments.addEmpty();
	});
	addToMiddle(addAppointments);
    }

    public void requestFocusForAddButton() {
	addAppointments.requestFocus();
    }

}
