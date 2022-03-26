package calendar;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import standardSwing.myComponent.MyLabel;
import standardSwing.settings.Colors;

public final class MonthRow extends JPanel {

    private static final Color BACKGROUND_COLOR = Colors.getBlue(1);

    private final List<MyLabel> months = getMonths();

    public MonthRow() {
	super();
	setLayout(new GridLayout(1, 12, 1, 1));
	setBackground(BACKGROUND_COLOR);
	addMonths();
    }

    private void addMonths() {
	for (final MyLabel month : months) {
	    add(month);
	}
    }

    private List<MyLabel> getMonths() {
	List<MyLabel> months = new ArrayList<>(12);
	months.add(initializeBackground(new MyLabel("Januar")));
	months.add(initializeBackground(new MyLabel("Februar")));
	months.add(initializeBackground(new MyLabel("März")));
	months.add(initializeBackground(new MyLabel("April")));
	months.add(initializeBackground(new MyLabel("Mai")));
	months.add(initializeBackground(new MyLabel("Juni")));
	months.add(initializeBackground(new MyLabel("Juli")));
	months.add(initializeBackground(new MyLabel("August")));
	months.add(initializeBackground(new MyLabel("September  ")));
	months.add(initializeBackground(new MyLabel("Oktober")));
	months.add(initializeBackground(new MyLabel("November")));
	months.add(initializeBackground(new MyLabel("Dezember")));
	return months;
    }

    private MyLabel initializeBackground(final MyLabel toInitialize) {
	toInitialize.setOpaque(true);
	toInitialize.setBackground(BACKGROUND_COLOR);
	toInitialize.setHorizontalAlignment(SwingConstants.CENTER);
	return toInitialize;
    }
}
