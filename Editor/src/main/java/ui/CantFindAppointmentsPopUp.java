package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import standard.implementations.MyTextButton;
import standard.implementations.MyLabel;
import standard.settings.Fonts;

public class CantFindAppointmentsPopUp extends JPanel {

    private final JPanel container = new JPanel(new BorderLayout());
    private final String message = "<html><body>\"appointments.json\" konnte nicht gefunden werden.<br>Sie können eine neue, leere Datei erstellen lassen oder das Programm beenden.<br>(Die Datei landet im selben Verzeichnis, wie der TerminEditor)</body></html>";
    private final MyLabel displayOfMessage = new MyLabel(message);
    private final MyTextButton yes = new MyTextButton("Ja und das Programm starten");
    private final MyTextButton no = new MyTextButton("Nein und das Programm beenden");

    public CantFindAppointmentsPopUp() {
	super();
	setupComponents();
    }

    private void setupComponents() {
	final JPanel buttons = new JPanel(new BorderLayout(40, 0));
	buttons.add(yes, BorderLayout.WEST);
	buttons.add(no, BorderLayout.EAST);
	final TitledBorder titledBorder = new TitledBorder("Neue, leere \"appointments.json\" erstellen?");
	titledBorder.setTitleFont(Fonts.standard());
	buttons.setBorder(titledBorder);
	setBackground(new Color(50, 50, 50, 50));
	final JPanel fillerOfButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
	fillerOfButtons.add(buttons);
	container.add(displayOfMessage, BorderLayout.NORTH);
	container.add(fillerOfButtons, BorderLayout.CENTER);
	container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	add(container);
    }

}
