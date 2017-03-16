package com.jizhuomi.surveypark.util;

public class StringUtil {
	public static String[] str2Arr(String str, String tag) {
		if (ValidateUtil.isValid(str)) {
			return str.split(tag);
		}
		return null;
	}
}
