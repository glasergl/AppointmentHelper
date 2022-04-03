package reader;

import java.io.File;
import java.util.Optional;

/**
 * Exception to indicate that a File doesn't have the correct format.
 * 
 * Optionally a line can be determined to show an error with more precision.
 * 
 * @author Gabriel Glaser
 * @version 29.03.2022
 */
public final class IllegalFileFormatException extends RuntimeException {

    /**
     * Constructor with the message containing the location of the file and an
     * optional line to specify the error location.
     * 
     * @param file
     * @param line
     */
    private IllegalFileFormatException(final File file, final Optional<Integer> line) {
	super(file.getAbsolutePath() + " doesn't have the correct format" + (line.isPresent() ? " at line " + line : "") + ".");
    }

    /**
     * Constructor with the message containing the location of the file with errors
     * and the line as the error location.
     * 
     * @param file
     * @param line
     */
    public IllegalFileFormatException(final File file, final int line) {
	this(file, Optional.of(line));
    }

    /**
     * Constructor with the message containing the location of the file with errors.
     * 
     * @param file
     */
    public IllegalFileFormatException(final File file) {
	this(file, Optional.empty());
    }

}
