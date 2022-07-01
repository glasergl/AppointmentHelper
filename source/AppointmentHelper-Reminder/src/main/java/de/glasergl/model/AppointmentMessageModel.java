package de.glasergl.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import de.glasergl.appointment.Appointment;

/**
 * Class which calculates a representative text of a list of appointments at a
 * given time.
 *
 * The whole text is stored in the List components. Additionally, the given Map
 * maps each index of the List components, which represents a name of an
 * Appointment, to the respective Appointment.
 *
 * @author Gabriel Glaser
 * @version 03.04.2022
 */
public final class AppointmentMessageModel {

    private final String timeDescription;
    private final List<String> components;
    private final List<Appointment> birthdays;
    private final List<Appointment> nonBirthdays;
    private final Map<Integer, Appointment> componentAppointmentMap = new HashMap<>();

    /**
     * Creates the components for the message which is retrievable through
     * getComponents().
     *
     * @param timeDescription
     * @param timeCondition
     * @param allAppointments
     */
    public AppointmentMessageModel(final String timeDescription, final Predicate<Appointment> timeCondition, final List<Appointment> allAppointments) {
	super();
	this.timeDescription = timeDescription;
	final List<Appointment> appointmentsAtTimeDescription = allAppointments.stream().filter(timeCondition).collect(Collectors.toList());
	this.birthdays = appointmentsAtTimeDescription.stream().filter(Appointment::isBirthday).collect(Collectors.toList());
	this.nonBirthdays = appointmentsAtTimeDescription.stream().filter(appointment -> {
	    return !appointment.isBirthday();
	}).collect(Collectors.toList());
	this.components = calculateComponents();
    }

    /**
     * @return An unmodifiable List of the components.
     */
    public List<String> getComponents() {
	return Collections.unmodifiableList(components);
    }

    /**
     * @return All components appended to one String.
     */
    public String getAppointmentMessage() {
	final StringBuilder wholeText = new StringBuilder();
	for (final String component : components) {
	    wholeText.append(component);
	}
	return wholeText.toString();
    }

    /**
     * @param index
     * @return True, if the String at index is representing the name of an
     *         Appointment, else false.
     */
    public boolean isName(final int index) {
	return componentAppointmentMap.containsKey(index);
    }

    /**
     * @param index
     * @return The Appointment which name is at the index of components.
     */
    public Appointment getAppointment(final int index) {
	if (!isName(index)) {
	    throw new IllegalArgumentException("At " + index + " is no name.");
	} else {
	    return componentAppointmentMap.get(index);
	}
    }

    /**
     * Calculates the List of all components needed for the message.
     *
     * @return The List of all components.
     */
    private List<String> calculateComponents() {
	final List<String> allComponents = new LinkedList<>();
	if (birthdays.size() > 0 || nonBirthdays.size() > 0) {
	    allComponents.add(timeDescription);
	    allComponents.add(" ");
	}
	addBirthdayLabels(allComponents);
	if (birthdays.size() > 0 && nonBirthdays.size() > 0) {
	    allComponents.add("und es");
	    allComponents.add(" ");
	}
	addNonBirthdayLabels(allComponents);
	return allComponents;
    }

    /**
     * Adds all enumerated birthdays to the given List.
     *
     * @param components
     */
    private void addBirthdayLabels(final List<String> components) {
	if (birthdays.size() > 0) {
	    components.add(birthdays.size() > 1 ? "haben" : "hat");
	    components.add(" ");
	    enumerateAppointmentNamesAndAddMapping(birthdays, components);
	    components.add(" ");
	    components.add("Geburtstag");
	    components.add(nonBirthdays.isEmpty() ? "!" : " ");
	}
    }

    /**
     * Adds all enumerated non-birthdays to the given List.
     *
     * @param components
     */
    private void addNonBirthdayLabels(final List<String> components) {
	if (nonBirthdays.size() > 0) {
	    components.add(nonBirthdays.size() > 1 ? "sind" : "ist");
	    components.add(" ");
	    enumerateAppointmentNamesAndAddMapping(nonBirthdays, components);
	    components.add(".");
	}
    }

    /**
     * Enumerates the names of the given Appointments and stores them in the given
     * List.
     *
     * Furthermore, for each name, the index of the name in the resulting List
     * components is mapped to the respective Appointment. This is necessary to
     * differentiate between necessary text, like commas, spaces etc. and the names
     * of the Appointments.
     *
     * @param appointmentsToEnumerate
     * @param components
     */
    private void enumerateAppointmentNamesAndAddMapping(final List<Appointment> appointmentsToEnumerate, final List<String> components) {
	for (int i = 0; i < appointmentsToEnumerate.size(); i++) {
	    final Appointment appointment = appointmentsToEnumerate.get(i);
	    components.add(appointment.getName());
	    componentAppointmentMap.put(components.size() - 1, appointment);
	    if (i < appointmentsToEnumerate.size() - 1) {
		if (i < appointmentsToEnumerate.size() - 2) {
		    components.add(", ");
		} else {
		    components.add(" und ");
		}
	    }
	}
    }

}
