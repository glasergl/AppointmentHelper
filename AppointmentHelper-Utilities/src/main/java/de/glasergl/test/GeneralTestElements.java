package de.glasergl.test;

import static org.junit.Assert.assertTrue;

import java.io.File;

import de.glasergl.fileConfiguration.AppointmentsConfigurationHandler;

/**
 * Class which contains generally applicable elements for performing tests
 * across the whole application.
 * 
 * @author Gabriel Glaser
 */
public class GeneralTestElements {
	/**
	 * Base path to test resources given by the Maven standard.
	 */
	public static final String BASE_TEST_RESOURCE_PATH = "src" + File.separator + "test" + File.separator + "resources"
			+ File.separator;

	/**
	 * Deletes the given file and asserts that the deletion was successful.
	 * 
	 * @param testFile
	 */
	public static void deleteTestFile(final File testFile) {
		final boolean couldSuccessfullyBeDeleted = testFile.delete();
		assertTrue(couldSuccessfullyBeDeleted);
	}

	/**
	 * Deletes the file represented by the given handler and asserts that the
	 * deletion was successful.
	 * 
	 * @param handler
	 */
	public static void deleteTestFileRepresentedBy(final AppointmentsConfigurationHandler handler) {
		final File testFile = handler.getConfigurationFile();
		deleteTestFile(testFile);
	}
}
