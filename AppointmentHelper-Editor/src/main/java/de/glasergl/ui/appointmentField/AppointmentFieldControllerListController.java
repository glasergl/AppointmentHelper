package de.glasergl.ui.appointmentField;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import de.glasergl.configuration.ConfigurationHandler;

/**
 * Top-Level Parent for all AppointmentFields which controls the JScrollPane and
 * the header.
 *
 * @author Gabriel Glaser
 */
public class AppointmentFieldControllerListController extends JPanel {

	private final AppointmentFieldControllerList appointmentFields;
	private final JScrollPane jScrollPane;

	public AppointmentFieldControllerListController(final ConfigurationHandler configurationHandler) {
		super();
		appointmentFields = new AppointmentFieldControllerList(configurationHandler);
		jScrollPane = new JScrollPane(appointmentFields, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		setup();
	}

	public void addEmptyAppointmentField() {
		appointmentFields.addEmptyAppointmentField();
		final JScrollBar verticalScrollBar = jScrollPane.getVerticalScrollBar();
		verticalScrollBar.setValue(verticalScrollBar.getMaximum());
		jScrollPane.revalidate();
		jScrollPane.repaint();
	}

	public void storeAll() {
		appointmentFields.storeAll();
	}

	public boolean allRepresentValidAppointment() {
		return appointmentFields.allRepresentValidAppointments();
	}

	public boolean isStored() {
		return appointmentFields.matchesConfiguration();
	}

	public void restoreLastDeleted() {
		appointmentFields.restoreLastDeleted();
	}

	private void setup() {
		setLayout(new BorderLayout());
		setupJScrollPane();
		add(jScrollPane, BorderLayout.CENTER);
	}

	private void setupJScrollPane() {
		jScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		jScrollPane.setPreferredSize(appointmentFields.getPreferredViewSize());
		jScrollPane.getVerticalScrollBar().setUnitIncrement(10);
	}
}
