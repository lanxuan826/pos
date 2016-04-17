package com.lanxuan.pub.base;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@MappedSuperclass
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@DynamicInsert
@DynamicUpdate
public class BaseEntity {
	
		protected Long id;
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}
		
		
		
}
