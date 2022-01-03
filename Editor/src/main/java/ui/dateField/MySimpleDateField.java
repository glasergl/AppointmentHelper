package ui.dateField;

import date.SimpleDate;
import eventListener.SiblingPopUpDisplayerOnClick;
import myComponent.MySiblingPopUp;
import myComponent.button.MyTextButton;
import settings.Fonts;
import date.SimpleDates;

public class MySimpleDateField extends MyTextButton {

    private final SiblingPopUpDisplayerOnClick monthPopUpController;
    private final AllMonths months;

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

    public void setDate(final SimpleDate toSet) {
	currentlySelected = toSet;
	setText(toSet.toStringWithLeadingZeros());
    }

    public SimpleDate getDate() {
	return currentlySelected;
    }

    public MySiblingPopUp getMonthPopUp() {
	return monthPopUpController.getPopUp();
    }

}
