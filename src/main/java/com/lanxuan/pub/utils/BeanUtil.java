package com.lanxuan.pub.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ArrayUtils;


public class BeanUtil extends BeanUtils{
	
	   public static Object getFieldByMethodName(Object obj, String methodName){
		   Class c = obj.getClass();
		   Method[] methods = c.getMethods();
		   List methodList = ReflectionUtils.convertElementPropertyToList(Arrays.asList(methods), "name");
		   Object value = null;
		   for(Method method : methods){
			   try{
				   if((methodList.contains(methodName)) && 
						   methodName.equals(method.getName())){
					   value = method.invoke(obj, new Object[0]);
				   }
			   }catch (IllegalArgumentException e) {
			         e.printStackTrace();
		       } catch (IllegalAccessException e) {
		         e.printStackTrace();
		       } catch (SecurityException e) {
		         e.printStackTrace();
		       } catch (InvocationTargetException e) {
		         e.printStackTrace();
		       }
		     }
		     return value;
		   }
	   
	   public static void checkEmpty2Null(Object obj)
	   {
	     Class c = obj.getClass();
	     Method[] methods = c.getMethods();
	     List methodNames = ReflectionUtils.convertElementPropertyToList(
	       Arrays.asList(methods), "name");
	     for (Method method : methods)
	       try {
	         String methodName = method.getName();
	         if (methodName.startsWith("get")) {
	           Object value = method.invoke(obj, new Object[0]);
	           if ((value != null) && (value.equals(""))) {
	             methodName = methodName.replace("get", "set");
	             if (methodNames.contains(methodName)) {
	               method = c.getMethod(methodName, new Class[] { String.class });
	               method.invoke(obj, new Object[1]);
	             }
	           }
	         }
	       } catch (IllegalArgumentException e) {
	         e.printStackTrace();
	       } catch (IllegalAccessException e) {
	         e.printStackTrace();
	       } catch (SecurityException e) {
	         e.printStackTrace();
	       } catch (NoSuchMethodException e) {
	         e.printStackTrace();
	       } catch (InvocationTargetException e) {
	         e.printStackTrace();
	       }
	   }
	   
	   
	   public static boolean checkAllFieldNull(Object obj)
	   {
	     boolean isAllFieldNull = true;
	     Class c = obj.getClass();
	     Method[] methods = c.getMethods();
	     for (Method method : methods) {
	       try {
	         String methodName = method.getName();
	         if ((methodName.startsWith("get")) && (!"getClass".equals(methodName))) {
	           Object value = method.invoke(obj, new Object[0]);
	           if (value != null)
	             isAllFieldNull = false;
	         }
	       }
	       catch (IllegalArgumentException e)
	       {
	         e.printStackTrace();
	       } catch (IllegalAccessException e) {
	         e.printStackTrace();
	       } catch (SecurityException e) {
	         e.printStackTrace();
	       } catch (InvocationTargetException e) {
	         e.printStackTrace();
	       }
	     }
	     return isAllFieldNull;
	   }
	 
	   public static void checkEmpty2Null(Map<String, Object> map) {
		     Iterator iterator = map.keySet().iterator(); 
		     while (iterator.hasNext()) {
		       String key = (String)iterator.next();
		       Object value = map.get(key);
		       if ((value != null) && ("".equals(value)))
		         map.put(key, null);
		     }
		   }
		 
	   
	   
}
