package main;

import java.util.Scanner;

public class MainClass {
	public static void main(String[] args) {
		// get av_number
		String av_number = "";
		if (args == null || args.length == 0) {
			Scanner system_scanner = new Scanner(System.in);
			System.out.print("AV NUMBER PLEASE : ");
			av_number = system_scanner.nextLine();
			av_number = av_number.substring(2);
			system_scanner.close();
		} else {
			av_number = args[0];
		}

		// fill av object
		XAVObject av_object = XPageClimber.getInstance().getAVObjectFromWeb(av_number);
		if (av_object == null) {
			return;
		}
		System.out.println("FETCH BASIC INFORMATION OF [" + av_number + "] COMPLETE");
		System.out.println("--NAME : " + av_object.getName() + "\n" + "--DESCRIPTION : " + av_object.getDescription());
		int page_count = XPageClimber.getInstance().getCommentPageSize(av_number);
		if (page_count < 0) {
			return;
		}
		System.out.println("FETCH COMMENT PAGE COUNT OF [" + av_number + "] COMPLETE");
		System.out.println("COMMENT PAGE COUNT : " + page_count);
		System.out.println("FILLING COMMENTS......");
		String result_code = XPageClimber.getInstance().fillComments(av_object, page_count);
		if (!result_code.equals("SUCCESS")) {
			return;
		}
		System.out.println("COMMENTS FILLED");
		System.out.println("WRITING TO DISK......");
		// write to file
		XReadWriteFile.getInstance().setOperatingFolderPath("D:\\XClimbers\\BiliBiliClimber");

		XReadWriteFile.getInstance().setOutputFileName(av_number + "_" + av_object.getName() + ".txt");

		XReadWriteFile.getInstance().outputString(av_object.toJsonObject().toString(1));

		System.out.println("WRITE COMPLETE!");
		System.out.println("FINISH!");
	}
}
