package main;

import java.util.ArrayList;

public class XErrorCodeHandler {
	private ArrayList<XErrorCodePair> errorcode_list;

	private static XErrorCodeHandler thisInstance;

	public static XErrorCodeHandler getInstance() {
		if (thisInstance == null) {
			thisInstance = new XErrorCodeHandler();
		}
		return thisInstance;
	}

	private XErrorCodeHandler() {
		init();
	}

	private void init() {
		errorcode_list = new ArrayList<>();
		errorcode_list.add(new XErrorCodePair("0001-BC", "ASSEMBLE URL AVNUMBER NULL"));
		errorcode_list.add(new XErrorCodePair("0002-BC", "ASSEMBLE URL PAGE NUMBER NULL"));
		errorcode_list.add(new XErrorCodePair("0003-BC", "FETCHING HTML INFO ERROR"));
		errorcode_list.add(new XErrorCodePair("0004-BC", "GET JSON STRING FROM DOCUMENT ERROR"));
		errorcode_list.add(new XErrorCodePair("0005-BC", "COMMENT TO JSONOBJECT ERROR"));
		errorcode_list.add(new XErrorCodePair("0006-BC", "AVOBJECT TO JSONOBJECT ERROR"));

		errorcode_list.add(new XErrorCodePair("0007-BC", "GET AVOBJECT INFO FROM WEBSITE ERROR"));
		errorcode_list.add(new XErrorCodePair("0008-BC", "GTE COMMENT COUNT ERROR"));
		errorcode_list.add(new XErrorCodePair("0009-BC", "GET PAGE COMMENT JSON FROM WEB NULL ERROR"));
		errorcode_list.add(new XErrorCodePair("0010-BC", "PAGE COMMENT JSON FORMAT READING ERROR"));
	}

	public void printErrorCode(String error_code) {
		for (int i = 0; i != errorcode_list.size(); i++) {
			if (errorcode_list.get(i).getCode().equals(error_code)) {
				System.out.println("ERROR OCCURED : " + error_code + " - " + errorcode_list.get(i).getName());
				return;
			}
		}
		System.out.println("ERROR CODE ERROR");
	}

	// when get comment count
	public void printGettingCommentCountError(String error_code, String av_number) {
		for (int i = 0; i != errorcode_list.size(); i++) {
			if (errorcode_list.get(i).getCode().equals(error_code)) {
				System.out.println(
						"ERROR OCCURED : " + error_code + " - " + errorcode_list.get(i).getName() + " - " + av_number);
				return;
			}
		}
		System.out.println("ERROR CODE ERROR");
	}

	// when fetching url
	public void printErrorWith_avNumber_pageNumber(String error_code, String av_number, String page_number) {
		for (int i = 0; i != errorcode_list.size(); i++) {
			if (errorcode_list.get(i).getCode().equals(error_code)) {
				System.out.println("ERROR OCCURED : " + error_code + " - " + errorcode_list.get(i).getName() + " - "
						+ av_number + " - " + page_number);
				return;
			}
		}
		System.out.println("ERROR CODE ERROR");
	}

	// when coverting comment to jsonObject
	public void printCommentToJsonObjectError(String error_code, String username, String comment_string) {
		for (int i = 0; i != errorcode_list.size(); i++) {
			if (errorcode_list.get(i).getCode().equals(error_code)) {
				System.out.println("ERROR OCCURED : " + error_code + " - " + errorcode_list.get(i).getName() + " - "
						+ username + " \n " + comment_string);
				return;
			}
		}
		System.out.println("ERROR CODE ERROR");
	}

	// when avobject to jsonobject error
	public void printAVObjectToJsonObjectError(String error_code, String av_number, String name) {
		for (int i = 0; i != errorcode_list.size(); i++) {
			if (errorcode_list.get(i).getCode().equals(error_code)) {
				System.out.println("ERROR OCCURED : " + error_code + " - " + errorcode_list.get(i).getName() + " - "
						+ av_number + " - " + name);
				return;
			}
		}
		System.out.println("ERROR CODE ERROR");
	}

}

class XErrorCodePair {
	private String code;
	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public XErrorCodePair(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

}
