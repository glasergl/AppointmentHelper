package calendar.test;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collections;
import org.junit.Test;
import appointment.Appointment;
import calendar.CalendarCellAppointmentsSummary;
import simpleDate.SimpleDate;

/**
 * @author Gabriel Glaser
 * @version 30.03.2022
 */
public class TestCalendarCellAppointmentsSummary {

    static SimpleDate testDate1 = new SimpleDate(14, 7);
    static SimpleDate testDate2 = new SimpleDate(18, 6);

    @Test
    public void testNoAppointments() {
	CalendarCellAppointmentsSummary appointmentsSummary = new CalendarCellAppointmentsSummary(testDate1, Collections.emptyList());
	assertEquals("", appointmentsSummary.getSummaryText());
    }

    @Test
    public void testNoAppointments1() {
	final Appointment appointment1 = new Appointment(testDate2, "Sam", "", true);
	final Appointment appointment2 = new Appointment(testDate2, "Frodo", "", false);
	final Appointment appointment3 = new Appointment(testDate2, "Gandalf", "", true);
	CalendarCellAppointmentsSummary appointmentsSummary = new CalendarCellAppointmentsSummary(testDate1, Arrays.asList(appointment1, appointment2, appointment3));
	assertEquals("", appointmentsSummary.getSummaryText());
    }

    @Test
    public void testWithOne() {
	CalendarCellAppointmentsSummary appointmentsSummary = new CalendarCellAppointmentsSummary(testDate1, Arrays.asList(new Appointment(testDate1, "Sam", "", true)));
	assertEquals("Sam", appointmentsSummary.getSummaryText());
    }

    @Test
    public void testSpecialCaseOfNameLength() {
	String specialCaseWord = "MMMMMMMMMMMMMMMM";
	CalendarCellAppointmentsSummary appointmentsSummary = new CalendarCellAppointmentsSummary(testDate1, Arrays.asList(new Appointment(testDate1, specialCaseWord, "", true)));
	assertEquals("1 Geburtstag", appointmentsSummary.getSummaryText());
    }

    @Test
    public void testSpecialCaseWithoutBirthday() {
	String specialCaseWord = "MMMMMMMMMMMMMMMM";
	CalendarCellAppointmentsSummary appointmentsSummary = new CalendarCellAppointmentsSummary(testDate1, Arrays.asList(new Appointment(testDate1, specialCaseWord, "", false)));
	assertEquals("1 Termin", appointmentsSummary.getSummaryText());
    }

    @Test
    public void testWithTwo() {
	final Appointment appointment1 = new Appointment(testDate1, "Sam", "", true);
	final Appointment appointment2 = new Appointment(testDate1, "Frodo", "", true);
	CalendarCellAppointmentsSummary appointmentsSummary = new CalendarCellAppointmentsSummary(testDate1, Arrays.asList(appointment1, appointment2));
	assertEquals("2 Geburtstage", appointmentsSummary.getSummaryText());
    }

    @Test
    public void testWithTwoWithoutAllBirthdays() {
	final Appointment appointment1 = new Appointment(testDate1, "Sam", "", false);
	final Appointment appointment2 = new Appointment(testDate1, "Frodo", "", true);
	CalendarCellAppointmentsSummary appointmentsSummary = new CalendarCellAppointmentsSummary(testDate1, Arrays.asList(appointment1, appointment2));
	assertEquals("2 Termine", appointmentsSummary.getSummaryText());
    }

    @Test
    public void testWithTwoButOnlyOneIsAt() {
	final Appointment appointment1 = new Appointment(testDate1, "Sam", "", false);
	final Appointment appointment2 = new Appointment(testDate2, "Frodo", "", true);
	CalendarCellAppointmentsSummary appointmentsSummary = new CalendarCellAppointmentsSummary(testDate1, Arrays.asList(appointment1, appointment2));
	assertEquals("Sam", appointmentsSummary.getSummaryText());
    }

    @Test
    public void testWithThree() {
	final Appointment appointment1 = new Appointment(testDate1, "Sam", "", true);
	final Appointment appointment2 = new Appointment(testDate1, "Frodo", "", true);
	final Appointment appointment3 = new Appointment(testDate1, "Gandalf", "", true);
	CalendarCellAppointmentsSummary appointmentsSummary = new CalendarCellAppointmentsSummary(testDate1, Arrays.asList(appointment1, appointment2, appointment3));
	assertEquals("3 Geburtstage", appointmentsSummary.getSummaryText());
    }

    @Test
    public void testWithThreeWithoutAllBirthdays() {
	final Appointment appointment1 = new Appointment(testDate1, "Sam", "", false);
	final Appointment appointment2 = new Appointment(testDate1, "Frodo", "", false);
	final Appointment appointment3 = new Appointment(testDate1, "Gandalf", "", true);
	CalendarCellAppointmentsSummary appointmentsSummary = new CalendarCellAppointmentsSummary(testDate1, Arrays.asList(appointment1, appointment2, appointment3));
	assertEquals("3 Termine", appointmentsSummary.getSummaryText());
    }

    @Test
    public void testWithThree1() {
	final Appointment appointment1 = new Appointment(testDate1, "Sam", "", false);
	final Appointment appointment2 = new Appointment(testDate2, "Frodo", "", false);
	final Appointment appointment3 = new Appointment(testDate1, "Gandalf", "", true);
	CalendarCellAppointmentsSummary appointmentsSummary = new CalendarCellAppointmentsSummary(testDate1, Arrays.asList(appointment1, appointment2, appointment3));
	assertEquals("2 Termine", appointmentsSummary.getSummaryText());
    }

    @Test
    public void testWithThree2() {
	final Appointment appointment1 = new Appointment(testDate1, "Sam", "", true);
	final Appointment appointment2 = new Appointment(testDate2, "Frodo", "", false);
	final Appointment appointment3 = new Appointment(testDate1, "Gandalf", "", true);
	CalendarCellAppointmentsSummary appointmentsSummary = new CalendarCellAppointmentsSummary(testDate1, Arrays.asList(appointment1, appointment2, appointment3));
	assertEquals("2 Geburtstage", appointmentsSummary.getSummaryText());
    }

    @Test
    public void testWithThree3() {
	final Appointment appointment1 = new Appointment(testDate2, "Sam", "", true);
	final Appointment appointment2 = new Appointment(testDate2, "Frodo", "", false);
	final Appointment appointment3 = new Appointment(testDate1, "Gandalf", "", true);
	CalendarCellAppointmentsSummary appointmentsSummary = new CalendarCellAppointmentsSummary(testDate1, Arrays.asList(appointment1, appointment2, appointment3));
	assertEquals("Gandalf", appointmentsSummary.getSummaryText());
    }

}
