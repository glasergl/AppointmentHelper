package ui;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import appointment.Appointment;
import appointment.SimpleDate;
import ui.calendar.CalendarCell;

public class TestCalendarCell {

    SimpleDate d1 = new SimpleDate(14, 7);
    SimpleDate d2 = new SimpleDate(18, 6);

    @Test
    public void testNoAppointments() {
	CalendarCell c = new CalendarCell(d1, List.of());
	assertEquals(getExpectedDateString(d1), c.toString());
    }

    @Test
    public void testNoAppointments1() {
	final Appointment a1 = new Appointment(d2, "Sam", "", true);
	final Appointment a2 = new Appointment(d2, "Frodo", "", false);
	final Appointment a3 = new Appointment(d2, "Gandalf", "", true);
	CalendarCell c = new CalendarCell(d1, List.of(a1, a2, a3));
	assertEquals(getExpectedDateString(d1), c.toString());
    }

    @Test
    public void testWithOne() {
	CalendarCell c = new CalendarCell(d1, List.of(new Appointment(d1, "Sam", "", true)));
	assertEquals(getExpectedCellString(d1, "Sam"), c.toString());
    }

    @Test
    public void testEdgeCaseOfNameLength() {
	String edgeWord = getEdgeWord();
	CalendarCell c = new CalendarCell(d1, List.of(new Appointment(d1, edgeWord, "", true)));
	assertEquals(getExpectedCellString(d1, edgeWord), c.toString());
    }

    @Test
    public void testSpecialCaseOfNameLength() {
	String specialCaseWord = getSpecialCaseWord();
	CalendarCell c = new CalendarCell(d1, List.of(new Appointment(d1, specialCaseWord, "", true)));
	assertEquals(getExpectedCellString(d1, "1 Geburtstag"), c.toString());
    }

    @Test
    public void testSpecialCaseWithoutBirthday() {
	String specialCaseWord = getSpecialCaseWord();
	CalendarCell c = new CalendarCell(d1, List.of(new Appointment(d1, specialCaseWord, "", false)));
	assertEquals(getExpectedCellString(d1, "1 Termin"), c.toString());
    }

    @Test
    public void testWithTwo() {
	final Appointment a1 = new Appointment(d1, "Sam", "", true);
	final Appointment a2 = new Appointment(d1, "Frodo", "", true);
	CalendarCell c = new CalendarCell(d1, List.of(a1, a2));
	assertEquals(getExpectedCellString(d1, "2 Geburtstage"), c.toString());
    }

    @Test
    public void testWithTwoWithoutAllBirthdays() {
	final Appointment a1 = new Appointment(d1, "Sam", "", false);
	final Appointment a2 = new Appointment(d1, "Frodo", "", true);
	CalendarCell c = new CalendarCell(d1, List.of(a1, a2));
	assertEquals(getExpectedCellString(d1, "2 Termine"), c.toString());
    }

    @Test
    public void testWithTwoButOnlyOneIsAt() {
	final Appointment a1 = new Appointment(d1, "Sam", "", false);
	final Appointment a2 = new Appointment(d2, "Frodo", "", true);
	CalendarCell c = new CalendarCell(d1, List.of(a1, a2));
	assertEquals(getExpectedCellString(d1, "Sam"), c.toString());
    }

    @Test
    public void testWithThree() {
	final Appointment a1 = new Appointment(d1, "Sam", "", true);
	final Appointment a2 = new Appointment(d1, "Frodo", "", true);
	final Appointment a3 = new Appointment(d1, "Gandalf", "", true);
	CalendarCell c = new CalendarCell(d1, List.of(a1, a2, a3));
	assertEquals(getExpectedCellString(d1, "3 Geburtstage"), c.toString());
    }

    @Test
    public void testWithThreeWithoutAllBirthdays() {
	final Appointment a1 = new Appointment(d1, "Sam", "", false);
	final Appointment a2 = new Appointment(d1, "Frodo", "", false);
	final Appointment a3 = new Appointment(d1, "Gandalf", "", true);
	CalendarCell c = new CalendarCell(d1, List.of(a1, a2, a3));
	assertEquals(getExpectedCellString(d1, "3 Termine"), c.toString());
    }

    @Test
    public void testWithThree1() {
	final Appointment a1 = new Appointment(d1, "Sam", "", false);
	final Appointment a2 = new Appointment(d2, "Frodo", "", false);
	final Appointment a3 = new Appointment(d1, "Gandalf", "", true);
	CalendarCell c = new CalendarCell(d1, List.of(a1, a2, a3));
	assertEquals(getExpectedCellString(d1, "2 Termine"), c.toString());
    }

    @Test
    public void testWithThree2() {
	final Appointment a1 = new Appointment(d1, "Sam", "", true);
	final Appointment a2 = new Appointment(d2, "Frodo", "", false);
	final Appointment a3 = new Appointment(d1, "Gandalf", "", true);
	CalendarCell c = new CalendarCell(d1, List.of(a1, a2, a3));
	assertEquals(getExpectedCellString(d1, "2 Geburtstage"), c.toString());
    }

    @Test
    public void testWithThree3() {
	final Appointment a1 = new Appointment(d2, "Sam", "", true);
	final Appointment a2 = new Appointment(d2, "Frodo", "", false);
	final Appointment a3 = new Appointment(d1, "Gandalf", "", true);
	CalendarCell c = new CalendarCell(d1, List.of(a1, a2, a3));
	assertEquals(getExpectedCellString(d1, "Gandalf"), c.toString());
    }

    private String getExpectedCellString(final SimpleDate toGetDateStringOf, final String rest) {
	return getExpectedDateString(toGetDateStringOf) + rest + " ";
    }

    private String getExpectedDateString(final SimpleDate toGetDateStringOf) {
	return String.valueOf(toGetDateStringOf.getDay()) + ".  ";
    }

    private String getEdgeWord() {
	String extremeWord = "";
	for (int i = 0; i < CalendarCell.MAXIMUM_NAME_LENGTH; i++) {
	    extremeWord += "a";
	}
	return extremeWord;
    }

    private String getSpecialCaseWord() {
	return getEdgeWord() + "a";
    }

}
