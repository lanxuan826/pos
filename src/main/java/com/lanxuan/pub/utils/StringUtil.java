package com.lanxuan.pub.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class StringUtil extends StringUtils {
	
	public static int parseString2Int(String str){
		int result = 0;
		if((StringUtils.isNotEmpty(str)) && isNumeric(str)){
			result = Integer.valueOf(str).intValue();
		}
		return result;
		
	}
		
	public static Long parseString2Long(String str){
		Long result = null;
		if((StringUtils.isNotEmpty(str)) &&isNumeric(str) ){
			result = Long.valueOf(str).longValue();
		}
		return result;
	}
	
	public static double parseString2Double(String str){
		double result = 0.0d;
		if((StringUtils.isNotEmpty(str)) &&isNumeric(str) ){
			result = Double.valueOf(str).doubleValue();
		}
		return result;
	}
	
	public static String parseAny2String(Object obj) {
	     String result = "";
	     if (obj != null) {
	       result = String.valueOf(obj);
	     }
	     return result;
	   }
	
	public static int parseAny2Int(Object obj) {
	     int result = 0;
	     String str = parseAny2String(obj);
	     if (str.length() > 10) {
	       str = str.substring(0, 10);
	     }
	     if ((StringUtils.isNotEmpty(str)) && (isNumeric(str))) {
	       result = Integer.valueOf(str).intValue();
	     }
	     return result;
	   }
	
	public static boolean isNotBlank(Object obj) {
	     boolean isNotBlank = false;
	     if ((obj != null) && (!"".equals(obj))) {
	       isNotBlank = true;
	     }
	     return isNotBlank;
	   }
	
	public static boolean isEmpty(String str){
		return (str == null) || (str.trim().equals("")) ||
				(str.trim().equals("null"));
	}
	
	public static boolean isNumeric(String str) {
	     if (str.indexOf(".") >= 0)
	       str = str.replace(".", "");
	     if (str.indexOf("-") >= 0) {
	       str = str.replace("-", "");
	     }
	     return StringUtils.isNumeric(str);
	   }
	
	   public static String parseArray2String(String[] array) {
		   
		   String result = "";
		   if((array != null) && array.length > 0){
			   result = ArrayUtils.toString(array);
			   result = result.substring(1, result.length() - 1);
		   }
		   return result;
	   }
	
	   public static String formatLetterOrDigit(String str) {
		   if (StringUtils.isBlank(str)) {
		       return null;
		     }
		   StringBuffer sb = new StringBuffer(str.length());
		   for (int i = 0; i < str.length(); i++) {
			   char c = str.charAt(i);
			   if(Character.isLetterOrDigit(c)){
				   sb.append(c);
			   }
		   }
		   return sb.toString();
	   }
	
}
