package calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JComponent;
import javax.swing.JPanel;
import appointment.Appointment;
import simpleDate.SimpleDate;
import standardSwing.container.LineOfJComponent;
import standardSwing.entity.Alignment;
import standardSwing.entity.ColorType;
import standardSwing.eventListener.ColorChangerOnHover;
import standardSwing.eventListener.SiblingPopUpDisplayerOnHover;
import standardSwing.myComponent.MyLabel;
import standardSwing.settings.Colors;
import standardSwing.settings.Fonts;

/**
 * Visual representation of the summary of the given Appointments at the given
 * date.
 * 
 * @author Gabriel Glaser
 * @version 30.03.2022
 */
public final class CalendarCellAppointmentsSummary extends JPanel {

    private static final Color BACKGROUND_COLOR_OF_POP_UP = Colors.getBlue(0);
    private static final int MAXIMUM_NAME_PIXEL_LENGTH = 120;

    private final List<Appointment> atDate;
    private final MyLabel appointmentsSummary = new MyLabel();

    /**
     * @param date
     * @param appointments
     */
    public CalendarCellAppointmentsSummary(final SimpleDate date, final List<Appointment> appointments) {
	this.atDate = appointments.stream().filter(appointment -> appointment.isAt(date)).collect(Collectors.toList());
	setLayout(new BorderLayout());
	setFont(Fonts.mediumSmall());
	appointmentsSummary.setText(calculateAppointmentsRepresentationText());
	add(appointmentsSummary, BorderLayout.CENTER);
	addMouseListener(new ColorChangerOnHover(Colors.ofFocus(), ColorType.BACKGROUND));
	if (atDate.size() > 1 || (atDate.size() == 1 && widthOfText(atDate.get(0).getName()) > MAXIMUM_NAME_PIXEL_LENGTH)) {
	    new SiblingPopUpDisplayerOnHover(getPopUpComponent(), this);
	}
    }

    /**
     * @return The textual representation of the given Appointments.
     */
    private String calculateAppointmentsRepresentationText() {
	final StringBuilder appointmentRepresentation = new StringBuilder();
	final String name = calculateTextForName();
	appointmentRepresentation.append(name);
	appointmentRepresentation.append(name.matches("[0-9]+") ? " " : "");
	appointmentRepresentation.append(calculateTextForEnding());
	return appointmentRepresentation.toString();
    }

    /**
     * @return The name of the one appointment, if there only exists one, else the
     *         number of appointments occurring today. In addition to that, it
     *         returns "1" if the one name is longer than allowed.
     */
    private String calculateTextForName() {
	if (atDate.size() == 1) {
	    final Appointment one = atDate.get(0);
	    final String nameOfOne = one.getName();
	    if (widthOfText(nameOfOne) > MAXIMUM_NAME_PIXEL_LENGTH) {
		return String.valueOf(1);
	    } else {
		return nameOfOne;
	    }
	} else if (atDate.size() > 1) {
	    return String.valueOf(atDate.size());
	} else {
	    return "";
	}
    }

    /**
     * @return The type of appointments occurring at the day. Returns an empty
     *         String, if only one appointment occurs today and its name is legal.
     */
    private String calculateTextForEnding() {
	final StringBuilder ending = new StringBuilder();
	final boolean allBirthdays = atDate.stream().allMatch(Appointment::isBirthday);
	if (atDate.size() == 1) {
	    final Appointment single = atDate.get(0);
	    final String nameOfOne = single.getName();
	    if (widthOfText(nameOfOne) > MAXIMUM_NAME_PIXEL_LENGTH) {
		ending.append(allBirthdays ? "Geburtstag" : "Termin");
	    }
	} else if (atDate.size() > 1) {
	    ending.append(allBirthdays ? "Geburtstage" : "Termine");
	}
	return ending.toString();
    }

    /**
     * @return A JComponent which contains all Appointment names vertically stacked.
     */
    private JComponent getPopUpComponent() {
	final LineOfJComponent names = new LineOfJComponent(Alignment.VERTICAL, getAppointmentNamesAsLabels(atDate), 0);
	names.setBackground(BACKGROUND_COLOR_OF_POP_UP);
	names.setBackgroundOfChildren(BACKGROUND_COLOR_OF_POP_UP);
	return names;
    }

    /**
     * @param text
     * @return The width of the text with the current Font configuration.
     */
    public int widthOfText(final String text) {
	final FontMetrics fontMetrics = getFontMetrics(getFont());
	return fontMetrics.stringWidth(text);
    }

    /**
     * @return The text which is displayed.
     */
    public String getSummaryText() {
	return appointmentsSummary.getText();
    }

    @Override
    public void setBackground(final Color newBackground) {
	super.setBackground(newBackground);
	if (appointmentsSummary != null) {
	    appointmentsSummary.setBackground(newBackground);
	}
    }

    @Override
    public void setFont(final Font newFont) {
	super.setFont(newFont);
	if (appointmentsSummary != null) {
	    appointmentsSummary.setFont(newFont);
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
