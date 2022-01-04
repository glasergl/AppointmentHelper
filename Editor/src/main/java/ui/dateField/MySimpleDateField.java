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

public class MySimpleDateField extends MyTextButton {

    private final SiblingPopUpDisplayerOnClick monthPopUpController;
    private final AllMonths months;
    private final List<ChangeListener> changeListeners = new ArrayList<>();

    private SimpleDate currentlySelected;

    public MySimpleDateField(final SimpleDate initialDate) {
	super(initialDate.toStringWithLeadingZeros(), false);
	this.currentlySelected = initialDate;
	months = new AllMonths(this, initialDate);
	monthPopUpController = new SiblingPopUpDisplayerOnClick(months, this);
	setFont(Fonts.resizedStandard(23.0f));
    }

    public MySimpleDateField() {
	this(SimpleDates.getToday());
    }

    public void addChangeListener(final ChangeListener toAdd) {
	changeListeners.add(toAdd);
    }

    public void setDate(final SimpleDate toSet) {
	currentlySelected = toSet;
	setText(toSet.toStringWithLeadingZeros());
	final ChangeEvent dateChange = new ChangeEvent(this);
	for (final ChangeListener changeListener : changeListeners) {
	    changeListener.stateChanged(dateChange);
	}
    }

    public SimpleDate getDate() {
	return currentlySelected;
    }

    public MySiblingPopUp getMonthPopUp() {
	return monthPopUpController.getPopUp();
    }

}
