package appointmentMessage;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import javax.swing.JPanel;
import appointment.Appointment;
import standardSwing.entity.ColorType;
import standardSwing.eventListener.ColorChangerOnHover;
import standardSwing.eventListener.SiblingPopUpDisplayerOnHover;
import standardSwing.myComponent.MyLabel;
import standardSwing.settings.Colors;

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
    private final AppointmentMessage appointmentMessage;

    public AppointmentMessagePanel(final String timeDescription, final Predicate<Appointment> timeCondition, final List<Appointment> appointmentsAtTimeDescription) {
	super();
	this.appointmentMessage = new AppointmentMessage(timeDescription, timeCondition, appointmentsAtTimeDescription);
	setup();
    }

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

    private void setupAppointmentLabel(final MyLabel appointmentName, final Appointment appointment) {
	appointmentName.addMouseListener(new ColorChangerOnHover(Colors.ofFocus(), ColorType.BACKGROUND));
	final String description = appointment.getDescription();
	final MyLabel withDescription = new MyLabel(description);
	withDescription.setBackground(BACKGROUND_COLOR_OF_POP_UP);
	if (!description.equals("")) {
	    new SiblingPopUpDisplayerOnHover(withDescription, appointmentName);
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
