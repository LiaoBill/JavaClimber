package main;

import java.io.*;
import java.util.*;

public class XReadWriteFile {
	private static XReadWriteFile thisInstance;

	public static XReadWriteFile getInstance() {
		if (thisInstance == null) {
			thisInstance = new XReadWriteFile();
		}
		return thisInstance;
	}

	private XReadWriteFile() {
		outputFileName = "";
		readFileName = "";
		operatingFolderPath = "";
	}

	private String outputFileName;
	private String readFileName;
	private String operatingFolderPath;

	public String getOperatingFolderPath() {
		return operatingFolderPath;
	}

	public void setOperatingFolderPath(String operatingFolderPath) {
		this.operatingFolderPath = operatingFolderPath;
	}

	public String getReadFileName() {
		return readFileName;
	}

	public void setReadFileName(String readFileName) {
		this.readFileName = readFileName;
	}

	public String getOutputFileName() {
		return outputFileName;
	}

	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

	/*
	 * Ignore empty line(with just \n or space or \t), keep line seperator "\n"
	 */
	public String readString() {
		// CHECK FOLDER PATH
		if (operatingFolderPath.equals("")) {
			return "OPERATING FOLDER PATH NOT SET";
		}
		File check_folder = new File(operatingFolderPath);
		check_folder.mkdirs();
		try {
			if (!check_folder.exists()) {
				return "CREATE FOLDER NOT SUCCESS";
			}
		} catch (Exception e) {
			// TODO: handle exception
			return "CREATE FOLDER EXCEPTION";
		}

		// CHECK FILE
		if (readFileName.equals("")) {
			return "READ FILE NAME NOT SET";
		}
		File check_file = new File(operatingFolderPath + "\\" + readFileName);

		try {
			// create the file if not existing
			check_file.createNewFile();
			if (!check_file.canExecute()) {
				return "FILE NOT EXECUTE ERROR";
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			return "FILE CHECK EXCEPTION";
		}

		// READ CONTENT
		Scanner scanner = null;
		String read_result = "";
		try {
			scanner = new Scanner(new File(operatingFolderPath + "\\" + readFileName), "utf8");
			while (scanner.hasNextLine()) {
				String current_line = scanner.nextLine();

				if (current_line.trim().equals("")) {
					continue;
				}
				read_result += current_line + "\n";
			}
			scanner.close();
			return read_result;
		} catch (Exception e) {
			// TODO: handle exception
			if (scanner != null) {
				scanner.close();
			}
			return "FILE READ EXCEPTION";
		}
	}

	public String outputString(String string_to_output) {
		// CHECK FOLDER PATH
		if (operatingFolderPath.equals("")) {
			return "OPERATING FOLDER PATH NOT SET";
		}
		File check_folder = new File(operatingFolderPath);
		check_folder.mkdirs();
		try {
			if (!check_folder.exists()) {
				return "CREATE FOLDER NOT SUCCESS";
			}
		} catch (Exception e) {
			// TODO: handle exception
			return "CREATE FOLDER EXCEPTION";
		}

		// CHECK FILE
		if (outputFileName.equals("")) {
			return "OUTPUT FILE NAME NOT SET";
		}
		File check_file = new File(operatingFolderPath + "\\" + outputFileName);

		try {
			// create the file if not existing
			check_file.createNewFile();
			if (!check_file.canExecute()) {
				return "FILE NOT EXECUTE ERROR";
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			return "FILE CREATION EXCEPTION";
		}

		// OUTPUT CONTENT
		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(operatingFolderPath + "\\" + outputFileName, "utf8");
			printWriter.print(string_to_output);
			printWriter.close();
			return "WRITE SUCCESS";
		} catch (Exception e) {
			if (printWriter != null) {
				printWriter.close();
			}
			System.out.println(e.toString());
			return "WRITE EXCEPTION OCCURED";
		}
	}

}
