package ui.dateField;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import date.SimpleDate;
import eventListener.SiblingPopUpDisplayerOnClick;
import myComponent.MySiblingPopUp;
import myComponent.button.MyTextButton;
import settings.Fonts;
import date.SimpleDates;

/**
 * Input-Field for a Simple-Date.
 * 
 * The component itself shows a date. By clicking on the date, another component
 * shows up under the date. The new component shows all month and lets the user
 * decide for a date.
 * 
 * @author Gabriel Glaser
 * @version 20.01.2022
 */
public class MySimpleDateField extends MyTextButton {

    private final SiblingPopUpDisplayerOnClick monthPopUpController;
    private final AllMonths months;
    private final List<ChangeListener> changeListeners = new ArrayList<>();

    private SimpleDate currentlySelected;

    /**
     * @param initialDate which is displayed.
     */
    public MySimpleDateField(final SimpleDate initialDate) {
	super(initialDate.toStringWithLeadingZeros(), false);
	this.currentlySelected = initialDate;
	months = new AllMonths(this, initialDate);
	monthPopUpController = new SiblingPopUpDisplayerOnClick(months, this);
	setFont(Fonts.resizedStandard(23.0f));
    }

    /**
     * Initially displays today.
     */
    public MySimpleDateField() {
	this(SimpleDates.getToday());
    }

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
	currentlySelected = dateToSet;
	setText(dateToSet.toStringWithLeadingZeros());
	final ChangeEvent dateChange = new ChangeEvent(this);
	for (final ChangeListener changeListener : changeListeners) {
	    changeListener.stateChanged(dateChange);
	}
    }

    public void setInputVisible(final boolean inputShouldBeVisible) {
	final MySiblingPopUp dateInput = monthPopUpController.getPopUp();
	dateInput.setVisible(inputShouldBeVisible);
    }

    public SimpleDate getDate() {
	return currentlySelected;
    }

}
