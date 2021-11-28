package ui;

import java.awt.BorderLayout;
import java.awt.Image;
import java.util.List;
import appointment.Appointment;
import standard.MyFrame;
import standard.SwingFunctions;

/**
 * Main Frame for the Editor.
 * 
 * @author Gabriel Glaser
 * @version 28.11.2021
 */
public class EditorFrame extends MyFrame {

	private static final Image ICON = SwingFunctions.getImage("Icon.png", EditorFrame.class);

	private final AllAppointments allAppointments;
	private final Header header;
	private final Footer footer;

	public EditorFrame(final List<Appointment> toRespect) {
		super("TerminEditor", ICON);
		allAppointments = new AllAppointments(toRespect);
		header = new Header(allAppointments);
		footer = new Footer(allAppointments);
		setupComponents();
		start();
	}

	private void setupComponents() {
		add(header, BorderLayout.NORTH);
		add(allAppointments, BorderLayout.CENTER);
		add(footer, BorderLayout.SOUTH);
	}

}
