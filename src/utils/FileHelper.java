/**
 * 
 */
package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.util.List;

import utils.csv.CSVWriter;

/**
 * @author ES
 *
 */
public class FileHelper {

	public static final String UTF8 = "UTF-8";

	/**
	 * Singleton
	 */
	private FileHelper() {
	}

	/**
	 * Creates a new directory, false otherwise
	 * 
	 * @param path
	 * @return
	 */
	public static boolean createDirectory(String path) {
		File dir = new File(path);

		return (!dir.exists()) ? dir.mkdirs() : false;
	}

	/**
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static ESFileWriter createFile(String path, boolean append) throws IOException {
		return new ESFileWriter (path, append);
	}

	/**
	 * Deletes a file on the given path
	 * @param path
	 * @return
	 */
	public static boolean deleteFile (String path) {
		return deleteFile (new File (path));
	}
	/**
	 * Deletes a file, if exists and is a file.
	 * @param file
	 * @return
	 */
	public static boolean deleteFile (File file) {
		return (exists (file) && isFile(file)) ? file.delete() : false;
	}
	
	/**
	 * Checks if a file exists in the given path
	 * 
	 * @param path
	 * @return
	 */
	public static boolean exists(String path) {
		return exists(new File(path));
	}

	/**
	 * returns true if the file exists, false otherwise
	 * 
	 * @param file
	 * @return
	 */
	public static boolean exists(File file) {
		return file.exists();
	}

	/**
	 * Returns true if "file" is a File, false otherwise
	 * 
	 * @param file
	 * @return
	 */
	public static boolean isFile(File file) {
		return file.isFile();
	}

	/**
	 * Returns True if file is readable, false otherwise
	 * 
	 * @param file
	 * @return
	 */
	public static boolean isReadable(File file) {
		return file.canRead();
	}

	/**
	 * 
	 * @param file
	 * @return Returns a CSVWriter for the file, or null otherwise.
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 */
	public static CSVWriter createFrenchWriter(File file) throws UnsupportedEncodingException, FileNotFoundException {

		if (file != null) {
			// encode file in UTF-8 to allow French characters
			BufferedWriter buffWriter = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(file, true), UTF8));

			return new CSVWriter(buffWriter);
		}

		return null;
	}

	/**
	 * 
	 * @param List<String[]>:
	 *            List of Strings to write to a csv file
	 * @param output:
	 *            File
	 * @param append:
	 *            true if we want to append to an existing file
	 * @return
	 * @throws Exception
	 */
	public static void saveAsCSV(List<String[]> allLines, File output, boolean append) throws Exception {

		BufferedWriter buffWriter = new BufferedWriter(new FileWriter(output, append));
		CSVWriter csvWriter = new CSVWriter(buffWriter);
		csvWriter.writeAll(allLines);

		csvWriter.flush();
		csvWriter.close();
		buffWriter.close();
	}

	/**
	 * Writes result set to a CSV file
	 * 
	 * @param rs:
	 *            Result Set to write
	 * @param output:
	 *            Output File
	 * @param append
	 * @param addColumnNames:
	 *            Only when a new file is used
	 * @throws Exception
	 */
	public static void saveAsCSV(ResultSet rs, File output, boolean append, boolean addColumnNames) throws Exception {

		BufferedWriter buffWriter = new BufferedWriter(new FileWriter(output, append));
		CSVWriter csvWriter = new CSVWriter(buffWriter);
		csvWriter.writeAll(rs, output.length() == 0 && addColumnNames);

		csvWriter.flush();
		csvWriter.close();
		buffWriter.close();
		rs.close();
	}

	/**
	 * Get the result set into CSV format
	 * 
	 * @param rs
	 * @return
	 * @throws Exception
	 */
	public static String getAsCSV(ResultSet rs, boolean includeColumnNames) throws Exception {
		StringWriter stringWriter = new StringWriter();

		// Write data into file
		CSVWriter csvWriter = new CSVWriter(stringWriter);
		csvWriter.writeAll(rs, includeColumnNames);
		// Flush & Close
		csvWriter.flush();
		csvWriter.close();
		// Release the resultSet
		rs.close();

		return stringWriter.toString();

	}
	
	/**
	 * 
	 * @param stringList
	 * @return
	 * @throws Exception
	 */
	public static String getAsCSV(List<String[]> stringList) throws Exception {
		StringWriter stringWriter = new StringWriter();

		// Write data into file
		CSVWriter csvWriter = new CSVWriter(stringWriter);
		csvWriter.writeAll(stringList);
		// Flush & Close
		csvWriter.flush();
		csvWriter.close();

		return stringWriter.toString();
	}

}
