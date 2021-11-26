package ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.swing.JPanel;
import appointments.Appointment;
import complex.PointingBorder;
import standard.helper.PopUpOnHoverController;
import standard.helper.BackgroundChangerOnHover;
import standard.implementations.MyLabel;
import standard.implementations.MySimplePopUp;
import standard.settings.Colors;

public final class AppointmentOutputText extends JPanel {

    private final String timeDescription;
    private final List<Appointment> birthdaysAtTimeDescription;
    private final List<Appointment> nonBirthdaysAtTimeDescription;
    private final List<MyLabel> allLabels = new ArrayList<>();
    private final List<MyLabel> birthdayLabels;
    private final List<MyLabel> nonBirthdayLabels;

    public AppointmentOutputText(final String timeDescription, final Predicate<Appointment> timeCondition, final List<Appointment> toRespect) {
	this.timeDescription = timeDescription;
	this.birthdaysAtTimeDescription = toRespect.stream().filter(timeCondition).filter(a -> a.isABirthday()).collect(Collectors.toList());
	this.nonBirthdaysAtTimeDescription = toRespect.stream().filter(timeCondition).filter(a -> !a.isABirthday()).collect(Collectors.toList());
	this.birthdayLabels = getNameLabels(birthdaysAtTimeDescription);
	this.nonBirthdayLabels = getNameLabels(nonBirthdaysAtTimeDescription);
	setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
	setup();
    }

    private void setup() {
	if (birthdaysAtTimeDescription.size() > 0 || nonBirthdaysAtTimeDescription.size() > 0) {
	    allLabels.add(new MyLabel(timeDescription + " "));
	}
	if (birthdaysAtTimeDescription.size() > 0) {
	    addBirthdayLabels();
	}
	if (nonBirthdaysAtTimeDescription.size() > 0) {
	    addTransition();
	    addNonBirthdayLabels();
	}
	for (final MyLabel toAdd : allLabels) {
	    add(toAdd);
	}
    }

    private void addBirthdayLabels() {
	allLabels.add(new MyLabel((birthdaysAtTimeDescription.size() > 1 ? "haben" : "hat") + " "));
	allLabels.addAll(enumerateLabels(birthdayLabels));
	allLabels.add(new MyLabel(" " + "Geburtstag"));
	if (nonBirthdaysAtTimeDescription.size() == 0) {
	    allLabels.add(new MyLabel("!"));
	} else {
	    allLabels.add(new MyLabel(" "));
	}
    }

    private void addTransition() {
	if (birthdaysAtTimeDescription.size() > 0) {
	    allLabels.add(new MyLabel("und es "));
	}
	if (nonBirthdaysAtTimeDescription.size() == 1) {
	    allLabels.add(new MyLabel("ist "));
	} else if (nonBirthdaysAtTimeDescription.size() >= 1) {
	    allLabels.add(new MyLabel("sind "));
	}

    }

    private void addNonBirthdayLabels() {
	allLabels.addAll(enumerateLabels(nonBirthdayLabels));
	if (nonBirthdaysAtTimeDescription.size() > 0) {
	    allLabels.add(new MyLabel("."));
	}
    }

    private List<MyLabel> getNameLabels(final List<Appointment> appointments) {
	final List<MyLabel> labels = new ArrayList<>();
	for (final Appointment appointment : appointments) {
	    final MyLabel withAppointmentName = new MyLabel(appointment.getName());
	    setupAppointmentLabel(withAppointmentName, appointment);
	    labels.add(withAppointmentName);
	}
	return labels;
    }

    private void setupAppointmentLabel(final MyLabel appointmentName, final Appointment appointment) {
	appointmentName.addMouseListener(new BackgroundChangerOnHover(Colors.ofFocus()));
	final String description = appointment.getDescription();
	final MyLabel withDescription = new MyLabel(description);
	withDescription.setBorder(new PointingBorder(withDescription.getBackground(), Colors.ofText()));
	if (!description.equals("")) {
	    appointmentName.addMouseListener(new PopUpOnHoverController(new MySimplePopUp(withDescription, this)));
	}
    }

    private List<MyLabel> enumerateLabels(final List<MyLabel> toEnumerate) {
	final List<MyLabel> enumerated = new ArrayList<>();
	for (int i = 0; i < toEnumerate.size(); i++) {
	    enumerated.add(toEnumerate.get(i));
	    if (i < toEnumerate.size() - 1) {
		if (i < toEnumerate.size() - 2) {
		    enumerated.add(new MyLabel(", "));
		} else {
		    enumerated.add(new MyLabel(" und "));
		}
	    }
	}
	return enumerated;
    }

    @Override
    public void setFont(final Font newFont) {
	super.setFont(newFont);
	if (allLabels != null) {
	    for (final MyLabel label : allLabels) {
		label.setFont(newFont);
	    }
	}
    }

    @Override
    public void setBackground(final Color newBackground) {
	if (allLabels != null) {
	    for (final MyLabel label : allLabels) {
		label.setBackground(newBackground);
	    }
	}
    }

    @Override
    public String toString() {
	final StringBuilder wholeText = new StringBuilder();
	for (final MyLabel toAppendText : allLabels) {
	    wholeText.append(toAppendText.getText());
	}
	return wholeText.toString();
    }

}
