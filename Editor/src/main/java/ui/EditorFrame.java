package ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.util.List;
import appointment.Appointment;
import standard.MyFrame;
import standard.SwingFunctions;

public class EditorFrame extends MyFrame {

	private static final Image ICON = SwingFunctions.getImage("Icon.png", EditorFrame.class);

	private final AllAppointments appointmentsDisplay;
	private final Header header;
	private final Footer footer;

	public EditorFrame(final List<Appointment> initial) {
		super("TerminEditor", ICON);
		appointmentsDisplay = new AllAppointments(initial);
		header = new Header(appointmentsDisplay);
		footer = new Footer(appointmentsDisplay);
		addComponents();
		start();
	}

	private void addComponents() {
		final Container contentPane = getContentPane();
		contentPane.add(header, BorderLayout.NORTH);
		contentPane.add(appointmentsDisplay, BorderLayout.CENTER);
		contentPane.add(footer, BorderLayout.SOUTH);
	}

}
