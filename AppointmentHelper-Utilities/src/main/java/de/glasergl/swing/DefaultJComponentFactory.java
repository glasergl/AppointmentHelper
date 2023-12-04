package de.glasergl.swing;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Factory class which provides the basic JComponents which will be used across
 * each tool.
 * 
 * @author Gabriel Glaser
 */
public class DefaultJComponentFactory {
	private static final Font DEFAULT_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 18);

	public static JTextField getDefaultJTextField() {
		final JTextField jTextField = new JTextField();
		jTextField.setFont(DEFAULT_FONT);
		jTextField.setBorder(new EmptyBorder(3, 3, 3, 3));
		return jTextField;
	}

	public static final JButton getDefaultJButton() {
		final JButton jButton = new JButton();
		jButton.setFont(DEFAULT_FONT);
		jButton.setFocusPainted(false);
		return jButton;
	}

	public static final JLabel getDefaultJLabel() {
		final JLabel jLabel = new JLabel();
		jLabel.setFont(DEFAULT_FONT);
		jLabel.setOpaque(true);
		return jLabel;
	}
}
