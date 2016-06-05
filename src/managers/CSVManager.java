/**
 * 
 */
package managers;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

import utils.DateHelper;
import utils.ESFileWriter;
import utils.FileHelper;

/**
 * @author ES
 *
 */
public class CSVManager {
	private ESFileWriter fileWriter = null;
	private String fileName = null;
	
	public CSVManager (String dir, String fileName) {
		// Create directory
		FileHelper.createDirectory(dir);
		// Format the fileName
		this.fileName = String.format("%s/%s_%s.csv", dir, DateHelper.getDate(), fileName);
		// Set the fileWriter and create a file
		setFileWriter(this.fileName, true);
	}
	
	
	/**
	 * 
	 * @param fileName
	 * @param rs
	 * @param append
	 * @return
	 */
	public boolean writeAsCSV(String fileName, ResultSet rs, boolean append) {
		this.fileName = String.format("%s_%s.csv", fileName, DateHelper.getDate());
		try {
			setFileWriter(this.fileName, append);
			String toWrite = FileHelper.getAsCSV(rs, !fileWriter.exists());
			
			this.fileWriter.writeLine(toWrite);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 
	 * @param fileName
	 * @param stringList
	 * @param append
	 * @return
	 */
	public boolean writeAsCSV(List<String[]> stringList, boolean append) {
		//this.fileName = String.format("%s_%s.csv", fileName, DateHelper.getDate());
		try {
			setFileWriter(this.fileName, append);
			String toWrite = FileHelper.getAsCSV(stringList);
			
			this.fileWriter.writeLine(toWrite);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @return the fileWriter
	 */
	public ESFileWriter getFileWriter() {
		return fileWriter;
	}

	/**
	 * @param fileWriter the fileWriter to set
	 */
	public void setFileWriter(ESFileWriter fileWriter) {
		this.fileWriter = fileWriter;
	}
	
	public void setFileWriter(String fileName, boolean append) {
		this.fileName = fileName;
		try {
			this.fileWriter = FileHelper.createFile(this.fileName, append);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
