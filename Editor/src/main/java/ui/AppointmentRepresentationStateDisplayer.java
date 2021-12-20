package ui;

import java.awt.Dimension;
import myComponent.MyLabel;

/**
 * Label which represents the current state of
 * 
 * @author Gabriel
 * @version 05.12.2021
 */
public class AppointmentRepresentationStateDisplayer extends MyLabel {

    private State current = State.NOTHING;

    public AppointmentRepresentationStateDisplayer() {
	super();
	setPreferredSize(new Dimension(20, 20));
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

    public enum State {
	NOTHING, UNSAVED, ERROR;
    }

}
