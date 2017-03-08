package com.jizhuomi.surveypark.util;

import java.util.Collection;

public class ValidateUtil {
	/**
	 * 判断字符串有效性 
	 */
	public static boolean isValid(String src) {
		return !(src == null || "".equals(src.trim()));
	}
	
	/**
	 * 判断集合的有效性
	 */
	public static boolean isValid(Collection col) {
		return !(col == null || col.isEmpty());
	}
}
