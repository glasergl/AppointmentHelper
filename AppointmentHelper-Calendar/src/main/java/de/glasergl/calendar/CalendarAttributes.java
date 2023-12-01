package de.glasergl.calendar;

import java.awt.Color;
import java.awt.Font;
import de.glasergl.standard.swing.settings.Colors;
import de.glasergl.standard.swing.settings.Fonts;

/**
 * Class which contains the attributes for the Calendar.
 *
 * @author Gabriel Glaser
 */
public final class CalendarAttributes {

	public static final Color CALENDAR_BACKGROUND = Colors.getBlue(1);
	public static final Color CALENDAR_FOREGROUND = Colors.ofText();
	public static final Color CELL_BACKGROUND_COLOR = Colors.getBlue(2);
	public static final Color BACKGROUND_COLOR_OF_POP_UP = Colors.getBlue(0);
	public static final Color BACKGROUND_COLOR_OF_TODAY = Colors.getBlue(3);

	public static final int MARGIN_BETWEEN_CELLS = 1;
	public static final int MARGIN_BETWEEN_CELL_COMPONENTS = 5;
	public static final int MAXIMUM_PIXEL_LENGTH_OF_NAME = 110;

	public static final Font FONT = Fonts.mediumSmall();

}
