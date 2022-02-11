package ui.dateField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import standardSwing.container.RowOfJComponent;
import standardSwing.myComponent.button.MyTextButton;
import standardSwing.settings.Colors;

/**
 * 
 * 
 * @author Gabriel Glaser
 * @version 20.01.2022
 */
public class AllMonths extends JPanel {

    private static final Color BACKGROUND_COLOR = Colors.getGray(1);
    private static final int WIDTH_OF_CLOSE_BUTTON = 40;
    private static final int DISTANCE_BETWEEN_MONTH_CONTROL_BUTTONS = 4;

    private final MySimpleDateField dateField;

    private final RowOfJComponent topButtons = new RowOfJComponent();
    private final RowOfJComponent middleButtons = new RowOfJComponent(7, 7);

    private final MyTextButton closeButton = new MyTextButton("x", false, new Color(209, 63, 52), Color.WHITE);
    private final MyTextButton goLeftButton = new MyTextButton("<<", false);
    private final MyTextButton goRightButton = new MyTextButton(">>", false);
    private final MyTextButton goLeftEdgeButton = new MyTextButton("I<<", false);
    private final MyTextButton goRightEdgeButton = new MyTextButton(">>I", false);

    private int currentMonth;
    private MonthField displayOfCurrentMonth;;

    public AllMonths(final MySimpleDateField dateField) {
	super(new BorderLayout());
	this.dateField = dateField;
	this.currentMonth = dateField.getDate().getMonth();
	this.displayOfCurrentMonth = new MonthField(dateField, currentMonth);
	setup();
	setDisplayedMonth(currentMonth);
    }

    private void setup() {
	setBackground(BACKGROUND_COLOR);
	topButtons.setBackground(BACKGROUND_COLOR);
	middleButtons.setBackground(BACKGROUND_COLOR);

	topButtons.addToRight(closeButton);
	middleButtons.addToLeft(goLeftEdgeButton);
	middleButtons.addToLeft(goLeftButton);
	middleButtons.addToRight(goRightButton);
	middleButtons.addToRight(goRightEdgeButton);

	middleButtons.forEachChildDo(button -> {
	    ((MyTextButton) button).updateBackground(BACKGROUND_COLOR);
	});
	middleButtons.setBorderOfChildren(new EmptyBorder(DISTANCE_BETWEEN_MONTH_CONTROL_BUTTONS / 2, DISTANCE_BETWEEN_MONTH_CONTROL_BUTTONS / 2, DISTANCE_BETWEEN_MONTH_CONTROL_BUTTONS / 2,
		DISTANCE_BETWEEN_MONTH_CONTROL_BUTTONS / 2));

	closeButton.setPreferredSize(new Dimension(WIDTH_OF_CLOSE_BUTTON, (int) closeButton.getPreferredSize().getHeight()));
	closeButton.setBackground(BACKGROUND_COLOR);

	setupActionListeners();

	add(topButtons, BorderLayout.NORTH);
	add(middleButtons, BorderLayout.CENTER);
    }

    private void setupActionListeners() {
	closeButton.addActionListener(click -> {
	    dateField.setDateInputVisible(false);
	});
	goLeftButton.addActionListener(click -> {
	    setDisplayedMonth(currentMonth == 1 ? 12 : currentMonth - 1);
	});
	goRightButton.addActionListener(click -> {
	    setDisplayedMonth(currentMonth == 12 ? 1 : currentMonth + 1);
	});
	goLeftEdgeButton.addActionListener(click -> {
	    setDisplayedMonth(1);
	});
	goRightEdgeButton.addActionListener(click -> {
	    setDisplayedMonth(12);
	});
    }

    /**
     * Sets the given month as the current month and shows the month.
     * 
     * @param newMonth
     */
    private void setDisplayedMonth(final int newMonth) {
	currentMonth = newMonth;
	remove(displayOfCurrentMonth);
	displayOfCurrentMonth = new MonthField(dateField, newMonth);
	add(displayOfCurrentMonth, BorderLayout.SOUTH);
	revalidate();
    }

}
