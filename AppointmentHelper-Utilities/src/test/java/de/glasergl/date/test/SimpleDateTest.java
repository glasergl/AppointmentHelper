package de.glasergl.date.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.glasergl.simpleDate.SimpleDate;

/**
 * @author Gabriel Glaser
 */
public class SimpleDateTest {

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
	public void testEqualsWith29February() {
		final SimpleDate february29 = new SimpleDate(29, 2);
		assertTrue(february29.equals(new SimpleDate(1, 3), 2019));
		assertFalse(february29.equals(new SimpleDate(1, 3), 2020));
	}

}
