package main;

import java.util.Optional;

/**
 * Exception to indicate that a File doesn't have the correct format.
 * 
 * @author Gabriel Glaser
 * @version 19.01.2022
 */
public final class IllegalFileFormatException extends Exception {

    private final String fileName;
    private final Optional<Integer> line;

    private IllegalFileFormatException(final String fileName, final Optional<Integer> line) {
	super(fileName + " doesn't have the correct format" + (line.isPresent() ? " at line " + line : "") + ".");
	this.fileName = fileName;
	this.line = line;
    }

    public IllegalFileFormatException(final String fileName, final int line) {
	this(fileName, Optional.of(line));
    }

    public IllegalFileFormatException(final String fileName) {
	this(fileName, Optional.empty());
    }

    public String getFileName() {
	return this.fileName;
    }

    public int getLine() {
	return line.get();
    }

}
