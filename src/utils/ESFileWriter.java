/**
 * 
 */
package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author ES
 *
 */
public class ESFileWriter {
	private File file;
	private boolean exists;
	private BufferedWriter buffWriter;
	private String eol = new String (new char[] { (char) 0x0d, (char) 0x0a }); // End of line
	private String header;
	private int numberOfEntries;
	
	/**
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public ESFileWriter (String fileName) throws IOException {
		this (fileName, false);
	}
	
	/**
	 * 
	 * @param fileName
	 * @param append
	 * @throws IOException
	 */
	public ESFileWriter (String fileName, boolean append) throws IOException {
		this (new File (fileName), append);
	}
	
	/**
	 * 
	 * @param file
	 * @throws IOException
	 */
	public ESFileWriter (File file) throws IOException {
		this (file, false);
	}
	
	/**
	 * 
	 * @param file
	 * @param append
	 * @throws IOException
	 */
	public ESFileWriter (File file, boolean append) throws IOException {
		this.exists = file.exists();
		this.file = file;
		buffWriter = new BufferedWriter(new FileWriter(file, append));
	}
	
	/**
	 * 
	 * @param line
	 * @throws IOException
	 */
	public void writeLine (String line) throws IOException {
		write (line, true);
	}
	
	/**
	 * 
	 * @param string
	 * @throws IOException
	 */
	public void writeString (String string) throws IOException {
		write (string, false);
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	public void writeEOL () throws IOException {
		buffWriter.write(eol);
		++numberOfEntries;
	}
	
	/**
	 * 
	 * @param line
	 * @param withEOL
	 * @throws IOException
	 */
	private void write (String line, boolean withEOL) throws IOException {
		if (header != null && file.length() == 0) {
			buffWriter.write(header);
			if (withEOL) {
				writeEOL();
			}
			buffWriter.flush();
			header = null;
		}
		
		buffWriter.write(line);
		if (withEOL) {
			writeEOL();
		}
		buffWriter.flush();
	}
	
	public void close () {
		if (buffWriter != null) {
			try {
				buffWriter.flush();
				buffWriter.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * @return the buffWriter
	 */
	public BufferedWriter getBuffWriter() {
		return buffWriter;
	}

	/**
	 * @param buffWriter the buffWriter to set
	 */
	public void setBuffWriter(BufferedWriter buffWriter) {
		this.buffWriter = buffWriter;
	}

	/**
	 * @return the eol
	 */
	public String getEol() {
		return eol;
	}

	/**
	 * @param eol the eol to set
	 */
	public void setEol(String eol) {
		this.eol = eol;
	}

	/**
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * @param header the header to set
	 */
	public void setHeader(String header) {
		this.header = header;
	}

	/**
	 * @return the numberOfEntries
	 */
	public int getNumberOfEntries() {
		return numberOfEntries;
	}

	/**
	 * @param numberOfEntries the numberOfEntries to set
	 */
	public void setNumberOfEntries(int numberOfEntries) {
		this.numberOfEntries = numberOfEntries;
	}

	/**
	 * @return the exists
	 */
	public boolean exists() {
		return exists;
	}

	/**
	 * @param exists the exists to set
	 */
	public void setExists(boolean exists) {
		this.exists = exists;
	}

}
