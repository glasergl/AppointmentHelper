package ui;

import static org.junit.Assert.*;
import org.junit.Test;
import appointment.Appointment;
import appointment.SimpleDate;

public class TestAppointmentRepresentationMessage {

	static AllAppointments allAppointments = new AllAppointments();
	static Appointment testAppointment = new Appointment(new SimpleDate(10, 10), "Frodo", false);

	static AppointmentRepresentationStateDisplayer.State nothing = AppointmentRepresentationStateDisplayer.State.NOTHING;
	static AppointmentRepresentationStateDisplayer.State unsaved = AppointmentRepresentationStateDisplayer.State.UNSAVED;
	static AppointmentRepresentationStateDisplayer.State error = AppointmentRepresentationStateDisplayer.State.ERROR;

	@Test
	public void testInitialMessage() {
		AppointmentRepresentation testWithoutInitialAppointment = new AppointmentRepresentation(allAppointments);
		assertEquals(unsaved, testWithoutInitialAppointment.getState());
		AppointmentRepresentation testWithInitialAppointment = new AppointmentRepresentation(allAppointments,
				testAppointment);
		assertEquals(nothing, testWithInitialAppointment.getState());
	}

	@Test
	public void testErrorMessageOnEmptyAppointmentRepresentation() {
		AppointmentRepresentation testWithoutInitialAppointment = new AppointmentRepresentation(allAppointments);
		testWithoutInitialAppointment.save();
		assertEquals(error, testWithoutInitialAppointment.getState());
	}

	@Test
	public void testErrorMessageAppointmentRepresentationWithInitialAppointment() {
		AppointmentRepresentation testWithInitialAppointment = new AppointmentRepresentation(allAppointments,
				testAppointment);
		testWithInitialAppointment.setName("");
		testWithInitialAppointment.save();
		assertEquals(error, testWithInitialAppointment.getState());
	}

	@Test
	public void testUnsavedMessageDate() {
		AppointmentRepresentation testWithInitialAppointment = new AppointmentRepresentation(allAppointments,
				testAppointment);
		testWithInitialAppointment.setDate(new SimpleDate(15, 10));
		assertEquals(unsaved, testWithInitialAppointment.getState());
		testWithInitialAppointment.setDate(new SimpleDate(10, 10));
		assertEquals(nothing, testWithInitialAppointment.getState());
	}

	@Test
	public void testUnsavedMessageName() {
		AppointmentRepresentation testWithInitialAppointment = new AppointmentRepresentation(allAppointments,
				testAppointment);
		testWithInitialAppointment.setName("Sam");
		assertEquals(unsaved, testWithInitialAppointment.getState());
		testWithInitialAppointment.setName("Frodo");
		assertEquals(nothing, testWithInitialAppointment.getState());
	}

	@Test
	public void testUnsavedMessageDescription() {
		AppointmentRepresentation testWithInitialAppointment = new AppointmentRepresentation(allAppointments,
				testAppointment);
		testWithInitialAppointment.setDescription("Hello There");
		assertEquals(unsaved, testWithInitialAppointment.getState());
		testWithInitialAppointment.setDescription("");
		assertEquals(nothing, testWithInitialAppointment.getState());
	}

	@Test
	public void testUnsavedMessageIsABirthday() {
		AppointmentRepresentation testWithInitialAppointment = new AppointmentRepresentation(allAppointments,
				testAppointment);
		testWithInitialAppointment.setIsABirthday(true);
		assertEquals(unsaved, testWithInitialAppointment.getState());
		testWithInitialAppointment.setIsABirthday(false);
		assertEquals(nothing, testWithInitialAppointment.getState());
	}

}
