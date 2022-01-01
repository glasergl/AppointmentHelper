package date;

import java.util.Calendar;

public final class SimpleDates {
    
    public static boolean isACorrectDate(final int day, final int month) {
	return (month >= 1 && month <= 12 && day >= 1 && day <= daysOfMonth(month)) || (day == 29 && month == 2);
    }

    public static SimpleDate getToday() {
	return getDateIn(0);
    }

    public static SimpleDate getTomorrow() {
	return getDateIn(1);
    }

    public static SimpleDate getDateIn(final int days) {
	final Calendar calendar = Calendar.getInstance();
	calendar.add(Calendar.DAY_OF_MONTH, days);
	return calendarToDate(calendar);
    }

    public static int getCurrentYear() {
	final Calendar calendar = Calendar.getInstance();
	return calendar.get(Calendar.YEAR);
    }

    public static boolean isSwitchingYear() {
	return isSwitchingYear(getCurrentYear());
    }

    public static boolean isSwitchingYear(final int year) {
	if (year % 400 == 0) {
	    return true;
	} else if (year % 100 == 0) {
	    return false;
	} else {
	    return year % 4 == 0;
	}
    }

    public static int daysOfMonth(final int month) {
	if (month < 1 || month > 12) {
	    throw new IllegalArgumentException("The " + month + ". month doesnt exist.");
	} else {
	    switch (month) {
	    case 1:
		return 31;
	    case 2:
		return isSwitchingYear() ? 29 : 28;
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
		return -1; // unreachable
	    }
	}
    }

    public static boolean isPossibleDay(final String toTest) {
	try {
	    final int tag = Integer.valueOf(toTest);
	    return 1 <= tag && tag <= 31;
	} catch (final NumberFormatException e) {
	    return false;
	}
    }

    public static boolean isPossibleMonth(final String toTest) {
	try {
	    final int month = Integer.valueOf(toTest);
	    return 1 <= month && month <= 12;
	} catch (final NumberFormatException e) {
	    return false;
	}
    }

    public static SimpleDate calendarToDate(final Calendar toTransform) {
	return new SimpleDate(toTransform.get(Calendar.DAY_OF_MONTH), toTransform.get(Calendar.MONTH) + 1);
    }

}
