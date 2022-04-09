package calendar.cell;

import java.awt.FontMetrics;
import java.util.List;

import appointment.Appointment;
import calendar.CalendarAttributes;
import standardSwing.myComponent.MyLabel;

/**
 * Class which contains the calculation of a summary-String of given
 * Appointments.
 *
 * @author Gabriel Glaser
 * @version 30.03.2022
 */
public final class AppointmentSummary {

    /**
     * @return A Summary representing the given Appointments.
     */
    public static String getSummary(final List<Appointment> appointments) {
	final StringBuilder summary = new StringBuilder();
	final String prefix = getPrefix(appointments);
	summary.append(prefix);
	summary.append(prefix.matches("[0-9]+") || prefix.equals("Ein") ? " " : "");
	summary.append(getSuffix(appointments));
	return summary.toString();
    }

    /**
     * @return Empty String, if the given Appointments are empty. The name of the
     *         one Appointment, if appointments.size() == 1. Else, the number of
     *         Appointments. In addition to that, it returns "Ein" if the one name
     *         is wider than allowed.
     */
    private static String getPrefix(final List<Appointment> appointments) {
	if (appointments.isEmpty()) {
	    return "";
	} else if (appointments.size() == 1) {
	    final Appointment single = appointments.get(0);
	    final String nameOfSingle = single.getName();
	    if (!isValidName(nameOfSingle)) {
		return "Ein";
	    } else {
		return nameOfSingle;
	    }
	} else {
	    return String.valueOf(appointments.size());
	}
    }

    /**
     * @return Empty String, if the given Appointments are empty or the name of the
     *         single Appointment is valid. Else, the correctly quantified type of
     *         the given Appointments.
     */
    private static String getSuffix(final List<Appointment> appointments) {
	if (appointments.isEmpty()) {
	    return "";
	} else if (appointments.size() == 1) {
	    final Appointment single = appointments.get(0);
	    final String nameOfSingle = single.getName();
	    if (!isValidName(nameOfSingle)) {
		return single.isBirthday() ? "Geburtstag" : "Termin";
	    } else {
		return "";
	    }
	} else {
	    return appointments.stream().allMatch(Appointment::isBirthday) ? "Geburtstage" : "Termine";
	}
    }

    /**
     * @param name
     * @return True if the given name is not wider than the maximum.
     */
    public static boolean isValidName(final String name) {
	return pixelWidthOf(name) <= CalendarAttributes.MAXIMUM_PIXEL_LENGTH_OF_NAME;
    }

    /**
     * Calculates the width of the given text in pixel by using a MyLabel with the
     * Cell-Font.
     *
     * @param text
     * @return The width of the text in pixel.
     */
    public static int pixelWidthOf(final String text) {
	final MyLabel label = new MyLabel();
	final FontMetrics fontMetrics = label.getFontMetrics(CalendarAttributes.FONT);
	return fontMetrics.stringWidth(text);
    }

}
