 package com.lanxuan.pub.base.entity;
 
 public class BasePagingEntity
 {
   protected Integer limit = Integer.valueOf(10);
 
   protected Integer offset = Integer.valueOf(0);
 
   public Integer getCurrentPage() {
     return Integer.valueOf(this.offset.intValue() / this.limit.intValue() + 1);
   }
 
   public Integer getLimit() {
     return this.limit;
   }
 
   public void setLimit(Integer limit) {
     this.limit = limit;
   }
 
   public Integer getOffset() {
     return this.offset;
   }
 
   public void setOffset(Integer offset) {
     this.offset = offset;
   }
 }

