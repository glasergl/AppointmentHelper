package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import appointment.Appointment;
import appointment.SimpleDate;

public final class Reader {

	private final File toRead;
	private final List<Appointment> adapted = new ArrayList<>();

	public Reader(final File oldAppointments) {
		super();
		this.toRead = oldAppointments;
		caclulateAdapted();
	}

	private void caclulateAdapted() {
		try (final BufferedReader reader = new BufferedReader(new FileReader(toRead))) {
			String currentLine;
			while ((currentLine = reader.readLine()) != null) {
				if (!currentLine.equals("") && currentLine.charAt(0) != '#') {
					adapted.add(extractFromLine(currentLine));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Appointment extractFromLine(final String line) {
		final String[] components = line.split("\\.");
		final String name = components[0];
		final String description = components[1];
		final SimpleDate date = new SimpleDate(Integer.valueOf(components[2]), Integer.valueOf(components[3]));
		return new Appointment(date, name, description, true);
	}

	public List<Appointment> getAdapted() {
		return adapted;
	}

}
