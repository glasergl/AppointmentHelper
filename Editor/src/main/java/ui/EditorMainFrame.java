package ui;

import java.awt.BorderLayout;
import java.awt.Image;
import java.util.List;
import appointments.Appointment;
import standard.MyFrame;
import standard.SwingFunctions;

public class EditorMainFrame extends MyFrame {

	private static final Image ICON = SwingFunctions.getImage("Icon.png", EditorMainFrame.class);

	private final AllAppointments allAppointments;
	private final Header header;
	private final Footer footer;

	public EditorMainFrame(final List<Appointment> toRespect) {
		super("TerminEditor", ICON);
		this.allAppointments = new AllAppointments(toRespect);
		this.header = new Header(allAppointments);
		this.footer = new Footer(allAppointments);
		setupComponents();
		start();
	}

	private void setupComponents() {
		add(header, BorderLayout.NORTH);
		add(allAppointments, BorderLayout.CENTER);
		add(footer, BorderLayout.SOUTH);
	}

}
