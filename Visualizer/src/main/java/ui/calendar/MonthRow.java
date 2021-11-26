package ui.calendar;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import standard.implementations.MyLabel;
import standard.settings.Colors;

public final class MonthRow extends JPanel {

	private final List<MyLabel> months = getMonths();

	public MonthRow() {
		super();
		setLayout(new GridLayout(1, 12, 1, 1));
		setBackground(Colors.getBackground(2));
		addMonths();
	}

	private void addMonths() {
		for (final MyLabel month : months) {
			add(month);
		}
	}

	private List<MyLabel> getMonths() {
		List<MyLabel> months = new ArrayList<>(12);
		months.add(new MyLabel("Januar"));
		months.add(new MyLabel("Februar"));
		months.add(new MyLabel("März"));
		months.add(new MyLabel("April"));
		months.add(new MyLabel("Mai"));
		months.add(new MyLabel("Juni"));
		months.add(new MyLabel("Juli"));
		months.add(new MyLabel("August"));
		months.add(new MyLabel("September  "));
		months.add(new MyLabel("Oktober"));
		months.add(new MyLabel("November"));
		months.add(new MyLabel("Dezember"));
		return months;
	}
}
