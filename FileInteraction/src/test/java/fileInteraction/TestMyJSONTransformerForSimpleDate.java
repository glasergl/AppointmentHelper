package fileInteraction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.json.JSONObject;
import org.junit.Test;
import appointment.SimpleDate;

public class TestMyJSONTransformerForSimpleDate {

    @Test
    public void testRepresentsSimpleDate() {
	JSONObject json = new JSONObject();
	json.put("day", 10);
	assertFalse(JSONTransformer.representsSimpleDate(json));
	json.put("month", 11);
	assertTrue(JSONTransformer.representsSimpleDate(json));
    }

    @Test
    public void testSimpleDateToJSON() {
	SimpleDate date = new SimpleDate(12, 3);
	JSONObject jsonOfA = JSONTransformer.simpleDateToJSON(date);
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
	SimpleDate date = JSONTransformer.jsonToSimpleDate(json);
	assertEquals(10, date.getDay());
	assertEquals(11, date.getMonth());
    }

}
