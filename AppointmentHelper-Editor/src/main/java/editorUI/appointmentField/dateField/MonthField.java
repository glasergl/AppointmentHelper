package editorUI.appointmentField.dateField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import de.glasergl.standard.swing.myComponent.button.CustomTextButton;
import de.glasergl.standard.swing.settings.Colors;
import simpleDate.SimpleDate;
import simpleDate.SimpleDates;
import swing.CustomizedSwing;

/**
 * Representation of a Month with each respective day.
 *
 * @author Gabriel Glaser
 */
public final class MonthField extends JPanel {
	private static final Color BACKGROUND_COLOR = Color.WHITE;
	private static final Color BACKGROUND_COLOR_OF_DAY = new Color(215, 215, 215);
	private static final int WIDTH_BETWEEN_DAY_LABELS = 2;
	private static final int SIZE_OF_DAY_LABEL = 50;

	private final SimpleDateField toSetSelectedDateOf;
	private final int monthToDepict;
	private final JLabel nameOfMonth;
	private final JPanel days = new JPanel(new GridLayout(5, 7, WIDTH_BETWEEN_DAY_LABELS, WIDTH_BETWEEN_DAY_LABELS));

	public MonthField(final SimpleDateField toSetSelectedDateOf, final int monthToDepict) {
		super(new BorderLayout());
		this.toSetSelectedDateOf = toSetSelectedDateOf;
		this.monthToDepict = monthToDepict;
		this.nameOfMonth = CustomizedSwing.getDefaultJLabel();
		nameOfMonth.setText(getMonthName());
		setup();
	}

	private void setup() {
		days.setBackground(BACKGROUND_COLOR);
		nameOfMonth.setHorizontalAlignment(SwingConstants.CENTER);
		nameOfMonth.setBackground(BACKGROUND_COLOR);
		addDays();
		add(nameOfMonth, BorderLayout.NORTH);
		add(days, BorderLayout.CENTER);
	}

	/**
	 * Adds the number of days of the current month to depict. To fulfill exact 35
	 * added Labels, empty ones are added after the days.
	 */
	private void addDays() {
		final int daysOfMonth = SimpleDates.daysOfMonth(monthToDepict);
		for (int day = 1; day <= SimpleDates.daysOfMonth(monthToDepict); day++) {
			addDayLabel(day);
		}
		for (int filler = daysOfMonth + 1; filler <= 35; filler++) {
			final JLabel empty = new JLabel();
			days.add(empty);
		}
	}

	private void addDayLabel(int day) {
		final SimpleDate date = new SimpleDate(day, monthToDepict);
		final CustomTextButton visualizedDay = new CustomTextButton(String.valueOf(day), BACKGROUND_COLOR_OF_DAY,
				Colors.ofText());
		visualizedDay.setPreferredSize(new Dimension(SIZE_OF_DAY_LABEL, SIZE_OF_DAY_LABEL));
		visualizedDay.addActionListener(click -> {
			toSetSelectedDateOf.setDateInputVisible(false);
			toSetSelectedDateOf.setDate(date);
		});
		days.add(visualizedDay);
	}

	/**
	 * @return German String representation of the month this depicts.
	 */
	public String getMonthName() {
		return SimpleDates.MONTHS.get(monthToDepict - 1);
	}
}
