 package com.lanxuan.pub.utils;
 
 import java.lang.annotation.Annotation;
 import java.lang.reflect.Field;
 import java.lang.reflect.InvocationTargetException;
 import java.lang.reflect.Method;
 import java.lang.reflect.ParameterizedType;
 import java.lang.reflect.Type;
 import java.util.ArrayList;
 import java.util.Collection;
 import java.util.Date;
 import java.util.Iterator;
 import java.util.List;
 import org.apache.commons.beanutils.ConvertUtils;
 import org.apache.commons.beanutils.PropertyUtils;
 import org.apache.commons.beanutils.converters.DateConverter;
 import org.apache.commons.lang3.StringUtils;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.util.Assert;
 
 public class ReflectionUtils
 {
   private static Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);
 
   static {
     DateConverter dc = new DateConverter();
     dc.setUseLocaleFormat(true);
     dc.setPatterns(new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" });
     ConvertUtils.register(dc, Date.class);
   }
 
   public static void invokeLoaderMethod(Object obj, String propertyName)
   {
     String loaderMethodName = "load" + StringUtils.capitalize(propertyName);
     invokeMethod(obj, loaderMethodName, new Class[0], new Object[0]);
   }
 
   public static Object invokeGetterMethod(Object obj, String propertyName)
   {
     String getterMethodName = "get" + StringUtils.capitalize(propertyName);
     return invokeMethod(obj, getterMethodName, new Class[0], new Object[0]);
   }
 
   public static void invokeSetterMethod(Object obj, String propertyName, Object value)
   {
     invokeSetterMethod(obj, propertyName, value, null);
   }
 
   public static void invokeSetterMethod(Object obj, String propertyName, Object value, Class<?> propertyType)
   {
     Class type = propertyType != null ? propertyType : value.getClass();
     String setterMethodName = "set" + StringUtils.capitalize(propertyName);
     invokeMethod(obj, setterMethodName, new Class[] { type }, new Object[] { value });
   }
 
   public static Object getFieldValue(Object obj, String fieldName)
   {
     Field field = getAccessibleField(obj, fieldName);
 
     if (field == null) {
       throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
     }
 
     Object result = null;
     try {
       result = field.get(obj);
     } catch (IllegalAccessException e) {
       logger.error("�������׳����쳣{}", e.getMessage());
     }
     return result;
   }
 
   public static void setFieldValue(Object obj, String fieldName, Object value)
   {
     Field field = getAccessibleField(obj, fieldName);
 
     if (field == null) {
       throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
     }
     try
     {
       field.set(obj, value);
     } catch (IllegalAccessException e) {
       logger.error("�������׳����쳣:{}", e.getMessage());
     }
   }
 
   public static Field getAccessibleField(Object obj, String fieldName)
   {
     Assert.notNull(obj, "object����Ϊ��");
     Assert.hasText(fieldName, "fieldName");
     for (Class superClass = obj.getClass(); superClass != Object.class; ) {
       try {
         Field field = superClass.getDeclaredField(fieldName);
         field.setAccessible(true);
         return field;
       }
       catch (NoSuchFieldException localNoSuchFieldException)
       {
         superClass = superClass.getSuperclass();
       }
 
     }
 
     return null;
   }
 
   public static Object invokeMethod(Object obj, String methodName, Class<?>[] parameterTypes, Object[] args)
   {
     Method method = getAccessibleMethod(obj, methodName, parameterTypes);
     if (method == null) {
       throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + obj + "]");
     }
     try
     {
       return method.invoke(obj, args);
     } catch (Exception e) {
       throw convertReflectionExceptionToUnchecked(e);
     }
   }
 
   public static Method getAccessibleMethod(Object obj, String methodName, Class<?>[] parameterTypes)
   {
     Assert.notNull(obj, "object����Ϊ��");
 
     for (Class superClass = obj.getClass(); superClass != Object.class; ) {
       try {
         Method method = superClass.getDeclaredMethod(methodName, parameterTypes);
 
         method.setAccessible(true);
 
         return method;
       }
       catch (NoSuchMethodException localNoSuchMethodException)
       {
         superClass = superClass.getSuperclass();
       }
 
     }
 
     return null;
   }
 
   public static <T> Class<T> getSuperClassGenricType(Class clazz)
   {
     return getSuperClassGenricType(clazz, 0);
   }
 
   public static Class getSuperClassGenricType(Class clazz, int index)
   {
     Type genType = clazz.getGenericSuperclass();
 
     if (!(genType instanceof ParameterizedType)) {
       logger.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
       return Object.class;
     }
 
     Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
 
     if ((index >= params.length) || (index < 0)) {
       logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: " + 
         params.length);
       return Object.class;
     }
     if (!(params[index] instanceof Class)) {
       logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
       return Object.class;
     }
 
     return (Class)params[index];
   }
 
   public static List convertElementPropertyToList(Collection collection, String propertyName)
   {
     List list = new ArrayList();
     try
     {
       for (Iterator localIterator = collection.iterator(); localIterator.hasNext(); ) { Object obj = localIterator.next();
         list.add(PropertyUtils.getProperty(obj, propertyName)); }
     }
     catch (Exception e) {
       throw convertReflectionExceptionToUnchecked(e);
     }
 
     return list;
   }
 
   public static String convertElementPropertyToString(Collection collection, String propertyName, String separator)
   {
     List list = convertElementPropertyToList(collection, propertyName);
     return StringUtils.join(list, separator);
   }
 
   public static Object convertStringToObject(String value, Class<?> toType)
   {
     try
     {
       return ConvertUtils.convert(value, toType);
     } catch (Exception e) {
       throw convertReflectionExceptionToUnchecked(e);
     }
   }
 
   public static RuntimeException convertReflectionExceptionToUnchecked(Exception e)
   {
     if (((e instanceof IllegalAccessException)) || ((e instanceof IllegalArgumentException)) || 
       ((e instanceof NoSuchMethodException)))
       return new IllegalArgumentException("Reflection Exception.", e);
     if ((e instanceof InvocationTargetException))
       return new RuntimeException("Reflection Exception.", ((InvocationTargetException)e).getTargetException());
     if ((e instanceof RuntimeException)) {
       return (RuntimeException)e;
     }
     return new RuntimeException("Unexpected Checked Exception.", e);
   }
 
   public static <T extends Annotation> List<T> getObjectAnnotations(Class<? extends Object> objClass, Class<? extends Annotation> annClass)
   {
     List objectAnnotations = new ArrayList();
     try {
       for (Method method : objClass.getMethods()) {
         String methodName = method.getName();
         if (methodName.startsWith("get")) {
           Annotation anno = method.getAnnotation(annClass);
           if (anno != null)
             objectAnnotations.add(anno);
         }
       }
     }
     catch (Exception e) {
       throw convertReflectionExceptionToUnchecked(e);
     }
     return objectAnnotations;
   }
 }

