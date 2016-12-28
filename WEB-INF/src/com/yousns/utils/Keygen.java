package com.yousns.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Keygen {
	public static String generateKey(){
		String key = null;
		Date current = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);   
		key =f.format(current);
		Random rand =new Random();
		key+=Integer.toString(rand.nextInt(9));
		key+=Integer.toString(rand.nextInt(9));
		key+=Integer.toString(rand.nextInt(9));
		return key;

	}
}
