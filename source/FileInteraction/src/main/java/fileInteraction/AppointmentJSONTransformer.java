package fileInteraction;

import org.json.JSONObject;

import appointment.Appointment;
import simpleDate.SimpleDate;

/**
 * Class which contains static functions to transform JSONObjects to
 * Appointment-Objects and back.
 *
 * @author Gabriel Glaser
 * @version 19.1.2021
 */
public final class AppointmentJSONTransformer {

    private AppointmentJSONTransformer() {
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
	    final SimpleDate ofJSON = SimpleDateJSONTransformer.jsonToSimpleDate(jsonToTransform.getJSONObject("date"));
	    return new Appointment(ofJSON, jsonToTransform.getString("name"), jsonToTransform.getString("description"), jsonToTransform.getBoolean("isABirthday"));
	}
    }

    /**
     * Transforms an Appointment into a JSONObject.
     *
     * @param appointmentToTransform
     * @return The given Appointment transformed into a JSONObject.
     */
    public static JSONObject appointmentToJSON(final Appointment appointmentToTransform) {
	JSONObject json = new JSONObject();
	json.put("date", SimpleDateJSONTransformer.simpleDateToJSON(appointmentToTransform.getDate()));
	json.put("name", appointmentToTransform.getName());
	json.put("description", appointmentToTransform.getDescription());
	json.put("isABirthday", appointmentToTransform.isBirthday());
	return json;
    }

    /**
     * Tests whether the given JSON represents an Appointment.
     *
     * @param jsonToTest
     * @return True, if the given JSON represents an Appointment.
     */
    public static boolean representsAppointment(final JSONObject jsonToTest) {
	final int numberOfKeys = jsonToTest.keySet().size();
	return jsonToTest.has("date") && jsonToTest.has("name") && jsonToTest.has("description") && jsonToTest.has("isABirthday") && numberOfKeys == 4;
    }

}
