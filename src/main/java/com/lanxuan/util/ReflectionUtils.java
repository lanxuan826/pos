package com.lanxuan.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ReflectionUtils {
	
	public static<T> Class<T> getSuperClassGenricType(Class clazz){
		return getSuperClassGenricType(clazz, 0);
	}
	
	public static Class getSuperClassGenricType(Class clazz, int index){
		Type genType = clazz.getGenericSuperclass();
		if( !(genType instanceof ParameterizedType)){
			/*loger.warn*/
			return Object.class;
		}
		Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
		if((index >= params.length) || index < 0 ){
//			logger.warnn
			return Object.class;
		}
		
		if(!(params[index] instanceof Class)){
//			logger.wa
			return Object.class;
		}
		return (Class) params[index];
	}
}
