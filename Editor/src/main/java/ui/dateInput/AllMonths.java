package ui.dateInput;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import appointment.SimpleDate;
import complex.RowOfJComponent;
import standard.implementations.MyTextButton;
import standard.settings.Colors;

public class AllMonths extends JPanel {

    private final MySimpleDateField toSetSelectedDateOf;
    private final RowOfJComponent top = new RowOfJComponent();
    private final RowOfJComponent bottom = new RowOfJComponent(7, 7);
    private final MyTextButton close = new MyTextButton("x");
    private final MyTextButton goLeft = new MyTextButton("<<");
    private final MyTextButton goRight = new MyTextButton(">>");
    private final MyTextButton goLeftEdge = new MyTextButton("I<<");
    private final MyTextButton goRightEdge = new MyTextButton(">>I");

    private int currentMonth;
    private MonthRepresentation displayOfCurrentMonth;;

    public AllMonths(final MySimpleDateField toSetSelectedDateOf, final SimpleDate initialDate) {
	super(new BorderLayout());
	this.toSetSelectedDateOf = toSetSelectedDateOf;
	this.currentMonth = initialDate.getMonth();
	this.displayOfCurrentMonth = new MonthRepresentation(toSetSelectedDateOf, currentMonth);
	setupButtons();
	updateDisplayedMonth();
    }

    public AllMonths(final MySimpleDateField toSetSelectedDateOf) {
	this(toSetSelectedDateOf, SimpleDate.getToday());
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
	close.setBackground(Color.RED);

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
	displayOfCurrentMonth = new MonthRepresentation(toSetSelectedDateOf, currentMonth);
	add(displayOfCurrentMonth, BorderLayout.SOUTH);
	revalidate();
    }
}
