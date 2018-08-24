package main;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class XAVObject {
	private String av_number;
	private String name;
	private String description;
	private ArrayList<XCommentObject> comments;

	public void addComment(XCommentObject new_comment) {
		comments.add(new_comment);
	}

	public XAVObject() {
		super();
		av_number = "";
		name = "";
		description = "";
		comments = new ArrayList<>();
	}

	public String getAv_number() {
		return av_number;
	}

	public void setAv_number(String av_number) {
		this.av_number = av_number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<XCommentObject> getComments() {
		return comments;
	}

	public void setComments(ArrayList<XCommentObject> comments) {
		this.comments = comments;
	}

	public JSONObject toJsonObject() {
		/*
		 * pre: 当前avObject中的所有字段都被赋值 post: 转化为JSONObject并返回 exception：
		 * 转化失败，报错0006-BC并返回空
		 */
		try {
			JSONObject current_making_json_object = new JSONObject();
			current_making_json_object.put("av_number", av_number);
			current_making_json_object.put("name", name);
			current_making_json_object.put("description", description);

			JSONArray comment_json_array = new JSONArray();
			for (int i = 0; i != comments.size(); i++) {
				JSONObject current_comment_json_object = comments.get(i).toJsonObject();
				if (current_comment_json_object == null) {
					// comment to json object convertion error occurred
					XErrorCodeHandler.getInstance().printAVObjectToJsonObjectError("0006-BC", av_number, name);
					return null;
				}
				comment_json_array.put(current_comment_json_object);
			}

			current_making_json_object.put("comments", comment_json_array);
			return current_making_json_object;
		} catch (Exception e) {
			XErrorCodeHandler.getInstance().printAVObjectToJsonObjectError("0006-BC", av_number, name);
			return null;
		}
	}
}
