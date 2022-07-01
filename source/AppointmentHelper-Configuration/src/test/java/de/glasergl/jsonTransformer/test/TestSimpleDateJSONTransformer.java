package de.glasergl.jsonTransformer.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.json.JSONObject;
import org.junit.Test;

import de.glasergl.jsonTransformer.SimpleDateJSONTransformer;
import de.glasergl.simpleDate.SimpleDate;

/**
 * Tests the transforming of JSONObjects to SimpleDates and back.
 * 
 * @author Gabriel Glaser
 * @version 30.06.2022
 */
public class TestSimpleDateJSONTransformer {

    @Test
    public void testRepresentsSimpleDate() {
	final JSONObject json = new JSONObject();
	json.put("day", 10);

	assertFalse(SimpleDateJSONTransformer.representsSimpleDate(json));
	json.put("month", 11);
	assertTrue(SimpleDateJSONTransformer.representsSimpleDate(json));
    }

    @Test
    public void testSimpleDateToJSON() {
	final SimpleDate date = new SimpleDate(12, 3);
	final JSONObject jsonOfA = SimpleDateJSONTransformer.simpleDateToJSON(date);

	assertTrue(jsonOfA.has("day"));
	assertTrue(jsonOfA.has("month"));

	assertEquals(12, jsonOfA.getInt("day"));
	assertEquals(3, jsonOfA.getInt("month"));
    }

    @Test
    public void testJSONToSimpleDate() {
	final JSONObject json = new JSONObject();
	json.put("day", 10);
	json.put("month", 11);
	final SimpleDate date = SimpleDateJSONTransformer.jsonToSimpleDate(json);

	assertEquals(10, date.getDay());
	assertEquals(11, date.getMonth());
    }

}
