package calendar.cell;

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
public final class SimpleDateDayPanel extends JPanel {

    private final MyLabel visualSimpleDate = new MyLabel();

    public SimpleDateDayPanel(final SimpleDate dateToDepict) {
	super();
	visualSimpleDate.setText(dateToDepict.getDay() + ".");
	setup();
    }

    private void setup() {
	setLayout(new BorderLayout());
	add(visualSimpleDate, BorderLayout.CENTER);
    }

    @Override
    public void setBackground(final Color newBackground) {
	super.setBackground(newBackground);
	if (visualSimpleDate != null) {
	    visualSimpleDate.setBackground(newBackground);
	}
    }

    @Override
    public void setForeground(final Color newForeground) {
	super.setForeground(newForeground);
	if (visualSimpleDate != null) {
	    visualSimpleDate.setForeground(newForeground);
	}
    }

    @Override
    public void setFont(final Font newFont) {
	super.setFont(newFont);
	if (visualSimpleDate != null) {
	    visualSimpleDate.setFont(newFont);
	}
    }

}
