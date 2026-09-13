package de.glasergl.appointment.helper.editor.ui;

import de.glasergl.appointment.helper.editor.ui.appointmentField.AppointmentFieldWrapperList;
import de.glasergl.appointment.helper.util.swing.CustomizedSwing;

import javax.swing.*;
import java.awt.*;

/**
 * Footer for the whole frame.
 * <p>
 * Contains a button to add an empty appointment.
 *
 * @author glasergl
 */
public class Footer extends JPanel {
    private static final int DISTANCE_TO_EDGE = 5;
    private static final Color BACKGROUND = new Color(247, 247, 247);
    private static final float ADD_BUTTON_PLUS_SIZE = 80.0f;

    private final AppointmentFieldWrapperList appointmentInputFields;
    private final JButton addAppointmentButton = CustomizedSwing.getDefaultJButton();

    public Footer(final AppointmentFieldWrapperList appointmentInputFields) {
        super();
        this.appointmentInputFields = appointmentInputFields;
        setup();
        addAppointmentButton.requestFocus();
    }

    private void setup() {
        setBackground(BACKGROUND);
        setupAddButton();
        add(addAppointmentButton);
    }

    private void setupAddButton() {
        addAppointmentButton.setText("+");
        addAppointmentButton.setFont(addAppointmentButton.getFont().deriveFont(ADD_BUTTON_PLUS_SIZE));
        addAppointmentButton.setPreferredSize(new Dimension(300, 100));
        addAppointmentButton.addActionListener((click) -> appointmentInputFields.addEmptyAppointmentField());
    }

    public void requestFocusForAddButton() {
        addAppointmentButton.requestFocusInWindow();
    }
}
