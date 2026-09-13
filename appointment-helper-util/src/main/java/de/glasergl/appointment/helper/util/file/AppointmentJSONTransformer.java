package de.glasergl.appointment.helper.util.file;

import de.glasergl.appointment.helper.util.entity.Appointment;
import de.glasergl.appointment.helper.util.entity.SimpleDate;
import org.json.JSONObject;

/**
 * Class which contains functions to transform JSONObjects to
 * Appointment-Objects and back.
 *
 * @author glasergl
 */
public final class AppointmentJSONTransformer {
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
            return new Appointment(ofJSON, jsonToTransform.getString("name"),
                    jsonToTransform.getBoolean("isABirthday"));
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
        json.put("date", SimpleDateJSONTransformer.simpleDateToJSON(appointmentToTransform.date()));
        json.put("name", appointmentToTransform.name());
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
        return jsonToTest.has("date") && jsonToTest.has("name") && jsonToTest.has("isABirthday") && numberOfKeys == 3;
    }
}
