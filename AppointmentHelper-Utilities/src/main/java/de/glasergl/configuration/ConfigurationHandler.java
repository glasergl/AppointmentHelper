package de.glasergl.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Class which allows interaction with a configuration.
 * 
 * The initial configuration is determined by the JSON-content of the given File
 * at instantiation. Will create a new configurationFile, with valid content, if
 * the given File doesn't exist. The current configuration is represented by the
 * JSONObject configuration. Its content gets written to the given File when
 * storeConfiguration() is called.
 *
 * @author Gabriel Glaser
 */
public final class ConfigurationHandler {

	private static final String DEFAULT_CONFIGURATION_FILE_PATH = "AppointmentHelper_Configuration.json";
	private static final int DEFAULT_JSON_INDENT_FACTOR = 3;
	private static final int DEFAULT_TOP_LEVEL_JSON_INDENT = 0;

	private final File configurationFile;
	private final JSONObject configuration;
	private final AppointmentsConfigurationHandler appointmentsHandler;

	public ConfigurationHandler(final String configurationFilePath) {
		super();
		this.configurationFile = new File(configurationFilePath);
		this.configuration = getInitialConfiguration();
		this.appointmentsHandler = new AppointmentsConfigurationHandler(this, configuration);
	}

	public ConfigurationHandler() {
		this(DEFAULT_CONFIGURATION_FILE_PATH);
	}

	/**
	 * Stores the content of the configuration in the configurationFile. If this
	 * isStored, the method does nothing.
	 */
	public void storeConfiguration() {
		try (final OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(configurationFile),
				StandardCharsets.UTF_8)) {
			configuration.write(writer, DEFAULT_JSON_INDENT_FACTOR, DEFAULT_TOP_LEVEL_JSON_INDENT);
		} catch (final IOException e) {
			throw new RuntimeException(
					"Couldn't store configuration to the file at <" + configurationFile.getAbsolutePath() + ">");
		}
	}

	public AppointmentsConfigurationHandler getAppointmentsHandler() {
		return appointmentsHandler;
	}

	/**
	 * @return The initial configuration stored in the configurationFile. Returns an
	 *         empty, valid configuration, if the file didn't exist.
	 */
	private JSONObject getInitialConfiguration() {
		if (!configurationFile.exists()) {
			createConfigurationFile(configurationFile.getAbsolutePath());
		}
		try (final InputStreamReader reader = new InputStreamReader(new FileInputStream(configurationFile),
				StandardCharsets.UTF_8);) {
			return new JSONObject(new JSONTokener(reader));
		} catch (final IOException e) {
			throw new RuntimeException(
					"Couldn't read configuration out of file at <" + configurationFile.getAbsolutePath() + ">");
		}
	}

	/**
	 * @param pathOfNewConfigurationFile
	 * @throws IllegalArgumentException If the configurationFile already exists.
	 * @return The created configurationFile.
	 */
	public static File createConfigurationFile(final String pathOfNewConfigurationFile)
			throws IllegalArgumentException {
		final File newConfigurationFile = new File(pathOfNewConfigurationFile);
		if (newConfigurationFile.exists()) {
			throw new IllegalArgumentException(
					"ConfigurationFile at " + pathOfNewConfigurationFile + " already exists.");
		}
		final JSONObject newConfiguration = new JSONObject();
		newConfiguration.put(AppointmentsConfigurationHandler.JSON_KEY, new JSONArray());
		try (final OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(newConfigurationFile),
				StandardCharsets.UTF_8);) {
			newConfiguration.write(writer, DEFAULT_JSON_INDENT_FACTOR, DEFAULT_TOP_LEVEL_JSON_INDENT);
		} catch (final IOException e) {
			throw new RuntimeException(
					"Couldn't create configurationFile at <" + newConfigurationFile.getAbsolutePath() + ">");
		}
		return newConfigurationFile;
	}
}
