package fileConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

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
public abstract class ConfigurationHandler {
	private static final int DEFAULT_JSON_INDENT_FACTOR = 3;
	private static final int DEFAULT_TOP_LEVEL_JSON_INDENT = 0;
	private static final Charset ENCODING = StandardCharsets.UTF_8;

	protected final File configurationFile;
	protected final JSONObject configuration;

	protected ConfigurationHandler(final String configurationFilePath) {
		super();
		this.configurationFile = new File(configurationFilePath);
		if (!configurationFile.exists()) {
			createEmptyFileInstance(configurationFile);
			this.configuration = createEmptyJSONConfiguration();
			storeConfiguration();
		} else {
			this.configuration = getInitialConfiguration();
		}
	}

	/**
	 * @return A JSON Object which represents an empty (but valid!) configuration of
	 *         the respective use case. If no configuration can be found on the hard
	 *         disk (at the given path at constructor time), then this function will
	 *         be called and the returned JSONObject will be stored on the hard
	 *         disk.
	 */
	protected abstract JSONObject createEmptyJSONConfiguration();

	/**
	 * Stores the current content of the JSONObject configuration to the file at the
	 * path given at constructor time.
	 */
	public final void storeConfiguration() {
		try (final OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(configurationFile),
				StandardCharsets.UTF_8)) {
			configuration.write(writer, DEFAULT_JSON_INDENT_FACTOR, DEFAULT_TOP_LEVEL_JSON_INDENT);
		} catch (final IOException e) {
			e.printStackTrace();
			throw new RuntimeException(
					"Couldn't store configuration to the file at <" + configurationFile.getAbsolutePath() + ">");
		}
	}

	/**
	 * @return The initial configuration stored in the configurationFile.
	 */
	private JSONObject getInitialConfiguration() {
		try (final InputStreamReader reader = new InputStreamReader(new FileInputStream(configurationFile),
				ENCODING);) {
			return new JSONObject(new JSONTokener(reader));
		} catch (final IOException e) {
			throw new RuntimeException(
					"Couldn't read configuration out of file at <" + configurationFile.getAbsolutePath() + ">");
		}
	}

	public File getConfigurationFile() {
		return configurationFile;
	}

	public JSONObject getConfigurationJSON() {
		return configuration;
	}

	private void createEmptyFileInstance(final File file) {
		try {
			final File parent = file.getParentFile();
			if (parent != null) {
				parent.mkdirs();
			}
			final boolean fileCreatedSuccessfully = file.createNewFile();
			if (!fileCreatedSuccessfully) {
				throw new RuntimeException("Couldn't create file at \"" + file.getAbsolutePath() + "\".");
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
