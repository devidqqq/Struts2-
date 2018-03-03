/**
 * 
 */
package com.struts.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 10172
 *
 */
public class DateUtil {

	public static String formatDateToString(Date date, String format) {
		String formatDate = "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if(date!=null) {
			formatDate = sdf.format(date);
		}
		return formatDate;
	}
	
	public static Date formatStringToDate(String date,String format) {
		Date newDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if(StringUtil.isNotEmpty(date)) {
			try {
				newDate = sdf.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return newDate;
	}
	
	public static String getCurrentDate() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(now);
	}
}
