package ui;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import appointment.Appointment;
import standard.settings.Colors;
import standard.settings.Fonts;

/**
 * An instance of this class depicts all birthdays.
 * 
 * @author Gabriel Glaser
 * @version 10.9.2021
 */
public final class AppointmentOutput extends JPanel {

	private final AppointmentOutputText today;
	private final AppointmentOutputText tomorrow;

	public AppointmentOutput(final List<Appointment> birthdaysToRespect) {
		super();
		this.today = new AppointmentOutputText("Heute", Appointment::isToday, birthdaysToRespect);
		this.tomorrow = new AppointmentOutputText("Morgen", Appointment::isTomorrow, birthdaysToRespect);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setFont(Fonts.big());
		setBackground(Colors.getGray(0));
		add(today);
		add(tomorrow);
	}

	@Override
	public void setFont(final Font newFontOfOutputTexts) {
		super.setFont(newFontOfOutputTexts);
		if (today != null && tomorrow != null) {
			today.setFont(newFontOfOutputTexts);
			tomorrow.setFont(newFontOfOutputTexts);
		}
	}

	@Override
	public void setBackground(final Color newBackground) {
		super.setBackground(newBackground);
		if (today != null && tomorrow != null) {
			today.setBackground(newBackground);
			tomorrow.setBackground(newBackground);
		}
	}

}
