package date.test;

import static org.junit.Assert.*;
import org.junit.Test;

import date.SimpleDate;
import date.SimpleDates;

/**
 * @author Gabriel Glaser
 * @version 13.10.2020
 */
public class TestSimpleDate {

    @Test
    public void testCompareTo() {
	assertTrue(new SimpleDate(10, 12).compareTo(new SimpleDate(3, 7)) > 0);
	assertTrue(new SimpleDate(5, 5).compareTo(new SimpleDate(2, 7)) < 0);
	assertEquals(0, new SimpleDate(12, 3).compareTo(new SimpleDate(12, 3)));
    }

    @Test
    public void testEqualsWithNormalCases() {
	assertTrue(new SimpleDate(10, 10).equals(new SimpleDate(10, 10)));
	assertTrue(new SimpleDate(7, 5).equals(new SimpleDate(7, 5)));
	assertTrue(new SimpleDate(17, 12).equals(new SimpleDate(17, 12)));
    }

    @Test
    public void testNotEqualsWithNormalCases() {
	assertFalse(new SimpleDate(5, 10).equals(new SimpleDate(7, 8)));
	assertFalse(new SimpleDate(2, 4).equals(new SimpleDate(10, 6)));
	assertFalse(new SimpleDate(15, 2).equals(new SimpleDate(16, 11)));
    }

    @Test
    public void testEqualsSpecialCase() {
	final SimpleDate february29 = new SimpleDate(29, 2);
	assertTrue(february29.equals(new SimpleDate(29, 2), 2020));
	assertTrue(february29.equals(new SimpleDate(1, 3), 2020));
	assertFalse(february29.equals(new SimpleDate(1, 3), 2019));
    }

    @Test
    public void testNormalCorrectDateValues() {
	assertTrue(SimpleDates.isValidDate(19, 5));
	assertTrue(SimpleDates.isValidDate(30, 6));
	assertTrue(SimpleDates.isValidDate(31, 10));
	assertTrue(SimpleDates.isValidDate(31, 8));
    }

    @Test
    public void testExtremeCorrectDateValues() {
	assertTrue(SimpleDates.isValidDate(31, 3));
	assertTrue(SimpleDates.isValidDate(1, 2));
	assertTrue(SimpleDates.isValidDate(31, 7));
	assertTrue(SimpleDates.isValidDate(31, 8));
	assertTrue(SimpleDates.isValidDate(30, 9));
	assertTrue(SimpleDates.isValidDate(29, 2));
    }

    @Test
    public void testNormalFalseDateValues() {
	assertFalse(SimpleDates.isValidDate(120, 21));
	assertFalse(SimpleDates.isValidDate(-1212, 21));
	assertFalse(SimpleDates.isValidDate(120, -31));
	assertFalse(SimpleDates.isValidDate(-12, -333));
    }

    @Test
    public void testExtremeFalseDateValues() {
	assertFalse(SimpleDates.isValidDate(31, 4));
	assertFalse(SimpleDates.isValidDate(30, 2));
	assertFalse(SimpleDates.isValidDate(31, 2));
	assertFalse(SimpleDates.isValidDate(31, 9));
	assertFalse(SimpleDates.isValidDate(31, 11));
	assertFalse(SimpleDates.isValidDate(32, 12));
    }

    @Test
    public void testIsSwitchingYearForSwitchingYears() {
	assertTrue(SimpleDates.isSwitchingYear(2020));
	assertTrue(SimpleDates.isSwitchingYear(2604));
	assertTrue(SimpleDates.isSwitchingYear(3200));
	assertTrue(SimpleDates.isSwitchingYear(2028));
	assertTrue(SimpleDates.isSwitchingYear(2400));
    }

    @Test
    public void testIsSwitchingYearForNonSwitchingYears() {
	assertFalse(SimpleDates.isSwitchingYear(2019));
	assertFalse(SimpleDates.isSwitchingYear(2500));
	assertFalse(SimpleDates.isSwitchingYear(2600));
	assertFalse(SimpleDates.isSwitchingYear(2601));
	assertFalse(SimpleDates.isSwitchingYear(2801));
	assertFalse(SimpleDates.isSwitchingYear(3263));
	assertFalse(SimpleDates.isSwitchingYear(3202));
    }

}
