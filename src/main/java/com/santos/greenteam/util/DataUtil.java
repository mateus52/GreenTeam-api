package com.santos.greenteam.util;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DataUtil {

	public static Date formataStringEmDate(String date) {

			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			
			LocalDateTime localDateTime = LocalDateTime.parse(date, format); 
			
			Date data = Timestamp.valueOf(localDateTime);
			
			return data;
	}
	
	public static String formataDateEmString(Date date, String mask) {
		
		DateFormat out = new SimpleDateFormat(mask);  
		   
		String result = out.format(date);
		
		return result;
	}
}
