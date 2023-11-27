package de.glasergl.ui.appointmentField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import de.glasergl.configuration.ConfigurationHandler;
import de.glasergl.standard.swing.myComponent.MyLabel;
import de.glasergl.standard.swing.settings.Fonts;

/**
 * Top-Level Parent for all AppointmentFields which controls the JScrollPane and
 * the header.
 *
 * @author Gabriel Glaser
 * @version 13.3.2022
 */
public class AppointmentFieldControllerListController extends JPanel {

    private final AppointmentFieldControllerList appointmentFields;
    private final ColumnDescription columnDescription = new ColumnDescription();
    private final JScrollPane jScrollPane;

    public AppointmentFieldControllerListController(final ConfigurationHandler configurationHandler) {
	super();
	appointmentFields = new AppointmentFieldControllerList(configurationHandler);
	jScrollPane = new JScrollPane(appointmentFields, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	setup();
    }

    public void addEmptyAppointmentField() {
//	jScrollPane.validate();
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
	add(columnDescription, BorderLayout.NORTH);
	add(jScrollPane, BorderLayout.CENTER);
    }

    private void setupJScrollPane() {
	jScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
	jScrollPane.setPreferredSize(appointmentFields.getPreferredViewSize());
	jScrollPane.getVerticalScrollBar().setUnitIncrement(10);
    }

    private final class ColumnDescription extends JPanel {
	private final MyLabel date = new MyLabel("Datum");
	private final MyLabel name = new MyLabel("Name");
	private final MyLabel description = new MyLabel("Beschreibung");

	public ColumnDescription() {
	    super(new FlowLayout(FlowLayout.LEFT, 0, 0));
	    setBackground(new Color(255,255,255));
	    date.setOpaque(false);
	    name.setOpaque(false);
	    description.setOpaque(false);
	    date.setFont(Fonts.resizedStandard(16.0f));
	    name.setFont(Fonts.resizedStandard(16.0f));
	    description.setFont(Fonts.resizedStandard(16.0f));
	    date.setBorder(new EmptyBorder(0, 37, 0, 0));
	    name.setBorder(new EmptyBorder(0, 27, 0, 0));
	    description.setBorder(new EmptyBorder(0, 249, 0, 0));
	    add(date);
	    add(name);
	    add(description);
	}
    }

}
