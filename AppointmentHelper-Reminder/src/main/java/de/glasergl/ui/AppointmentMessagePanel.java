package de.glasergl.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.swing.JPanel;

import de.glasergl.appointment.Appointment;
import de.glasergl.model.AppointmentMessageModel;
import de.glasergl.standard.swing.entity.ColorType;
import de.glasergl.standard.swing.eventListener.ColorChangerOnHover;
import de.glasergl.standard.swing.eventListener.SiblingPopUpDisplayerOnHover;
import de.glasergl.standard.swing.myComponent.MyLabel;
import de.glasergl.standard.swing.settings.Colors;

/**
 * Class which calculates the sentence which contains the appointments of today
 * and tomorrow.
 *
 * @author Gabriel Glaser
 * @version 3.4.2022
 */
public final class AppointmentMessagePanel extends JPanel {

    private static final Color BACKGROUND_COLOR_OF_POP_UP = Colors.getBlue(0);

    private final List<MyLabel> allLabels = new ArrayList<>();
    private final AppointmentMessageModel appointmentMessage;

    public AppointmentMessagePanel(final String timeDescription, final Predicate<Appointment> timeCondition, final List<Appointment> allAppointments) {
	super();
	this.appointmentMessage = new AppointmentMessageModel(timeDescription, timeCondition, allAppointments);
	setup();
    }

    /**
     * Adds all components of the AppointmentMessage as MyLabels to this.
     *
     * If a component is a name, it gets set up further.
     */
    private void setup() {
	setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
	final List<String> messageComponents = appointmentMessage.getComponents();
	for (int i = 0; i < messageComponents.size(); i++) {
	    final MyLabel component = new MyLabel(messageComponents.get(i));
	    if (appointmentMessage.isName(i)) {
		setupAppointmentLabel(component, appointmentMessage.getAppointment(i));
	    }
	    allLabels.add(component);
	    add(component);
	}
    }

    /**
     * Adds a PopUp with the appointments' description under the MyLabel with the
     * respective name.
     *
     * @param appointmentNameLabel
     * @param appointment
     */
    private void setupAppointmentLabel(final MyLabel appointmentNameLabel, final Appointment appointment) {
	appointmentNameLabel.addMouseListener(new ColorChangerOnHover(Colors.ofFocus(), ColorType.BACKGROUND));
	final String description = appointment.getDescription();
	final MyLabel withDescription = new MyLabel(description);
	withDescription.setBackground(BACKGROUND_COLOR_OF_POP_UP);
	if (!description.equals("")) {
	    new SiblingPopUpDisplayerOnHover(withDescription, appointmentNameLabel);
	}
    }

    @Override
    public void setBackground(final Color newBackground) {
	if (allLabels != null) {
	    for (final MyLabel label : allLabels) {
		label.setBackground(newBackground);
	    }
	}
    }

    @Override
    public void setForeground(final Color newForeground) {
	super.setForeground(newForeground);
	if (allLabels != null) {
	    for (final MyLabel label : allLabels) {
		label.setForeground(newForeground);
	    }
	}
    }

    @Override
    public void setFont(final Font newFont) {
	super.setFont(newFont);
	if (allLabels != null) {
	    for (final MyLabel label : allLabels) {
		label.setFont(newFont);
	    }
	}
    }

}
