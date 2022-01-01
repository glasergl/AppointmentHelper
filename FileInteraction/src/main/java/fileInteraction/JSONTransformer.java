package fileInteraction;

import org.json.JSONObject;
import appointment.Appointment;
import date.SimpleDate;

/**
 * @author Gabriel Glaser
 * @version 31.12.2021
 */
public final class JSONTransformer {

    private JSONTransformer() {
    }

    /**
     * Transforms a JSONObject to an Appointment.
     * 
     * @param jsonToTransform
     * @return The Appointment represented by the given JSONObject.
     */
    public static Appointment jsonToAppointment(final JSONObject jsonToTransform) {
	if (!representsAppointment(jsonToTransform)) {
	    throw new IllegalArgumentException(jsonToTransform + " is not a representation of an appointment.");
	} else {
	    final SimpleDate ofJSON = jsonToSimpleDate(jsonToTransform.getJSONObject("date"));
	    return new Appointment(ofJSON, jsonToTransform.getString("name"), jsonToTransform.getString("description"), jsonToTransform.getBoolean("isABirthday"));
	}
    }

    /**
     * Transforms a JSONObject to a Date.
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
     * Transforms an Appointment into a JSONObject.
     * 
     * @param appointmentToTransform
     * @return The given Appointment transformed into a JSONObject.
     */
    public static JSONObject appointmentToJSON(final Appointment appointmentToTransform) {
	JSONObject json = new JSONObject();
	json.put("date", simpleDateToJSON(appointmentToTransform.getDate()));
	json.put("name", appointmentToTransform.getName());
	json.put("description", appointmentToTransform.getDescription());
	json.put("isABirthday", appointmentToTransform.isBirthday());
	return json;
    }

    /**
     * Transforms a Date into a JSONObject.
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
     * Tests whether the given JSON represents an Appointment.
     * 
     * @param jsonToTest
     * @return True, if the given JSON represents an Appointment.
     */
    public static boolean representsAppointment(final JSONObject jsonToTest) {
	return jsonToTest.has("date") && jsonToTest.has("name") && jsonToTest.has("description") && jsonToTest.has("isABirthday");
    }

    /**
     * Tests whether the given JSON represents a SimpleDate.
     * 
     * @param jsonToTest
     * @return True, if the given JSON represents a SimpleDate.
     */
    public static boolean representsSimpleDate(final JSONObject jsonToTest) {
	return jsonToTest.has("day") && jsonToTest.has("month");
    }

}
