package ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Optional;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import appointment.Appointment;
import appointment.SimpleDate;
import main.AppointmentInteracter;
import standard.implementations.MyButton;
import standard.implementations.MyCheckBox;
import standard.implementations.MyHintTextField;
import standard.implementations.MyLabel;
import standard.settings.Colors;
import ui.dateInput.MySimpleDateField;

public class AppointmentRepresentation extends JPanel {

	private final AllAppointments parent;
	private final MyLabel message = new MyLabel();
	private final MySimpleDateField date;
	private final MyHintTextField name = new MyHintTextField("Name");
	private final MyHintTextField description = new MyHintTextField("Beschreibung");
	private final MyCheckBox isABirthday = new MyCheckBox("ist Geburtstag");
	private final MyButton cancel = new MyButton("Cancel");
	private final MyButton delete = new MyButton("Delete");

	private Optional<Appointment> currentlyStored = Optional.empty();

	public AppointmentRepresentation(final AllAppointments parent, final Appointment toDisplay) {
		super();
		this.parent = parent;
		this.currentlyStored = Optional.of(toDisplay);
		this.date = new MySimpleDateField(toDisplay.getDate(), this);
		name.setText(toDisplay.getName());
		description.setText(toDisplay.getDescription());
		isABirthday.setSelected(toDisplay.isABirthday());
		setup();
	}

	public AppointmentRepresentation(final AllAppointments parent) {
		super();
		this.parent = parent;
		this.currentlyStored = Optional.empty();
		this.date = new MySimpleDateField(this);
		message.setText("*");
		isABirthday.setSelected(true);
		setup();
	}

	private void setup() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
		setBackground(Colors.getGray(1));
		setupSubcomponents();
	}

	private void setupSubcomponents() {
		message.setPreferredSize(new Dimension(20, 20));
		message.setBackground(getBackground());
		name.setColumns(16);
		description.setColumns(40);
		isABirthday.setBackground(getBackground());
		setupActionListeners();
		addComponents();
		isABirthday.addChangeListener((change) -> {
			updateSavedState();
		});
		final DocumentListener forChanges = new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateSavedState();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateSavedState();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateSavedState();
			}
		};
		name.addDocumentListener(forChanges);
		description.addDocumentListener(forChanges);
	}

	private void setupActionListeners() {
		cancel.addActionListener((click) -> {
			cancel();
		});
		delete.addActionListener((click) -> {
			delete();
		});
	}

	private void addComponents() {
		add(message);
		add(date);
		add(name);
		add(description);
		add(isABirthday);
		add(cancel);
		add(delete);
	}

	public void updateSavedState() {
		if (currentlyStored.isPresent()) {
			final Appointment currentlyStored = this.currentlyStored.get();
			if (currentlyStored.getDate().equals(date.getDate()) && currentlyStored.getName().equals(name.getText())
					&& currentlyStored.getDescription().equals(description.getText())
					&& currentlyStored.isABirthday() == isABirthday.isSelected()) {
				message.setText("");
			} else {
				message.setText("*");
			}
		}
	}

	private void cancel() {
		if (currentlyStored.isPresent()) {
			date.setDate(currentlyStored.get().getDate());
			name.setText(currentlyStored.get().getName());
			description.setText(currentlyStored.get().getDescription());
			isABirthday.setSelected(currentlyStored.get().isABirthday());
		}
	}

	private void delete() {
		parent.removeAppointment(this);
		if (currentlyStored.isPresent()) {
			AppointmentInteracter.remove(currentlyStored.get());
		}
	}

	public void save() {
		final String name = this.name.getText();
		if (name.length() != 0) {
			message.setText("");
			final Appointment currentContent;
			if (currentlyStored.isPresent()) {
				currentContent = new Appointment(date.getDate(), name, description.getText(), isABirthday.isSelected(),
						currentlyStored.get().getID());
				AppointmentInteracter.updateAppointment(currentContent);
			} else {
				currentContent = new Appointment(date.getDate(), name, description.getText(), isABirthday.isSelected());
				AppointmentInteracter.add(currentContent);
			}
			currentlyStored = Optional.of(currentContent);
		} else {
			message.setText("!");
		}
	}

	public String getMessage() {
		return message.getText();
	}

	public void setDate(final SimpleDate newDate) {
		date.setDate(newDate);
	}

	public void setName(final String newName) {
		name.setText(newName);
	}

	public void setDescription(final String newDescription) {
		description.setText(newDescription);
	}

	public void setIsABirthday(final boolean newIsABirthday) {
		isABirthday.setSelected(newIsABirthday);
	}

}
