package test;

import main.*;

public class XAVObject_test {
	public static void main(String[] args) {
		XAVObject aObject = new XAVObject();

		aObject.setAv_number("2030058103");
		aObject.setName("蜘蛛侠PS4游戏画面实际测试");
		aObject.setDescription("YouTube转载");

		// comment one
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

		// comment two
		XCommentObject commentObject2 = new XCommentObject();
		commentObject2.setUsername("CCPPVAN");
		commentObject2.setComment_string("这个画面讲真很棒啊");
		commentObject2.setLike_count(10);

		XCommentObject subcomment_21 = new XCommentObject();
		subcomment_21.setUsername("Stupid沙雕");
		subcomment_21.setComment_string("虐杀原形了解一下");
		subcomment_21.setLike_count(-2);

		XCommentObject subcomment_22 = new XCommentObject();
		subcomment_22.setUsername("钢筋");
		subcomment_22.setComment_string("楼上字如其名");
		subcomment_22.setLike_count(1000);

		commentObject2.addReply(subcomment_21);
		commentObject2.addReply(subcomment_22);

		// add comments
		aObject.addComment(commentObject);
		aObject.addComment(commentObject2);

		System.out.println(aObject.toJsonObject().toString(1));
	}
}
