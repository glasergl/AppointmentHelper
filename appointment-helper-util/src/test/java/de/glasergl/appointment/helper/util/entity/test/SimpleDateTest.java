package de.glasergl.appointment.helper.util.entity.test;

import de.glasergl.appointment.helper.util.entity.SimpleDate;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author glasergl
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
        assertEquals(new SimpleDate(10, 10), new SimpleDate(10, 10));
        assertEquals(new SimpleDate(7, 5), new SimpleDate(7, 5));
        assertEquals(new SimpleDate(17, 12), new SimpleDate(17, 12));
    }

    @Test
    public void testNotEqualsWithNormalCases() {
        assertNotEquals(new SimpleDate(5, 10), new SimpleDate(7, 8));
        assertNotEquals(new SimpleDate(2, 4), new SimpleDate(10, 6));
        assertNotEquals(new SimpleDate(15, 2), new SimpleDate(16, 11));
    }

    @Test
    public void testEqualsWith29February() {
        final SimpleDate february29 = new SimpleDate(29, 2);
        assertTrue(february29.equals(new SimpleDate(1, 3), 2019));
        assertFalse(february29.equals(new SimpleDate(1, 3), 2020));
    }

}
