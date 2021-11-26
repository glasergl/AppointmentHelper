package ui.dateInput;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import appointment.SimpleDate;
import complex.LineOfJComponent;
import standard.implementations.MySimpleButton;
import standard.settings.Colors;

public class AllMonths extends JPanel {

	private final MySimpleDateField toSetSelectedDateOf;
	private final LineOfJComponent top = new LineOfJComponent();
	private final LineOfJComponent bottom = new LineOfJComponent(7, 7);
	private final MySimpleButton close = new MySimpleButton("x");
	private final MySimpleButton goLeft = new MySimpleButton("<<");
	private final MySimpleButton goRight = new MySimpleButton(">>");
	private final MySimpleButton goLeftEdge = new MySimpleButton("I<<");
	private final MySimpleButton goRightEdge = new MySimpleButton(">>I");

	private int currentMonth;
	private MonthRepresentation displayOfCurrentMonth;;

	public AllMonths(final MySimpleDateField toSetSelectedDateOf, final SimpleDate initialDate) {
		super(new BorderLayout());
		this.toSetSelectedDateOf = toSetSelectedDateOf;
		this.currentMonth = initialDate.getMonth();
		this.displayOfCurrentMonth = new MonthRepresentation(toSetSelectedDateOf.getMonthPopUp(), toSetSelectedDateOf,
				currentMonth);
		setupButtons();
		updateDisplayedMonth();
	}

	public AllMonths(final MySimpleDateField toSetSelectedDateOf) {
		this(toSetSelectedDateOf, SimpleDate.getToday());
	}

	private void setupButtons() {
		top.setBackground(Colors.getBackground(2));
		bottom.setBackground(Colors.getBackground(2));
		top.addToRight(close);
		bottom.addToLeft(goLeftEdge);
		bottom.addToLeft(goLeft);
		bottom.addToRight(goRight);
		bottom.addToRight(goRightEdge);

		bottom.setBackgroundForSubComponents(Colors.getBackground(2));
		bottom.setBorderForSubComponents(new EmptyBorder(2, 2, 2, 2));

		close.setPreferredSize(new Dimension(40, (int) close.getPreferredSize().getHeight()));
		close.setBackground(Colors.getBackground(2));

		setupActionListeners();
		add(top, BorderLayout.NORTH);
		add(bottom, BorderLayout.CENTER);
	}

	private void setupActionListeners() {
		close.addActionListener((click) -> {
			toSetSelectedDateOf.getMonthPopUp().setVisible(false);
		});
		goLeft.addActionListener((click) -> {
			currentMonth = currentMonth == 1 ? 12 : currentMonth - 1;
			updateDisplayedMonth();
		});
		goRight.addActionListener((click) -> {
			currentMonth = currentMonth == 12 ? 1 : currentMonth + 1;
			updateDisplayedMonth();
		});
		goLeftEdge.addActionListener((click) -> {
			currentMonth = 1;
			updateDisplayedMonth();
		});
		goRightEdge.addActionListener((click) -> {
			currentMonth = 12;
			updateDisplayedMonth();
		});
	}

	private void updateDisplayedMonth() {
		remove(displayOfCurrentMonth);
		displayOfCurrentMonth = new MonthRepresentation(toSetSelectedDateOf.getMonthPopUp(), toSetSelectedDateOf,
				currentMonth);
		add(displayOfCurrentMonth, BorderLayout.SOUTH);
		revalidate();
	}
}
