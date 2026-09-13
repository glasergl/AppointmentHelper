package de.glasergl.appointment.helper.editor.ui;

import de.glasergl.appointment.helper.editor.ui.appointmentField.AppointmentFieldWrapperList;
import de.glasergl.appointment.helper.util.swing.CustomizedSwing;

import javax.swing.*;
import java.awt.*;

/**
 * Header for the whole frame.
 * <p>
 * Contains a button which saves all currently entered appointments and a button
 * to restore the last deleted button.
 *
 * @author glasergl
 */
public class Header extends JPanel {
    private static final int DISTANCE_TO_EDGE = 5;
    private static final Color BACKGROUND = new Color(247, 247, 247);

    private final AppointmentFieldWrapperList appointmentInputFields;
    private final JButton saveButton = CustomizedSwing.getDefaultJButton();

    public Header(final AppointmentFieldWrapperList appointmentFields) {
        super(new FlowLayout(FlowLayout.LEFT));
        this.appointmentInputFields = appointmentFields;
        setup();
    }

    private void setup() {
        setBackground(BACKGROUND);
        setupActionListeners();
        saveButton.setText("Speichern");
        add(saveButton);
    }

    private void setupActionListeners() {
        saveButton.addActionListener(click -> {
            if (appointmentInputFields.allRepresentValidAppointments()) {
                appointmentInputFields.storeAll();
            } else {
                final String title = "Ungültiger Termin";
                final String message = "Mindestens ein Termin ist ungültig.\nBitte geben Sie jedem Termin einen Namen und versuchen es erneut.";
                JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
