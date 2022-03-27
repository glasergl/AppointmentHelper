package calendar.test;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collections;
import org.junit.Test;
import appointment.Appointment;
import calendar.CalendarCell;
import date.SimpleDate;

/**
 * @author Gabriel Glaser
 * @version 27.03.2022
 */
public class TestCalendarCell {

    static SimpleDate testDate1 = new SimpleDate(14, 7);
    static SimpleDate testDate2 = new SimpleDate(18, 6);

    @Test
    public void testNoAppointments() {
	CalendarCell calendarCell = new CalendarCell(testDate1, Collections.emptyList());
	assertEquals(getExpectedDateString(testDate1), calendarCell.toString());
    }

    @Test
    public void testNoAppointments1() {
	final Appointment appointment1 = new Appointment(testDate2, "Sam", "", true);
	final Appointment appointment2 = new Appointment(testDate2, "Frodo", "", false);
	final Appointment appointment3 = new Appointment(testDate2, "Gandalf", "", true);
	CalendarCell calendarCell = new CalendarCell(testDate1, Arrays.asList(appointment1, appointment2, appointment3));
	assertEquals(getExpectedDateString(testDate1), calendarCell.toString());
    }

    @Test
    public void testWithOne() {
	CalendarCell calendarCell = new CalendarCell(testDate1, Arrays.asList(new Appointment(testDate1, "Sam", "", true)));
	assertEquals(getExpectedCellString(testDate1, "Sam"), calendarCell.toString());
    }

    @Test
    public void testSpecialCaseOfNameLength() {
	String specialCaseWord = "MMMMMMMMMMMMMMMM";
	CalendarCell calendarCell = new CalendarCell(testDate1, Arrays.asList(new Appointment(testDate1, specialCaseWord, "", true)));
	assertEquals(getExpectedCellString(testDate1, "1 Geburtstag"), calendarCell.toString());
    }

    @Test
    public void testSpecialCaseWithoutBirthday() {
	String specialCaseWord = "MMMMMMMMMMMMMMMM";
	CalendarCell calendarCell = new CalendarCell(testDate1, Arrays.asList(new Appointment(testDate1, specialCaseWord, "", false)));
	assertEquals(getExpectedCellString(testDate1, "1 Termin"), calendarCell.toString());
    }

    @Test
    public void testWithTwo() {
	final Appointment appointment1 = new Appointment(testDate1, "Sam", "", true);
	final Appointment appointment2 = new Appointment(testDate1, "Frodo", "", true);
	CalendarCell calendarCell = new CalendarCell(testDate1, Arrays.asList(appointment1, appointment2));
	assertEquals(getExpectedCellString(testDate1, "2 Geburtstage"), calendarCell.toString());
    }

    @Test
    public void testWithTwoWithoutAllBirthdays() {
	final Appointment appointment1 = new Appointment(testDate1, "Sam", "", false);
	final Appointment appointment2 = new Appointment(testDate1, "Frodo", "", true);
	CalendarCell calendarCell = new CalendarCell(testDate1, Arrays.asList(appointment1, appointment2));
	assertEquals(getExpectedCellString(testDate1, "2 Termine"), calendarCell.toString());
    }

    @Test
    public void testWithTwoButOnlyOneIsAt() {
	final Appointment appointment1 = new Appointment(testDate1, "Sam", "", false);
	final Appointment appointment2 = new Appointment(testDate2, "Frodo", "", true);
	CalendarCell calendarCell = new CalendarCell(testDate1, Arrays.asList(appointment1, appointment2));
	assertEquals(getExpectedCellString(testDate1, "Sam"), calendarCell.toString());
    }

    @Test
    public void testWithThree() {
	final Appointment appointment1 = new Appointment(testDate1, "Sam", "", true);
	final Appointment appointment2 = new Appointment(testDate1, "Frodo", "", true);
	final Appointment appointment3 = new Appointment(testDate1, "Gandalf", "", true);
	CalendarCell calendarCell = new CalendarCell(testDate1, Arrays.asList(appointment1, appointment2, appointment3));
	assertEquals(getExpectedCellString(testDate1, "3 Geburtstage"), calendarCell.toString());
    }

    @Test
    public void testWithThreeWithoutAllBirthdays() {
	final Appointment appointment1 = new Appointment(testDate1, "Sam", "", false);
	final Appointment appointment2 = new Appointment(testDate1, "Frodo", "", false);
	final Appointment appointment3 = new Appointment(testDate1, "Gandalf", "", true);
	CalendarCell calendarCell = new CalendarCell(testDate1, Arrays.asList(appointment1, appointment2, appointment3));
	assertEquals(getExpectedCellString(testDate1, "3 Termine"), calendarCell.toString());
    }

    @Test
    public void testWithThree1() {
	final Appointment appointment1 = new Appointment(testDate1, "Sam", "", false);
	final Appointment appointment2 = new Appointment(testDate2, "Frodo", "", false);
	final Appointment appointment3 = new Appointment(testDate1, "Gandalf", "", true);
	CalendarCell calendarCell = new CalendarCell(testDate1, Arrays.asList(appointment1, appointment2, appointment3));
	assertEquals(getExpectedCellString(testDate1, "2 Termine"), calendarCell.toString());
    }

    @Test
    public void testWithThree2() {
	final Appointment appointment1 = new Appointment(testDate1, "Sam", "", true);
	final Appointment appointment2 = new Appointment(testDate2, "Frodo", "", false);
	final Appointment appointment3 = new Appointment(testDate1, "Gandalf", "", true);
	CalendarCell calendarCell = new CalendarCell(testDate1, Arrays.asList(appointment1, appointment2, appointment3));
	assertEquals(getExpectedCellString(testDate1, "2 Geburtstage"), calendarCell.toString());
    }

    @Test
    public void testWithThree3() {
	final Appointment appointment1 = new Appointment(testDate2, "Sam", "", true);
	final Appointment appointment2 = new Appointment(testDate2, "Frodo", "", false);
	final Appointment appointment3 = new Appointment(testDate1, "Gandalf", "", true);
	CalendarCell calendarCell = new CalendarCell(testDate1, Arrays.asList(appointment1, appointment2, appointment3));
	assertEquals(getExpectedCellString(testDate1, "Gandalf"), calendarCell.toString());
    }

    private String getExpectedCellString(final SimpleDate toGetDateStringOf, final String rest) {
	return getExpectedDateString(toGetDateStringOf) + rest + " ";
    }

    private String getExpectedDateString(final SimpleDate toGetDateStringOf) {
	return String.valueOf(toGetDateStringOf.getDay()) + ".  ";
    }

}
