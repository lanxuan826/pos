 package com.lanxuan.pub.vo;
 
 import java.util.List;
 
 public class QueryResult
 {
   private List results;
   private int maxPage;
   private int size;
 
   public List getResults()
   {
     return this.results;
   }
 
   public void setResults(List results) {
     this.results = results;
   }
 
   public int getMaxPage() {
     return this.maxPage;
   }
 
   public void setMaxPage(int maxPage) {
     this.maxPage = maxPage;
   }
 
   public int getSize() {
     return this.size;
   }
 
   public void setSize(int size) {
     this.size = size;
   }
 }

