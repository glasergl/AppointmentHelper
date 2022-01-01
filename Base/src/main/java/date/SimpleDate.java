package date;

/**
 * Immutable class to store a simple date with just day and month.
 * 
 * @author Gabriel Glaser
 * @version 1.1.2022
 */
public final class SimpleDate implements Comparable<SimpleDate> {

    private final int day;
    private final int month;

    /**
     * @param day
     * @param month
     * @throws IllegalArgumentException - If the given day and month don't represent
     *                                  a valid date.
     */
    public SimpleDate(final int day, final int month) throws IllegalArgumentException {
	if (!SimpleDates.isValidDate(day, month)) {
	    throw new IllegalArgumentException(day + "." + month + " is not a valid date.");
	}
	this.day = day;
	this.month = month;
    }

    public boolean isToday() {
	return equals(SimpleDates.getToday());
    }

    public boolean isTomorrow() {
	return equals(SimpleDates.getTomorrow());
    }

    @Override
    public int compareTo(final SimpleDate toCompare) {
	final int monthCompareValue = Integer.compare(month, toCompare.getMonth());
	if (monthCompareValue != 0) {
	    return monthCompareValue;
	} else {
	    return Integer.compare(day, toCompare.getDay());
	}
    }

    @Override
    public boolean equals(final Object toCompare) {
	if (!(toCompare instanceof SimpleDate)) {
	    return false;
	} else {
	    return equals((SimpleDate) toCompare, SimpleDates.getCurrentYear());
	}
    }

    public boolean equals(final SimpleDate toCompare, final int year) {
	if (day == toCompare.day && month == toCompare.month) {
	    return true;
	} else {
	    if (day == 29 && month == 2) {
		return toCompare.day == 1 && toCompare.month == 3 && SimpleDates.isSwitchingYear(year);
	    } else if (toCompare.day == 29 && toCompare.month == 2) {
		return day == 1 && month == 3 && SimpleDates.isSwitchingYear(year);
	    } else {
		return false;
	    }

	}
    }

    /**
     * @return day.month
     */
    @Override
    public String toString() {
	return day + "." + month;
    }

    /**
     * @return DD.MM with day and month
     */
    public String toStringWithLeadingZeros() {
	return (day < 10 ? "0" : "") + day + "." + (month < 10 ? "0" : "") + month;
    }

    public int getDay() {
	return day;
    }

    public int getMonth() {
	return month;
    }

}
