package calendar;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JComponent;
import appointment.Appointment;
import standardSwing.container.LineOfJComponent;
import date.SimpleDate;
import standardSwing.entity.Alignment;
import standardSwing.entity.ColorType;
import standardSwing.eventListener.ColorChangerOnHover;
import standardSwing.eventListener.SiblingPopUpDisplayerOnHover;
import standardSwing.myComponent.MyLabel;
import standardSwing.settings.Colors;
import standardSwing.settings.Fonts;

/**
 * Representation of a single calendar cell which represents a date and a
 * collection of appointments which are at the specified date.
 *
 * @author Gabriel Glaser
 * @version 6.1.2022
 */
public final class CalendarCell extends MyLabel {

    public static final int MAXIMUM_NAME_LENGTH = 12;
    private static final Color BACKGROUND_COLOR = Colors.getBlue(2);
    private static final Color BACKGROUND_COLOR_OF_POP_UP = Colors.getBlue(0);

    private final SimpleDate date;
    private final List<Appointment> atDate;

    public CalendarCell(final SimpleDate date, final List<Appointment> toRespect) {
	super();
	this.date = date;
	this.atDate = toRespect.stream().filter(appointment -> appointment.isAt(date)).collect(Collectors.toList());
	setText(calculateTextForDate());
	setFont(Fonts.mediumSmall());
	setBackground(BACKGROUND_COLOR);
	addMouseListener(new ColorChangerOnHover(Colors.ofFocus(), ColorType.BACKGROUND));
	if (atDate.size() > 1 || (atDate.size() == 1 && atDate.get(0).getName().length() > MAXIMUM_NAME_LENGTH)) {
	    new SiblingPopUpDisplayerOnHover(getPopUpComponent(), this);
	}
    }

    private String calculateTextForDate() {
	final StringBuilder textForDate = new StringBuilder(date.getDay() + "." + "  ");
	final String name = calculateTextForName();
	textForDate.append(name);
	textForDate.append(name.length() > 0 ? " " : "");
	textForDate.append(calculateTextForEnding());
	return textForDate.toString();
    }

    private String calculateTextForName() {
	if (atDate.size() == 1) {
	    final Appointment one = atDate.get(0);
	    final String nameOfOne = one.getName();
	    if (nameOfOne.length() > MAXIMUM_NAME_LENGTH) {
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

    private String calculateTextForEnding() {
	final StringBuilder ending = new StringBuilder();
	final boolean allBirthdays = atDate.stream().allMatch(Appointment::isBirthday);
	if (atDate.size() == 1) {
	    final Appointment one = atDate.get(0);
	    final String nameOfOne = one.getName();
	    if (nameOfOne.length() > MAXIMUM_NAME_LENGTH) {
		ending.append(allBirthdays ? "Geburtstag " : "Termin ");
	    }
	} else if (atDate.size() > 1) {
	    ending.append(allBirthdays ? "Geburtstage " : "Termine ");
	}
	return ending.toString();
    }

    private JComponent getPopUpComponent() {
	final LineOfJComponent names = new LineOfJComponent(Alignment.VERTICAL, getNamesAsLabels(atDate), 0);
	names.setBackground(BACKGROUND_COLOR_OF_POP_UP);
	return names;
    }

    private List<MyLabel> getNamesAsLabels(final List<Appointment> appointments) {
	final List<MyLabel> names = new ArrayList<>(appointments.size());
	for (final Appointment appointment : appointments) {
	    final MyLabel withAppointmentName = new MyLabel(appointment.getName());
	    withAppointmentName.setBackground(BACKGROUND_COLOR_OF_POP_UP);
	    names.add(withAppointmentName);
	}
	return names;
    }

    @Override
    public String toString() {
	return getText();
    }

}
