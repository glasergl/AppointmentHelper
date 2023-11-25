package de.glasergl.calendar.cell.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

import de.glasergl.appointment.Appointment;
import de.glasergl.calendar.cell.AppointmentSummary;
import de.glasergl.simpleDate.SimpleDate;

/**
 * @author Gabriel Glaser
 * @version 30.03.2022
 */
public class TestAppointmentSummary {

    static SimpleDate testDate1 = new SimpleDate(14, 7);
    static SimpleDate testDate2 = new SimpleDate(18, 6);

    @Test
    public void testNoAppointments() {
	assertEquals("", AppointmentSummary.getSummary(Collections.emptyList()));
    }

    @Test
    public void testWithOne() {
	assertEquals("Sam", AppointmentSummary.getSummary(Arrays.asList(new Appointment(testDate1, "Sam", "", true))));
    }

    @Test
    public void testSpecialCaseOfNameLength() {
	String specialCaseWord = "MMMMMMMMMMMMMMMM";
	assertEquals("Ein Geburtstag", AppointmentSummary.getSummary(Arrays.asList(new Appointment(testDate1, specialCaseWord, "", true))));
    }

    @Test
    public void testSpecialCaseWithoutBirthday() {
	String specialCaseWord = "MMMMMMMMMMMMMMMM";
	assertEquals("Ein Termin", AppointmentSummary.getSummary(Arrays.asList(new Appointment(testDate1, specialCaseWord, "", false))));
    }

    @Test
    public void testWithTwo() {
	final Appointment appointment1 = new Appointment(testDate1, "Sam", "", true);
	final Appointment appointment2 = new Appointment(testDate1, "Frodo", "", true);
	assertEquals("2 Geburtstage", AppointmentSummary.getSummary(Arrays.asList(appointment1, appointment2)));
    }

    @Test
    public void testWithTwoWithoutAllBirthdays() {
	final Appointment appointment1 = new Appointment(testDate1, "Sam", "", false);
	final Appointment appointment2 = new Appointment(testDate1, "Frodo", "", true);
	assertEquals("2 Termine", AppointmentSummary.getSummary(Arrays.asList(appointment1, appointment2)));
    }

    @Test
    public void testWithThree() {
	final Appointment appointment1 = new Appointment(testDate1, "Sam", "", true);
	final Appointment appointment2 = new Appointment(testDate1, "Frodo", "", true);
	final Appointment appointment3 = new Appointment(testDate1, "Gandalf", "", true);
	assertEquals("3 Geburtstage", AppointmentSummary.getSummary(Arrays.asList(appointment1, appointment2, appointment3)));
    }

    @Test
    public void testWithThreeWithoutAllBirthdays() {
	final Appointment appointment1 = new Appointment(testDate1, "Sam", "", false);
	final Appointment appointment2 = new Appointment(testDate1, "Frodo", "", false);
	final Appointment appointment3 = new Appointment(testDate1, "Gandalf", "", true);
	assertEquals("3 Termine", AppointmentSummary.getSummary(Arrays.asList(appointment1, appointment2, appointment3)));
    }

}
