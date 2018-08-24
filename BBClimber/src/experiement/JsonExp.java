package experiement;

import java.util.*;

import org.json.*;

public class JsonExp {
	public static void main(String[] args) {
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

		System.out.println(jsonArray.toString(1));

		String readString = jsonArray.toString(0);

		JSONArray readArray = new JSONArray(readString);

		String information = "";
		for (Object jsonObject : readArray) {
			JSONObject current_json_object = (JSONObject) jsonObject;
			information += current_json_object.get("name") + "|";
			information += current_json_object.get("phone number") + "|";
			information += current_json_object.get("location") + "|";
		}
		System.out.println(information);
	}
}
