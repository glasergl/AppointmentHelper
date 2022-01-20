package ui.dateField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import container.RowOfJComponent;
import date.SimpleDate;
import date.SimpleDates;
import myComponent.button.MyTextButton;
import settings.Colors;

public class AllMonths extends JPanel {

    private final MySimpleDateField toSetSelectedDateOf;
    private final RowOfJComponent top = new RowOfJComponent();
    private final RowOfJComponent bottom = new RowOfJComponent(7, 7);
    private final MyTextButton close = new MyTextButton("x", false);
    private final MyTextButton goLeft = new MyTextButton("<<", false);
    private final MyTextButton goRight = new MyTextButton(">>", false);
    private final MyTextButton goLeftEdge = new MyTextButton("I<<", false);
    private final MyTextButton goRightEdge = new MyTextButton(">>I", false);

    private int currentMonth;
    private MonthField displayOfCurrentMonth;;

    public AllMonths(final MySimpleDateField toSetSelectedDateOf, final SimpleDate initialDate) {
	super(new BorderLayout());
	this.toSetSelectedDateOf = toSetSelectedDateOf;
	this.currentMonth = initialDate.getMonth();
	this.displayOfCurrentMonth = new MonthField(toSetSelectedDateOf, currentMonth);
	setupButtons();
	updateDisplayedMonth();
    }

    public AllMonths(final MySimpleDateField toSetSelectedDateOf) {
	this(toSetSelectedDateOf, SimpleDates.getToday());
    }

    private void setupButtons() {
	setBackground(Colors.getGray(1));
	top.setBackground(Colors.getGray(1));
	bottom.setBackground(Colors.getGray(1));

	top.addToRight(close);
	bottom.addToLeft(goLeftEdge);
	bottom.addToLeft(goLeft);
	bottom.addToRight(goRight);
	bottom.addToRight(goRightEdge);

	bottom.setBackgroundOfChildren(Colors.getGray(1));
	bottom.setBorderOfChildren(new EmptyBorder(2, 2, 2, 2));

	close.setPreferredSize(new Dimension(40, (int) close.getPreferredSize().getHeight()));
	close.setBackground(getBackground());
	close.setBackgroundWhileMouseHovered(new Color(209, 63, 52));
	close.setForegroundWhileMouseHovered(new Color(230, 230, 230));
	goLeft.setBackgroundWhileMouseHovered(Colors.getGray(2));
	goLeftEdge.setBackgroundWhileMouseHovered(Colors.getGray(2));
	goRight.setBackgroundWhileMouseHovered(Colors.getGray(2));
	goRightEdge.setBackgroundWhileMouseHovered(Colors.getGray(2));

	setupActionListeners();

	add(top, BorderLayout.NORTH);
	add(bottom, BorderLayout.CENTER);
    }

    private void setupActionListeners() {
	close.addActionListener((click) -> {
	    toSetSelectedDateOf.setInputVisible(false);
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
	displayOfCurrentMonth = new MonthField(toSetSelectedDateOf, currentMonth);
	add(displayOfCurrentMonth, BorderLayout.SOUTH);
	revalidate();
    }
}
