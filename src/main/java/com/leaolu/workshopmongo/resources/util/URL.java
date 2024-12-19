package com.leaolu.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class URL {
	//decodes the URL in the web pattern, if the text isn't in this pattern, it returns an empty string
	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	//converts the String into Date, if the String isn't in the SimpleDateFormat pattern, it will result as a ParseException, 
	//so it will return the defaultValue, that is the second parameter
	public static Date convertDate(String textDate, Date defaultValue) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
			return sdf.parse(textDate);
		}catch(ParseException e) {
			return defaultValue;
		}
	}
}
