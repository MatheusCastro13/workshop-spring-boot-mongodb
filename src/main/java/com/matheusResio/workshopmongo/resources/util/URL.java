package com.matheusResio.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

public class URL {

	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		}catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	public static Instant convertDate(String textDate, Instant defaultValue) {
		return Instant.parse(textDate);		
	}
}
