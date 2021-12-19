package ui.dateInput;

import appointment.SimpleDate;
import standard.helper.listeners.SiblingPopUpDisplayerOnClick;
import standard.implementations.MySiblingPopUp;
import standard.implementations.MyTextButton;
import standard.settings.Colors;
import standard.settings.Fonts;
import ui.AppointmentRepresentation;

public class MySimpleDateField extends MyTextButton {

    private final SiblingPopUpDisplayerOnClick monthPopUpController;
    private final AllMonths months;
    private final AppointmentRepresentation ofThis;

    private SimpleDate currentlySelected;

    public MySimpleDateField(final SimpleDate initialDate, final AppointmentRepresentation ofThis) {
	super(initialDate.toStringWithLeadingZeros());
	this.ofThis = ofThis;
	this.currentlySelected = initialDate;
	months = new AllMonths(this, initialDate);
	monthPopUpController = new SiblingPopUpDisplayerOnClick(months, this);
	setBackground(Colors.getGray(1));
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
