package ui.dateField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import container.RowOfJComponent;
import myComponent.button.MyTextButton;
import settings.Colors;

/**
 * 
 * 
 * @author Gabriel Glaser
 * @version 20.01.2022
 */
public class AllMonths extends JPanel {

    private static final Color BACKGROUND_COLOR = Colors.getGray(1);
    private static final Color MONTH_CONTROL_BUTTON_COLOR = Colors.getGray(2);
    private static final int WIDTH_OF_CLOSE_BUTTON = 40;
    private static final int DISTANCE_BETWEEN_MONTH_CONTROL_BUTTONS = 4;

    private final MySimpleDateField dateField;

    private final RowOfJComponent top = new RowOfJComponent();
    private final RowOfJComponent middle = new RowOfJComponent(7, 7);

    private final MyTextButton closeButton = new MyTextButton("x", false);
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
	top.setBackground(BACKGROUND_COLOR);
	middle.setBackground(BACKGROUND_COLOR);

	top.addToRight(closeButton);
	middle.addToLeft(goLeftEdgeButton);
	middle.addToLeft(goLeftButton);
	middle.addToRight(goRightButton);
	middle.addToRight(goRightEdgeButton);

	middle.setBackgroundOfChildren(BACKGROUND_COLOR);
	middle.setBorderOfChildren(new EmptyBorder(DISTANCE_BETWEEN_MONTH_CONTROL_BUTTONS / 2, DISTANCE_BETWEEN_MONTH_CONTROL_BUTTONS / 2, DISTANCE_BETWEEN_MONTH_CONTROL_BUTTONS / 2,
		DISTANCE_BETWEEN_MONTH_CONTROL_BUTTONS / 2));

	closeButton.setPreferredSize(new Dimension(WIDTH_OF_CLOSE_BUTTON, (int) closeButton.getPreferredSize().getHeight()));
	closeButton.setBackground(BACKGROUND_COLOR);
	closeButton.setBackgroundWhileMouseHovered(new Color(209, 63, 52));
	closeButton.setForegroundWhileMouseHovered(new Color(230, 230, 230));
	goLeftButton.setBackgroundWhileMouseHovered(MONTH_CONTROL_BUTTON_COLOR);
	goLeftEdgeButton.setBackgroundWhileMouseHovered(MONTH_CONTROL_BUTTON_COLOR);
	goRightButton.setBackgroundWhileMouseHovered(MONTH_CONTROL_BUTTON_COLOR);
	goRightEdgeButton.setBackgroundWhileMouseHovered(MONTH_CONTROL_BUTTON_COLOR);

	setupActionListeners();

	add(top, BorderLayout.NORTH);
	add(middle, BorderLayout.CENTER);
    }

    private void setupActionListeners() {
	closeButton.addActionListener((click) -> {
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

    private void setDisplayedMonth(final int newMonth) {
	currentMonth = newMonth;
	remove(displayOfCurrentMonth);
	displayOfCurrentMonth = new MonthField(dateField, newMonth);
	add(displayOfCurrentMonth, BorderLayout.SOUTH);
	revalidate();
    }

}
