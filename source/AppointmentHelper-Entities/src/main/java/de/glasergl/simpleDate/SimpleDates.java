package de.glasergl.simpleDate;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * Class which contains function about simple dates.
 *
 * @author Gabriel Glaser
 * @version 29.3.2022
 */
public final class SimpleDates {

    public static final List<String> MONTHS = Collections.unmodifiableList(Arrays.asList("Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember"));

    /**
     * @param day
     * @param month
     * @return Whether day.month would represent a valid date.
     */
    public static boolean isValidDate(final int day, final int month) {
	return (month >= 1 && month <= 12 && day >= 1 && day <= daysOfMonth(month)) || (day == 29 && month == 2);
    }

    /**
     * @return The current date when the function gets called.
     */
    public static SimpleDate getToday() {
	return getDateIn(0);
    }

    /**
     * @return Tomorrow in relation to the current date when the function gets
     *         called.
     */
    public static SimpleDate getTomorrow() {
	return getDateIn(1);
    }

    /**
     * Calculates the date in the given days in relation to the current date when
     * the function gets called.
     *
     * @param days
     * @return The date in the given days.
     */
    public static SimpleDate getDateIn(final int days) {
	final Calendar calendar = Calendar.getInstance();
	calendar.add(Calendar.DAY_OF_MONTH, days);
	return calendarToDate(calendar);
    }

    /**
     * @return The current year when the function gets called.
     */
    public static int getCurrentYear() {
	final Calendar calendar = Calendar.getInstance();
	return calendar.get(Calendar.YEAR);
    }

    /**
     * @return True, if the current year when the function gets called is a
     *         switching year.
     */
    public static boolean isSwitchingYear() {
	return isSwitchingYear(getCurrentYear());
    }

    /**
     * Calculates whether the given year is a switching year.
     *
     * @param yearToTest
     * @return True if the given year is a switching year.
     */
    public static boolean isSwitchingYear(final int yearToTest) {
	if (yearToTest % 400 == 0) {
	    return true;
	} else if (yearToTest % 100 == 0) {
	    return false;
	} else {
	    return yearToTest % 4 == 0;
	}
    }

    /**
     * Calculates the days of the given month.
     *
     * @param month
     * @return The days of the given month.
     */
    public static int daysOfMonth(final int month) {
	if (month < 1 || month > 12) {
	    throw new IllegalArgumentException("The " + month + ". month doesnt exist.");
	} else {
	    switch (month) {
	    case 1:
		return 31;
	    case 2:
		return 29;
	    case 3:
		return 31;
	    case 4:
		return 30;
	    case 5:
		return 31;
	    case 6:
		return 30;
	    case 7:
		return 31;
	    case 8:
		return 31;
	    case 9:
		return 30;
	    case 10:
		return 31;
	    case 11:
		return 30;
	    case 12:
		return 31;
	    default:
		throw new RuntimeException();
	    }
	}
    }

    /**
     * Calculates the SimpleDate represented by the given Calendar.
     *
     * @param toTransform
     * @return The SimpleDate represented by the given Calendar.
     */
    public static SimpleDate calendarToDate(final Calendar toTransform) {
	return new SimpleDate(toTransform.get(Calendar.DAY_OF_MONTH), toTransform.get(Calendar.MONTH) + 1);
    }

}
