package com.jizhuomi.surveypark.util;

public class StringUtil {
	public static String[] str2Arr(String str, String tag) {
		if (ValidateUtil.isValid(str)) {
			return str.split(tag);
		}
		return null;
	}

	public static boolean contains(String[] values, String value) {
		// TODO Auto-generated method stub
		if (ValidateUtil.isValid(values)) {
			for (String s : values) {
				if (s.equals(value)) {
					return true;
				}
			}
		}
		return false;
	}

	public static String arr2Str(String[] value) {
		// TODO Auto-generated method stub
		String temp = "";
		if (ValidateUtil.isValid(value)) {
			for (String s : value) {
				temp += s + ",";
			}
			return temp.substring(0, temp.length() - 1);
		}
		return temp;
	}
}
