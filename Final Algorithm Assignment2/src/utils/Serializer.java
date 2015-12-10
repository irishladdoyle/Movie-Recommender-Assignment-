package utils;

/**
 * 
 * @author Eamonn deLeaster
 * Push - enables an application to‘push’ various objects onto a stack
 * Pop - allows the read back 
 * write - writes the file into the stack
 * read - reads the file into the stack
 *
 */

public interface Serializer {
	void push(Object o);

	Object pop();

	void write() throws Exception;

	void read() throws Exception;
}