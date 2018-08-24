package main;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class XCommentObject {
	private String username;
	private String comment_string;
	private int like_count;
	private ArrayList<XCommentObject> replies;

	public void addReply(XCommentObject reply) {
		replies.add(reply);
	}

	public XCommentObject() {
		super();
		username = "";
		comment_string = "";
		like_count = 0;
		replies = new ArrayList<>();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getComment_string() {
		return comment_string;
	}

	public void setComment_string(String comment_string) {
		this.comment_string = comment_string;
	}

	public int getLike_count() {
		return like_count;
	}

	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}

	public ArrayList<XCommentObject> getReplies() {
		return replies;
	}

	public void setReplies(ArrayList<XCommentObject> replies) {
		this.replies = replies;
	}

	public JSONObject toJsonObject() {
		/*
		 * pre: 当前commentObject中的所有字段都被赋值 post: 转化为JSONObject并返回 exception：
		 * 转化失败，报错0005-BC并返回空
		 */
		JSONObject current_making_json_object = new JSONObject();
		try {
			current_making_json_object.put("username", username);
			current_making_json_object.put("comment_string", comment_string);
			current_making_json_object.put("like_count", like_count);

			JSONArray replices_json_array = new JSONArray();
			for (int i = 0; i != replies.size(); i++) {
				JSONObject current_reply_comment = replies.get(i).toJsonObject();
				replices_json_array.put(current_reply_comment);
			}

			current_making_json_object.put("replies", replices_json_array);

			return current_making_json_object;
		} catch (Exception e) {
			XErrorCodeHandler.getInstance().printCommentToJsonObjectError("0005-BC", username, comment_string);
			return null;
		}

	}
}
