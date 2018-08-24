package test;

import org.json.JSONArray;
import org.json.JSONObject;

import main.*;

public class XReadWriteFile_test {
	public static void main(String[] args) {

		XWriteFileTest();

		XReadWriteFile.getInstance().setOperatingFolderPath("D:\\XClimbers\\BiliBiliClimber");

		XReadWriteFile.getInstance().setReadFileName("output_file_test.txt");

		String read_result = XReadWriteFile.getInstance().readString();

		System.out.println(read_result);
	}

	public static void XWriteFileTest() {

		XReadWriteFile.getInstance().setOperatingFolderPath("D:\\XClimbers\\BiliBiliClimber");

		XReadWriteFile.getInstance().setOutputFileName("output_file_test.txt");

		JSONArray jsonArray = new JSONArray();
		JSONObject studentObject = new JSONObject();
		studentObject.put("name", "Peter Parker");
		studentObject.put("phone number", "15317310380");
		studentObject.put("location", "zhongshanbei road 3663 number");

		JSONObject englishClassObject = new JSONObject();
		englishClassObject.put("name", "English");
		englishClassObject.put("description", "this is a good lesson");

		JSONObject computerClassObject = new JSONObject();
		computerClassObject.put("name", "Computer Science");
		computerClassObject.put("description", "this is a bad lesson");

		JSONArray classes = new JSONArray();
		classes.put(englishClassObject);
		classes.put(computerClassObject);

		studentObject.put("classes", classes);

		// 5
		jsonArray.put(studentObject);
		jsonArray.put(studentObject);
		jsonArray.put(studentObject);
		jsonArray.put(studentObject);
		jsonArray.put(studentObject);

		String result_string_code = XReadWriteFile.getInstance().outputString(jsonArray.toString(1));

		System.out.println(result_string_code);
	}
}
