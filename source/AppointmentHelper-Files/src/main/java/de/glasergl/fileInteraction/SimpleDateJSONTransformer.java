package de.glasergl.fileInteraction;

import org.json.JSONObject;

import de.glasergl.simpleDate.SimpleDate;

/**
 * Class which contains static functions to transform JSONObjects to
 * SimpleDate-Objects and back.
 *
 * @author Gabriel Glaser
 * @version 19.1.2021
 */
public final class SimpleDateJSONTransformer {

    private SimpleDateJSONTransformer() {
    }

    /**
     * Transforms a JSONObject to a SimpleDate.
     *
     * @param jsonToTransform
     * @return The Date represented by the given JSONObject.
     */
    public static SimpleDate jsonToSimpleDate(final JSONObject jsonToTransform) {
	if (!representsSimpleDate(jsonToTransform)) {
	    throw new IllegalArgumentException(jsonToTransform + " doesnt represent a date.");
	}
	return new SimpleDate(jsonToTransform.getInt("day"), jsonToTransform.getInt("month"));
    }

    /**
     * Transforms a SimpleDate into a JSONObject.
     *
     * @param dateToTransform
     * @return The given Date transformed into a JSONObject.
     */
    public static JSONObject simpleDateToJSON(final SimpleDate dateToTransform) {
	final JSONObject json = new JSONObject();
	json.put("day", dateToTransform.getDay());
	json.put("month", dateToTransform.getMonth());
	return json;
    }

    /**
     * Tests whether the given JSON represents a SimpleDate.
     *
     * @param jsonToTest
     * @return True, if the given JSON represents a SimpleDate.
     */
    public static boolean representsSimpleDate(final JSONObject jsonToTest) {
	final int numberOfKeys = jsonToTest.keySet().size();
	return jsonToTest.has("day") && jsonToTest.has("month") && numberOfKeys == 2;
    }

}
