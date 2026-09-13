package de.glasergl.appointment.helper.util.swing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Factory class which provides the basic JComponents which will be used across
 * each tool.
 *
 * @author glasergl
 */
public class CustomizedSwing {
    public static final Font DEFAULT_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 18);

    public static JTextField getDefaultJTextField() {
        final JTextField jTextField = new JTextField();
        jTextField.setFont(DEFAULT_FONT);
        jTextField.setBorder(new EmptyBorder(3, 3, 3, 3));
        return jTextField;
    }

    public static JButton getDefaultJButton() {
        final JButton jButton = new JButton();
        jButton.setFont(DEFAULT_FONT);
        jButton.setFocusPainted(false);
        return jButton;
    }

    public static JLabel getDefaultJLabel() {
        return getDefaultJLabel("");
    }

    public static JLabel getDefaultJLabel(final String labelText) {
        final JLabel jLabel = new JLabel(labelText);
        jLabel.setFont(DEFAULT_FONT);
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        return jLabel;
    }

    public static JCheckBox getDefaultJCheckBox() {
        final JCheckBox jCheckBox = new JCheckBox();
        jCheckBox.setFont(DEFAULT_FONT);
        jCheckBox.setFocusPainted(false);
        jCheckBox.setBackground(null);
        return jCheckBox;
    }
}
