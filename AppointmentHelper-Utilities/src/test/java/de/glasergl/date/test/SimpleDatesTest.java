package de.glasergl.date.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.glasergl.simpleDate.SimpleDates;

/**
 * @author Gabriel Glaser
 */
public class SimpleDatesTest {

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
