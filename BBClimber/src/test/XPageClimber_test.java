package test;

import main.XAVObject;
import main.XPageClimber;

public class XPageClimber_test {
	public static void main(String[] args) {
		XAVObject av_object = XPageClimber.getInstance().getAVObjectFromWeb("29943731");
		if (av_object == null) {
			return;
		}
		int page_count = XPageClimber.getInstance().getCommentPageSize("29943731");
		if (page_count < 0) {
			return;
		}
		String result_code = XPageClimber.getInstance().fillComments(av_object, page_count);
		if (!result_code.equals("SUCCESS")) {
			return;
		}
		System.out.println(av_object.toJsonObject().toString(1));
	}
}
