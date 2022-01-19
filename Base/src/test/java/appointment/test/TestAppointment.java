package appointment.test;

import static org.junit.Assert.*;

import org.junit.Test;

import appointment.Appointment;
import date.SimpleDate;

/**
 * @author Gabriel Glaser
 * @version 19.01.2022
 */
public class TestAppointment {

    @Test
    public void testCompareToLevel1() {
	Appointment a1 = new Appointment(new SimpleDate(12, 5), "Tom");
	Appointment a2 = new Appointment(new SimpleDate(24, 3), "Tom");
	assertTrue(a1.compareTo(a2) > 0);
    }

    @Test
    public void testCompareToLevel2() {
	Appointment a1 = new Appointment(new SimpleDate(12, 5), "Tom");
	Appointment a2 = new Appointment(new SimpleDate(12, 5), "Peter");
	assertTrue(a1.compareTo(a2) > 0);
    }

    @Test
    public void testCompareToLevel3() {
	Appointment a1 = new Appointment(new SimpleDate(12, 5), "Tom", "TestB", true);
	Appointment a2 = new Appointment(new SimpleDate(12, 5), "Tom", "TestC", true);
	assertTrue(a1.compareTo(a2) < 0);
    }

    @Test
    public void testCompareToLevel4() {
	Appointment a1 = new Appointment(new SimpleDate(12, 5), "Tom", "TestA", false);
	Appointment a2 = new Appointment(new SimpleDate(12, 5), "Tom", "TestA", true);
	assertTrue(a1.compareTo(a2) < 0);
    }

    public void testCompareToEquals() {
	Appointment a1 = new Appointment(new SimpleDate(12, 5), "Tom", "TestH", false);
	Appointment a2 = new Appointment(new SimpleDate(12, 5), "Tom", "TestH", false);
	assertEquals(0, a1.compareTo(a2));
    }

}
