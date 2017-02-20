package com.jizhuomi.surveypark.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class App {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer buffer = new StringBuffer();
		char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
		String src = "kaolv";
		byte[] bytes = src.getBytes();
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] targ = md.digest(bytes);
		for (byte b : targ) {
			buffer.append(chars[(b >> 4) & 0x0f]);
			buffer.append(chars[b & 0x0f]);
		}
		System.out.println(buffer.toString());
	}

}
