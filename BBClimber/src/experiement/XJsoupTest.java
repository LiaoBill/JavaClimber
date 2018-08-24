package experiement;

import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class XJsoupTest {
	public static void main(String[] args) {
		try {
			String url = "https://api.bilibili.com/x/v2/reply?jsonp=jsonp&pn=2&type=1&oid=29943731";
			// Document document = Jsoup.connect(url).ignoreContentType(true).get();
			Document document = Jsoup.parse(new URL(url).openStream(), "utf-8", url);
			System.out.println(document.text());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
	}
}
