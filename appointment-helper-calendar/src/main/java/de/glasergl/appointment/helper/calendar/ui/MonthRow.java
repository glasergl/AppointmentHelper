package de.glasergl.appointment.helper.calendar.ui;

import de.glasergl.appointment.helper.util.entity.SimpleDates;
import de.glasergl.appointment.helper.util.swing.CustomizedSwing;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Visual representation of all Months in a horizontal row.
 *
 * @author glasergl
 */
public final class MonthRow extends JPanel {
    private final int horizontalGap = 1;
    private final List<JLabel> monthLabels = getMonthLabels();

    public MonthRow() {
        super();
        setLayout(new GridLayout(1, monthLabels.size(), horizontalGap, 0));
        for (final JLabel monthLabel : monthLabels) {
            add(monthLabel);
        }
    }

    private List<JLabel> getMonthLabels() {
        final List<JLabel> monthLabels = new ArrayList<>();
        for (final String month : SimpleDates.MONTHS) {
            final JLabel monthLabel = CustomizedSwing.getDefaultJLabel();
            monthLabel.setText(month);
            final Font fontForMonthLabel = CustomizedSwing.DEFAULT_FONT.deriveFont(Font.BOLD);
            monthLabel.setFont(fontForMonthLabel);
            monthLabels.add(monthLabel);
        }
        return monthLabels;
    }
}
