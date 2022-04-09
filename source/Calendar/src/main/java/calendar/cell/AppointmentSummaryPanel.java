package calendar.cell;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import appointment.Appointment;
import calendar.CalendarAttributes;
import standardSwing.border.LeftRightRoundBorder;
import standardSwing.container.LineOfJComponent;
import standardSwing.entity.Alignment;
import standardSwing.entity.ColorType;
import standardSwing.eventListener.ColorChangerOnHover;
import standardSwing.eventListener.SiblingPopUpDisplayerOnHover;
import standardSwing.myComponent.MyLabel;
import standardSwing.settings.Colors;

/**
 * Visual representation of the summary of the given Appointments at the given
 * date.
 *
 * @author Gabriel Glaser
 * @version 30.03.2022
 */
public final class AppointmentSummaryPanel extends JPanel {

    private final MyLabel appointmentSummary = new MyLabel();

    /**
     * @param appointments
     */
    public AppointmentSummaryPanel(final List<Appointment> appointments) {
	super();
	appointmentSummary.setText(AppointmentSummary.getSummary(appointments));
	setup();
	final boolean singleNameTooLong = appointments.size() == 1 && !AppointmentSummary.isValidName(appointments.get(0).getName());
	if (appointments.size() > 1 || singleNameTooLong) {
	    final LineOfJComponent names = new LineOfJComponent(Alignment.VERTICAL, getAppointmentNamesAsLabels(appointments));
	    names.setBackground(CalendarAttributes.BACKGROUND_COLOR_OF_POP_UP);
	    names.setBackgroundOfChildren(CalendarAttributes.BACKGROUND_COLOR_OF_POP_UP);
	    new SiblingPopUpDisplayerOnHover(names, this);
	}
    }

    private void setup() {
	setLayout(new BorderLayout());
	setFont(CalendarAttributes.FONT);
	setBorder(new LeftRightRoundBorder(8, 20));
	add(appointmentSummary, BorderLayout.CENTER);
	addMouseListener(new ColorChangerOnHover(Colors.ofFocus(), ColorType.BACKGROUND));
    }

    @Override
    public void setBackground(final Color newBackground) {
	super.setBackground(newBackground);
	if (appointmentSummary != null) {
	    appointmentSummary.setBackground(newBackground);
	}
    }

    @Override
    public void setForeground(final Color newForeground) {
	super.setForeground(newForeground);
	if (appointmentSummary != null) {
	    appointmentSummary.setForeground(newForeground);
	}
    }

    @Override
    public void setFont(final Font newFont) {
	super.setFont(newFont);
	if (appointmentSummary != null) {
	    appointmentSummary.setFont(newFont);
	}
    }

    /**
     * @param appointments
     * @return A List with MyLabels containing the name of the given Appointment as
     *         text.
     */
    private static List<MyLabel> getAppointmentNamesAsLabels(final List<Appointment> appointments) {
	final List<MyLabel> names = new ArrayList<>(appointments.size());
	for (final Appointment appointment : appointments) {
	    final MyLabel withAppointmentName = new MyLabel(appointment.getName());
	    names.add(withAppointmentName);
	}
	return names;
    }

}
