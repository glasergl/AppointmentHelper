package fileInteraction.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.json.JSONObject;
import org.junit.Test;
import date.SimpleDate;
import fileInteraction.SimpleDateJSONTransformer;

/**
 * @author Gabriel Glaser
 * @version 19.01.2022
 */
public class TestSimpleDateJSONTransformer {

    @Test
    public void testRepresentsSimpleDate() {
	JSONObject json = new JSONObject();
	json.put("day", 10);

	assertFalse(SimpleDateJSONTransformer.representsSimpleDate(json));
	json.put("month", 11);
	assertTrue(SimpleDateJSONTransformer.representsSimpleDate(json));
    }

    @Test
    public void testSimpleDateToJSON() {
	SimpleDate date = new SimpleDate(12, 3);
	JSONObject jsonOfA = SimpleDateJSONTransformer.simpleDateToJSON(date);

	assertTrue(jsonOfA.has("day"));
	assertTrue(jsonOfA.has("month"));

	assertEquals(12, jsonOfA.getInt("day"));
	assertEquals(3, jsonOfA.getInt("month"));
    }

    @Test
    public void testJSONToSimpleDate() {
	JSONObject json = new JSONObject();
	json.put("day", 10);
	json.put("month", 11);
	SimpleDate date = SimpleDateJSONTransformer.jsonToSimpleDate(json);

	assertEquals(10, date.getDay());
	assertEquals(11, date.getMonth());
    }

}
