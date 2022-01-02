package ui.appointmentInput;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;

import myComponent.MyLabel;

/**
 * MyLabel which represents the current state of an AppointmentField.
 * 
 * @author Gabriel
 * @version 1.1.2022
 */
public class AppointmentFieldState extends MyLabel {

    private State current = State.NOTHING;

    public AppointmentFieldState() {
	super();
	setPreferredSize(new Dimension(20, 20));
	setHorizontalAlignment(CENTER);
    }

    public void toNothing() {
	changeState(State.NOTHING);
    }

    public void toUnsaved() {
	changeState(State.UNSAVED);
    }

    public void toError() {
	changeState(State.ERROR);
    }

    public State getState() {
	return current;
    }

    protected void paintComponent(final Graphics context) {
	final Container parent = getParent();
	setBackground(parent.getBackground());
	super.paintComponent(context);
    }

    private void changeState(final State newState) {
	current = newState;
	displayCurrentState();
    }

    private void displayCurrentState() {
	if (current == State.NOTHING) {
	    setText("");
	} else if (current == State.UNSAVED) {
	    setText("*");
	} else {
	    setText("!");
	}
    }

    public static enum State {
	NOTHING, UNSAVED, ERROR;
    }

}
