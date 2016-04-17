/*     */ package com.lanxuan.pub.orm;
/*     */ 
import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.PostConstruct;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.hibernate.cfg.ImprovedNamingStrategy;
/*     */ import org.hibernate.cfg.NamingStrategy;
/*     */ import org.hibernate.internal.util.StringHelper;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.core.io.Resource;
/*     */ import org.springframework.core.io.support.ResourcePatternResolver;
/*     */ import org.springframework.util.ClassUtils;
/*     */ 
/*     */ public class MyNamingStrategy extends ImprovedNamingStrategy
/*     */   implements NamingStrategy, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -4298964226329429548L;
/*  43 */   private static Logger logger = Logger.getLogger(MyNamingStrategy.class);
/*     */   private static final String RESOURCE_PATTERN = "/**/*.class";
/*     */ 
/*     */   @Autowired
/*     */   private ResourcePatternResolver resourcePatternResolver;
/*  50 */   private List<String> classFileNames = new ArrayList();
/*     */   private String[] packagesToScan;
/*     */ 
/*     */   public void setPackagesToScan(String[] packagesToScan)
/*     */   {
/*  55 */     this.packagesToScan = packagesToScan;
/*     */   }
/*     */ 
/*     */   public String columnName(String columnName)
/*     */   {
/*  60 */     return addUnderscores(columnName).toUpperCase();
/*     */   }
/*     */ 
/*     */   public String tableName(String tableName)
/*     */   {
/*  65 */     return addUnderscores(tableName).toUpperCase();
/*     */   }
/*     */ 
/*     */   public String classToTableName(String className)
/*     */   {
/*  70 */     String tableName = addUnderscores(StringHelper.unqualify(className)).toUpperCase();
/*  71 */     for (String fileName : this.classFileNames) {
/*  72 */       String name = obtainClassName(fileName);
/*  73 */       if (name.equals(className)) {
/*  74 */         if (fileName.contains("platform"))
/*  75 */           tableName = "T_E4S_DB_" + tableName;
/*  76 */         else if (fileName.contains("display"))
/*  77 */           tableName = "BZ_" + tableName;
/*  78 */         else if (fileName.contains("shoping")) {
/*  79 */           tableName = "SC_" + tableName;
/*     */         }
/*     */       }
/*     */     }
/*  83 */     logger.info("#############tableName=###########" + tableName);
/*     */ 
/*  85 */     return tableName;
/*     */   }
/*     */ 
/*     */   public String propertyToColumnName(String propertyName)
/*     */   {
/*  90 */     return addUnderscores(StringHelper.unqualify(propertyName)).toUpperCase();
/*     */   }
/*     */ 
/*     */   @PostConstruct
/*     */   private void scanEntityPackages() {
/*  95 */     for (String pkg : this.packagesToScan) {
/*  96 */       String pattern = "classpath*:" + 
/*  97 */         ClassUtils.convertClassNameToResourcePath(pkg) + "/**/*.class";
/*     */       try
/*     */       {
/* 100 */         Resource[] resources = this.resourcePatternResolver.getResources(pattern);
/*     */ 
/* 102 */         for (Resource resource : resources)
/* 103 */           if (resource.isReadable())
/*     */           {
/* 107 */             String name = resource.toString();
/* 108 */             String className = name.substring(name.indexOf("com"), name.length() - 1);
/* 109 */             this.classFileNames.add(className);
/*     */           }
/*     */       }
/*     */       catch (IOException e1)
/*     */       {
/* 114 */         e1.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private String obtainClassName(String fileName) {
/* 120 */     int loc1 = fileName.lastIndexOf("\\");
/* 121 */     if (loc1 == -1) {
/* 122 */       loc1 = fileName.lastIndexOf("/");
/*     */     }
/* 124 */     int loc2 = fileName.lastIndexOf(".");
/* 125 */     return fileName.substring(loc1 + 1, loc2);
/*     */   }
/*     */ }

/* Location:           D:\BF\桌面\e4s-os-1.6.0-SNAPSHOT.jar
 * Qualified Name:     com.e4s.pub.orm.MyNamingStrategy
 * JD-Core Version:    0.6.2
 */