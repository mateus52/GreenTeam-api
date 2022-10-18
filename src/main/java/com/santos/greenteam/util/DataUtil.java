package com.santos.greenteam.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtil {

	public static Date formataStringEmDate(String date) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		
		
		try {
			Date data = formatter.parse(date);
			
			return data;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	public static String formataDateEmString(Date date, String mask) {
		DateFormat out = new SimpleDateFormat(mask);  
		   
		String result = out.format(date);
		
		return result;
	}
}
