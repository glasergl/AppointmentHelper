package appointment;

import java.util.List;

/**
 * Class which contains functions about Appointments.
 * 
 * @author Gabriel Glaser
 * @version 29.03.2022
 */
public final class Appointments {

    /**
     * Sorts the given appointments by the standard comparator.
     * 
     * @param appointmentsToSort
     */
    public static void sortAppointments(final List<Appointment> appointmentsToSort) {
	appointmentsToSort.sort((appointment1, appointment2) -> {
	    return appointment1.compareTo(appointment2);
	});
    }
}
