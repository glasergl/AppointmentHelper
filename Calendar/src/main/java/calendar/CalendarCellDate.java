package calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import simpleDate.SimpleDate;
import standardSwing.myComponent.MyLabel;

/**
 * Visual representation of a SimpleDate.
 * 
 * @author Gabriel Glaser
 * @version 30.03.2022
 */
public final class CalendarCellDate extends JPanel {

    private final MyLabel visualDate = new MyLabel();

    public CalendarCellDate(final SimpleDate toDepict) {
	super();
	visualDate.setText(toDepict.getDay() + ".");
	setLayout(new BorderLayout());
	add(visualDate, BorderLayout.CENTER);
    }

    @Override
    public void setBackground(final Color newBackground) {
	super.setBackground(newBackground);
	if (visualDate != null) {
	    visualDate.setBackground(newBackground);
	}
    }

    @Override
    public void setFont(final Font newFont) {
	super.setFont(newFont);
	if (visualDate != null) {
	    visualDate.setFont(newFont);
	}
    }

}
