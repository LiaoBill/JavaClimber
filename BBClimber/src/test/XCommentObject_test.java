package test;

import org.json.JSONObject;

import main.*;

public class XCommentObject_test {
	public static void main(String[] args) {
		XCommentObject commentObject = new XCommentObject();
		commentObject.setUsername("Billliao");
		commentObject.setComment_string("今天天气真好");
		commentObject.setLike_count(20);

		XCommentObject subcomment_1 = new XCommentObject();
		subcomment_1.setUsername("CAT");
		subcomment_1.setComment_string("今天天气一般般吧");
		subcomment_1.setLike_count(0);

		XCommentObject subcomment_2 = new XCommentObject();
		subcomment_2.setUsername("DOG");
		subcomment_2.setComment_string("为什么开始谈论天气了");
		subcomment_2.setLike_count(10);

		commentObject.addReply(subcomment_1);
		commentObject.addReply(subcomment_2);

		JSONObject comment_json_object = commentObject.toJsonObject();
		System.out.println(comment_json_object.toString(1));
	}
}
