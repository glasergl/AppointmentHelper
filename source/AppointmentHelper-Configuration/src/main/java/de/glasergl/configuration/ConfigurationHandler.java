package de.glasergl.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Class which allows the interaction with the configurationFile.
 *
 * @author Gabriel Glaser
 * @version 18.6.2022
 */
public final class ConfigurationHandler {

    private static final String DEFAULT_CONFIGURATION_FILE_PATH = "AppointmentHelper_Configuration.json";
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private static final int JSON_INDENT_FACTOR = 3;
    private static final int TOP_LEVEL_JSON_INDENT = 0;

    private final File configurationFile;
    private final JSONObject configuration;
    private final AppointmentsConfigurationHandler appointmentsHandler;

    /**
     * Creates an instance out of the given configurationFile.
     * 
     * @param configurationFile
     */
    public ConfigurationHandler(final File configurationFile) {
	super();
	this.configurationFile = configurationFile;
	this.configuration = getInitialConfiguration();
	this.appointmentsHandler = new AppointmentsConfigurationHandler(this, configuration);
    }

    /**
     * Creates an instance with the default configurationFile.
     */
    public ConfigurationHandler() {
	this(getDefaultConfigurationFile());
    }

    /**
     * Saves the current state of the configuration to the configurationFile.
     */
    public void saveConfiguration() {
	if (!configurationFile.exists()) {
	    throw new IllegalArgumentException("The configuration-file doesn't exist.");
	}
	try (final OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(configurationFile), DEFAULT_CHARSET)) {
	    configuration.write(writer, JSON_INDENT_FACTOR, TOP_LEVEL_JSON_INDENT);
	} catch (final IOException e) {
	    throw new RuntimeException("Couldn't store configuration to the file.");
	}
    }

    /**
     * @return The initial configuration stored in the configurationFile.
     */
    private JSONObject getInitialConfiguration() {
	if (!configurationFile.exists()) {
	    throw new IllegalArgumentException("The configuration-file doesn't exist.");
	}
	try (final InputStreamReader reader = new InputStreamReader(new FileInputStream(configurationFile), DEFAULT_CHARSET);) {
	    return new JSONObject(new JSONTokener(reader));
	} catch (final IOException e) {
	    throw new RuntimeException("Couldn't parse configuration-file to a JSONObject.");
	}
    }

    public AppointmentsConfigurationHandler getAppointmentsHandler() {
	return appointmentsHandler;
    }

    /**
     * @return The existing default configurationFile or else, a created one.
     */
    public static File getDefaultConfigurationFile() {
	if (Files.exists(Paths.get(DEFAULT_CONFIGURATION_FILE_PATH))) {
	    return new File(DEFAULT_CONFIGURATION_FILE_PATH);
	} else {
	    return createNewConfigurationFile(DEFAULT_CONFIGURATION_FILE_PATH);
	}
    }

    /**
     * @param pathOfNewConfigurationFile
     * @throws IllegalArgumentException If the configurationFile already exists.
     * @return The created configurationFile.
     */
    public static File createNewConfigurationFile(final String pathOfNewConfigurationFile) throws IllegalArgumentException {
	final JSONObject newConfiguration = new JSONObject();
	newConfiguration.put(AppointmentsConfigurationHandler.JSON_KEY, new JSONArray());
	final File newConfigurationFile = new File(pathOfNewConfigurationFile);
	if (newConfigurationFile.exists()) {
	    throw new IllegalArgumentException("ConfigurationFile at " + pathOfNewConfigurationFile + " already exists.");
	}
	try (final OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(newConfigurationFile), DEFAULT_CHARSET);) {
	    newConfiguration.write(writer, JSON_INDENT_FACTOR, TOP_LEVEL_JSON_INDENT);
	} catch (final IOException e) {
	    throw new RuntimeException("Couldn't create configurationFile " + pathOfNewConfigurationFile);
	}
	return newConfigurationFile;
    }
}
