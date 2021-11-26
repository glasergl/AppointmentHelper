package main;

import java.io.File;
import javax.swing.SwingUtilities;
import appointments.AppointmentInteracter;
import standard.settings.Colors;
import ui.MainFrame;
import ui.VisualErrors;

/**
 * @author Gabriel Glaser
 * @version 11.9.2021
 */
public final class AppointmentHelper {

    public static final File WITH_APPOINTMENTS = new File("appointments.json");

    public static void main(final String[] args) {
	Colors.setDarkModeEnabled(false);
	if (!WITH_APPOINTMENTS.exists()) {
	    VisualErrors.showCouldntFindAppointmentFileError();
	} else {
	    SwingUtilities.invokeLater(() -> {
		new MainFrame(AppointmentInteracter.getAppointments());
	    });

	}
    }

}
