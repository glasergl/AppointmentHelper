package ui.dateField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import standardSwing.container.RowOfJComponent;
import standardSwing.myComponent.button.CustomTextButton;
import standardSwing.settings.Colors;

/**
 * This JPanel visualizes all months as individual MonthField's and allows
 * controlling them with Buttons.
 * 
 * @author Gabriel Glaser
 * @version 13.03.2022
 */
public class AllMonths extends JPanel {

    private static final Color BACKGROUND_COLOR = Colors.getBlue(1);
    private static final int WIDTH_OF_CLOSE_BUTTON = 40;
    private static final int DISTANCE_BETWEEN_MONTH_CONTROL_BUTTONS = 4;
    private static final Dimension SIZE_OF_MONTH_CONTROL_BUTTONS = new Dimension(35, 35);

    private final MySimpleDateField dateField;

    private final RowOfJComponent topButtons = new RowOfJComponent();
    private final RowOfJComponent middleButtons = new RowOfJComponent(7, 7);

    private final CustomTextButton closeButton = new CustomTextButton("x", BACKGROUND_COLOR, Color.BLACK);
    private final CustomTextButton goLeftButton = new CustomTextButton("<<", BACKGROUND_COLOR, Color.BLACK);
    private final CustomTextButton goRightButton = new CustomTextButton(">>", BACKGROUND_COLOR, Color.BLACK);
    private final CustomTextButton goLeftEdgeButton = new CustomTextButton("I<<", BACKGROUND_COLOR, Color.BLACK);
    private final CustomTextButton goRightEdgeButton = new CustomTextButton(">>I", BACKGROUND_COLOR, Color.BLACK);

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

	closeButton.setBackgroundWhileHovered(new Color(209, 63, 52));
	closeButton.setForegroundWhileHovered(Color.WHITE);
	middleButtons.setBorderOfChildren(new EmptyBorder(DISTANCE_BETWEEN_MONTH_CONTROL_BUTTONS / 2, DISTANCE_BETWEEN_MONTH_CONTROL_BUTTONS / 2, DISTANCE_BETWEEN_MONTH_CONTROL_BUTTONS / 2,
		DISTANCE_BETWEEN_MONTH_CONTROL_BUTTONS / 2));

	closeButton.setPreferredSize(new Dimension(WIDTH_OF_CLOSE_BUTTON, (int) closeButton.getPreferredSize().getHeight()));
	closeButton.setBackground(BACKGROUND_COLOR);

	goLeftButton.setPreferredSize(SIZE_OF_MONTH_CONTROL_BUTTONS);
	goRightButton.setPreferredSize(SIZE_OF_MONTH_CONTROL_BUTTONS);
	goLeftEdgeButton.setPreferredSize(SIZE_OF_MONTH_CONTROL_BUTTONS);
	goRightEdgeButton.setPreferredSize(SIZE_OF_MONTH_CONTROL_BUTTONS);

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
