package main;

import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class XPageClimber {
	private static XPageClimber thisInstance;

	public static XPageClimber getInstance() {
		if (thisInstance == null) {
			thisInstance = new XPageClimber();
		}
		return thisInstance;
	}

	private XPageClimber() {

	}

	private String bilibili_api_base_url = "https://api.bilibili.com/x/v2/reply?jsonp=jsonp&pn=";
	private String bilibili_base_url = "https://www.bilibili.com/video/av";

	public String assembleUrl(String av_number, String page_number) {
		if (av_number == null || av_number.equals("")) {
			XErrorCodeHandler.getInstance().printErrorCode("0001-BC");
			return null;
		}
		if (page_number == null || page_number.equals("")) {
			XErrorCodeHandler.getInstance().printErrorCode("0002-BC");
			return null;
		}
		String url = bilibili_api_base_url + page_number + "&type=1&oid=" + av_number;
		return url;
	}

	public Document fetchHTML_throughUrl(String url, String av_number, String page_number) {
		try {
			Document document = Jsoup.parse(new URL(url).openStream(), "utf-8", url);
			return document;
		} catch (Exception e) {
			XErrorCodeHandler.getInstance().printErrorWith_avNumber_pageNumber("0003-BC", av_number, page_number);
			return null;
		}
	}

	public String getJson_throughDocument(Document document, String av_number, String page_number) {
		try {
			return document.text();
		} catch (Exception e) {
			XErrorCodeHandler.getInstance().printErrorWith_avNumber_pageNumber("0004-BC", av_number, page_number);
			return null;
		}
	}

	public String getPageCommentJson_throughAVnumber(String av_number, String page_number) {
		String url = assembleUrl(av_number, page_number);
		if (url == null) {
			return null;
		}
		Document document = fetchHTML_throughUrl(url, av_number, page_number);
		if (document == null) {
			return null;
		}
		String json = getJson_throughDocument(document, av_number, page_number);
		if (json == null) {
			return null;
		}
		return json;
	}

	public int getCommentPageSize(String av_number) {
		/*
		 * post: return page number exception: error then return -1
		 */
		String json = XPageClimber.getInstance().getPageCommentJson_throughAVnumber(av_number, "1");
		if (json == null) {
			return -1;
		}
		try {
			JSONObject jsonObject = new JSONObject(json);
			JSONObject page_json_object = jsonObject.getJSONObject("data").getJSONObject("page");
			/*
			 * IF STRING IS GOT
			 * 
			 * String comment_size_perpage_string = page_json_object.getInt("size");
			 * System.out.println("good"); String comment_count_string =
			 * page_json_object.getString("count"); System.out.println("good"); int
			 * comment_size_perpage, comment_count; try { comment_size_perpage =
			 * Integer.parseInt(comment_size_perpage_string); comment_count =
			 * Integer.parseInt(comment_count_string); int page_count =
			 * (int)Math.ceil((double)comment_count/comment_size_perpage); return
			 * page_count; } catch (Exception e) {
			 * XErrorCodeHandler.getInstance().printGettingCommentCountError("0008-BC",
			 * av_number); return -2; }
			 */
			int comment_size_perpage = page_json_object.getInt("size");
			int comment_count = page_json_object.getInt("count");
			if (comment_size_perpage == 0) {
				XErrorCodeHandler.getInstance().printGettingCommentCountError("0008-BC", av_number);
				return -2;
			}
			int page_count = (int) Math.ceil((double) comment_count / comment_size_perpage);
			return page_count;
		} catch (Exception e) {
			XErrorCodeHandler.getInstance().printGettingCommentCountError("0008-BC", av_number);
			return -3;
		}
	}

	public XAVObject getAVObjectFromWeb(String av_number) {
		String url = bilibili_base_url + av_number;
		XAVObject making_av_object = new XAVObject();
		try {
			Document document = Jsoup.connect(url).get();
			// av number
			making_av_object.setAv_number(av_number);
			// name
			Element viewbox_report_div = document.getElementById("viewbox_report");
			Element viewbox_report_div_h1 = viewbox_report_div.child(0);
			Element viewbox_report_div_h1_span = viewbox_report_div_h1.child(0);
			making_av_object.setName(viewbox_report_div_h1_span.text());
			// description
			Element v_desc_div = document.getElementById("v_desc");
			Elements v_desc_info_open_div = v_desc_div.getElementsByClass("info open");
			making_av_object.setDescription(v_desc_info_open_div.text());

			return making_av_object;
		} catch (Exception e) {
			XErrorCodeHandler.getInstance().printErrorCode("0007-BC");
			return null;
		}
	}

	public String fillComments(XAVObject av_object, int page_count) {
		/*
		 * pre: av_object with everything filled but comments. page_count ∈ [1,n]
		 */
		// get av_number from av_object
		String av_number = av_object.getAv_number();

		// request for every page number
		for (int i = 0; i != page_count; i++) {

			// get current page number
			String page_number = Integer.toString(i + 1);

			// use url with av_number and page_number to get comment json
			String comment_json = getPageCommentJson_throughAVnumber(av_number, page_number);

			// exception
			if (comment_json == null) {
				XErrorCodeHandler.getInstance().printErrorWith_avNumber_pageNumber("0009-BC", av_number, page_number);
				return null;
			}

			// use comment_json to get object made
			try {
				// read comment json string
				JSONObject jsonObject = new JSONObject(comment_json);

				JSONArray replies_json_array = jsonObject.getJSONObject("data").getJSONArray("replies");

				for (Object object : replies_json_array) {
					XCommentObject current_comment_object = new XCommentObject();
					JSONObject reply_json_object = (JSONObject) object;

					// username
					String username = reply_json_object.getJSONObject("member").getString("uname");
					current_comment_object.setUsername(username);

					// comment_string
					String comment_message = reply_json_object.getJSONObject("content").getString("message");
					current_comment_object.setComment_string(comment_message);

					// like count
					int like_count = reply_json_object.getInt("like");
					current_comment_object.setLike_count(like_count);

					// sub comments
					JSONArray sub_comments_array = reply_json_object.getJSONArray("replies");

					for (Object sub_object : sub_comments_array) {
						XCommentObject current_sub_comment_object = new XCommentObject();
						JSONObject sub_comment_object = (JSONObject) sub_object;

						// username
						String sub_comment_username = sub_comment_object.getJSONObject("member").getString("uname");
						current_sub_comment_object.setUsername(sub_comment_username);

						// comment_string
						String sub_comment_comment_message = sub_comment_object.getJSONObject("content")
								.getString("message");
						current_sub_comment_object.setComment_string(sub_comment_comment_message);

						// like count
						int sub_comment_like_count = sub_comment_object.getInt("like");
						current_sub_comment_object.setLike_count(sub_comment_like_count);

						// add sub comment to comment
						current_comment_object.addReply(current_sub_comment_object);
					}

					// add comment to AVObject
					av_object.addComment(current_comment_object);
				}
			} catch (Exception e) {
				// TODO: handle exception
				XErrorCodeHandler.getInstance().printErrorWith_avNumber_pageNumber("0010-BC", av_number, page_number);
				return null;
			}
		}

		return "SUCCESS";
	}
}
