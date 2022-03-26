package appointmentOutput;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.JPanel;
import appointment.Appointment;
import standardSwing.settings.Colors;
import standardSwing.settings.Fonts;

/**
 * Class which shows the appointments of today and tomorrow.
 * 
 * @author Gabriel Glaser
 * @version 6.1.2021
 */
public final class AppointmentOutput extends JPanel {

    private final AppointmentOutputText outputOfToday;
    private final AppointmentOutputText outputOfTomorrow;

    public AppointmentOutput(final List<Appointment> appointmentsToConsider) {
	super();
	this.outputOfToday = new AppointmentOutputText("Heute", Appointment::isToday, appointmentsToConsider);
	this.outputOfTomorrow = new AppointmentOutputText("Morgen", Appointment::isTomorrow, appointmentsToConsider);
	setLayout(new BorderLayout());
	setFont(Fonts.big());
	setBackground(Colors.getGray(0));
	add(outputOfToday, BorderLayout.NORTH);
	add(outputOfTomorrow, BorderLayout.SOUTH);
    }

    @Override
    public void setFont(final Font newFontOfOutputTexts) {
	super.setFont(newFontOfOutputTexts);
	if (outputOfToday != null && outputOfTomorrow != null) {
	    outputOfToday.setFont(newFontOfOutputTexts);
	    outputOfTomorrow.setFont(newFontOfOutputTexts);
	}
    }

    @Override
    public void setBackground(final Color newBackground) {
	super.setBackground(newBackground);
	if (outputOfToday != null && outputOfTomorrow != null) {
	    outputOfToday.setBackground(newBackground);
	    outputOfTomorrow.setBackground(newBackground);
	}
    }

}
