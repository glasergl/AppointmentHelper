package ui.dateInput;

import appointment.SimpleDate;
import eventListener.SiblingPopUpDisplayerOnClick;
import myComponent.MySiblingPopUp;
import myComponent.MyTextButton;
import settings.Colors;
import settings.Fonts;
import ui.AppointmentRepresentation;

public class MySimpleDateField extends MyTextButton {

    private final SiblingPopUpDisplayerOnClick monthPopUpController;
    private final AllMonths months;
    private final AppointmentRepresentation ofThis;

    private SimpleDate currentlySelected;

    public MySimpleDateField(final SimpleDate initialDate, final AppointmentRepresentation ofThis) {
	super(initialDate.toStringWithLeadingZeros(), false);
	this.ofThis = ofThis;
	this.currentlySelected = initialDate;
	months = new AllMonths(this, initialDate);
	monthPopUpController = new SiblingPopUpDisplayerOnClick(months, this);
	setBackground(Colors.getGray(1));
	setBackgroundWhileMouseHovered(Colors.getGray(2));
	setFont(Fonts.resizedStandard(23.0f));
    }

    public MySimpleDateField(final AppointmentRepresentation ofThis) {
	this(SimpleDate.getToday(), ofThis);
    }

    public void setDate(final SimpleDate toSet) {
	currentlySelected = toSet;
	setText(toSet.toStringWithLeadingZeros());
	ofThis.updateSavedState();
    }

    public SimpleDate getDate() {
	return currentlySelected;
    }

    public MySiblingPopUp getMonthPopUp() {
	return monthPopUpController.getPopUp();
    }

}
