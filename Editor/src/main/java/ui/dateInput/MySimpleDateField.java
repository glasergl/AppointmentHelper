package ui.dateInput;

import appointment.SimpleDate;
import complex.PointingBorder;
import standard.helper.PopUpOnHoverController;
import standard.helper.enums.StartMouseEvent;
import standard.implementations.MyTextButton;
import standard.settings.Colors;
import standard.settings.Fonts;
import ui.AppointmentRepresentation;
import standard.implementations.MySimplePopUp;

public class MySimpleDateField extends MyTextButton {

	private final MySimplePopUp monthPopUp;
	private final AllMonths months;
	private final AppointmentRepresentation ofThis;

	private SimpleDate currentlySelected;

	public MySimpleDateField(final SimpleDate initialDate, final AppointmentRepresentation ofThis) {
		super(initialDate.toStringWithLeadingZeros());
		this.ofThis = ofThis;
		this.currentlySelected = initialDate;
		months = new AllMonths(this, initialDate);
		monthPopUp = new MySimplePopUp(months, this);
		setBackground(Colors.getGray(1));
		setFont(Fonts.resizedStandard(23.0f));
		monthPopUp.setPopUpComponent(months);
		monthPopUp.setBorderOfSubComponent(new PointingBorder(Colors.getGray(1), Colors.ofText()));
		addMouseListener(new PopUpOnHoverController(monthPopUp, StartMouseEvent.CLICK));
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

	public MySimplePopUp getMonthPopUp() {
		return monthPopUp;
	}

}
