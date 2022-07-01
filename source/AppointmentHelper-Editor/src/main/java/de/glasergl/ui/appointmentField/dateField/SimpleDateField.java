package de.glasergl.ui.appointmentField.dateField;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.glasergl.simpleDate.SimpleDate;
import de.glasergl.simpleDate.SimpleDates;
import de.glasergl.standard.swing.eventListener.SiblingPopUpDisplayerOnClick;
import de.glasergl.standard.swing.myComponent.MySiblingPopUp;
import de.glasergl.standard.swing.myComponent.button.CustomTextButton;
import de.glasergl.standard.swing.settings.Fonts;

/**
 * Input-Field for a Simple-Date.
 *
 * The component itself shows a date. By clicking on the date, another component
 * shows up under the date. The new component shows all months with days and
 * lets the user decide for a date.
 *
 * @author Gabriel Glaser
 * @version 20.01.2022
 */
public class SimpleDateField extends CustomTextButton {

    private static final float DATE_TEXT_SIZE = 23.0f;
    private static final Dimension PREFERRED_SIZE = new Dimension(70, 40);

    private final SiblingPopUpDisplayerOnClick monthPopUpController;
    private final AllMonthFields monthsInput;
    private final List<ChangeListener> changeListeners = new ArrayList<>();

    private SimpleDate currentlySelectedDate;

    /**
     * @param initialDate which is displayed.
     */
    public SimpleDateField(final SimpleDate initialDate) {
	super(initialDate.toStringWithLeadingZeros(), false);
	this.currentlySelectedDate = initialDate;
	this.monthsInput = new AllMonthFields(this);
	this.monthPopUpController = new SiblingPopUpDisplayerOnClick(monthsInput, this);
	setFont(Fonts.resizedStandard(DATE_TEXT_SIZE));
	setPreferredSize(PREFERRED_SIZE);
    }

    /**
     * Initially displays today.
     */
    public SimpleDateField() {
	this(SimpleDates.getToday());
    }

    /**
     * Adds the given ChangeListener to a List of ChangeListener's.
     *
     * They are called when any date is set on this input field.
     */
    @Override
    public void addChangeListener(final ChangeListener toAdd) {
	changeListeners.add(toAdd);
    }

    /**
     * Sets the date to the given date and displays it. Furthermore, notifies all
     * added ChangeListeners.
     *
     * @param dateToSet
     */
    public void setDate(final SimpleDate dateToSet) {
	currentlySelectedDate = dateToSet;
	setText(dateToSet.toStringWithLeadingZeros());
	final ChangeEvent dateChange = new ChangeEvent(this);
	for (final ChangeListener changeListener : changeListeners) {
	    changeListener.stateChanged(dateChange);
	}
    }

    /**
     * Sets the visibility of the pop up date input according to the argument.
     *
     * @param inputShouldBeVisible
     */
    public void setDateInputVisible(final boolean inputShouldBeVisible) {
	if (monthPopUpController.popUpHasBeenCreated()) {
	    final MySiblingPopUp dateInput = monthPopUpController.getPopUp();
	    dateInput.setVisible(inputShouldBeVisible);
	}
    }

    public SimpleDate getDate() {
	return currentlySelectedDate;
    }

}
