package main;

import java.io.File;

public final class Adapter {

    private static final File GEBURTSTAGE = new File("Geburtstage.txt");
    private static final File APPOINTMENTS = new File("appointments.json");

    public static void main(String[] args) {
	System.out.println(GEBURTSTAGE.exists());
	final Reader reader = new Reader(GEBURTSTAGE);
	final Storer storer = new Storer(reader.getAdapted());
	storer.storeAppointmentsAt(APPOINTMENTS);
    }

}
