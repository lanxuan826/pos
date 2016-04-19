package com.lanxuan.pub.base.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.session.RowBounds;
import org.springframework.transaction.annotation.Transactional;

import com.lanxuan.pub.base.entity.BaseEntity;
import com.lanxuan.pub.base.entity.BasePagingEntity;
import com.lanxuan.pub.base.repository.mybatis.MybatisDao;
import com.lanxuan.pub.utils.BeanUtil;
import com.lanxuan.pub.vo.QueryResult;

@Transactional
public class QueryService<T, PK extends Serializable> {

	private int pageSize;

	private MybatisDao<T, PK> mybatisDao;

	public QueryService() {

	}

	public QueryService(MybatisDao<T, PK> mybatisDao) {
		this.mybatisDao = mybatisDao;
	}

	public MybatisDao<T, PK> getMybatisDao() {
		return mybatisDao;
	}

	@Transactional(readOnly = true)
	public boolean check(String property, String parameter) {
		boolean isExist = false;
		Map parameters = new HashedMap();
		parameters.put("property", property);
		parameters.put("parameters", parameter);
		int count = getMybatisDao().checkForExist(parameters);
		if (count > 0) {
			isExist = true;
		}
		return isExist;
	}

	@Transactional(readOnly = true)
	public T get(String property, String parameter) {
		Map parameters = new HashMap();
		parameters.put("property", property);
		parameters.put("parameter", parameter);
		return getMybatisDao().queryForObject(parameters);
	}

	@Transactional(readOnly = true)
	public List<T> search(BaseEntity parameters) {
		BeanUtil.checkEmpty2Null(parameters);
		return getMybatisDao().queryForList(parameters);
	}
	
	   @Transactional(readOnly=true)
	   public List<T> search(BasePagingEntity parameters) {
	     BeanUtil.checkEmpty2Null(parameters);
	     return getMybatisDao().queryForList(parameters);
	   }
	 
	   @Transactional(readOnly=true)
	   public List<T> search(Map<String, Object> parameters) {
	     BeanUtil.checkEmpty2Null(parameters);
	     return getMybatisDao().queryForList(parameters);
	   }
	 
	   @Transactional(readOnly=true)
	   public List<T> search(int startingIndex, Map<String, Object> parameters) {
	     BeanUtil.checkEmpty2Null(parameters);
	     return getMybatisDao().queryForList(parameters, 
	       new RowBounds(startingIndex, this.pageSize));
	   }
	 
	   @Transactional(readOnly=true)
	   public List<T> search(int startingIndex, BaseEntity parameters) {
	     BeanUtil.checkEmpty2Null(parameters);
	     return getMybatisDao().queryForList(parameters, 
	       new RowBounds(startingIndex, this.pageSize));
	   }
	 
	   @Transactional(readOnly=true)
	   public List<T> search(int startingIndex, BasePagingEntity parameters) {
	     BeanUtil.checkEmpty2Null(parameters);
	     return getMybatisDao().queryForList(parameters, 
	       new RowBounds(startingIndex, this.pageSize));
	   }
	 
	   @Transactional(readOnly=true)
	   public QueryResult query(int currentPage, Map<String, Object> parameters) {
	     BeanUtil.checkEmpty2Null(parameters);
	     QueryResult queryResult = new QueryResult();
	     int startingIndex = (currentPage - 1) * this.pageSize;
	     queryResult.setResults(getMybatisDao().queryForList(parameters, 
	       new RowBounds(startingIndex, this.pageSize)));
	     int size = getMybatisDao().queryForCount(parameters);
	     int maxPage = size % this.pageSize != 0 ? size / this.pageSize + 1 : size / 
	       this.pageSize;
	     queryResult.setSize(size);
	 
	     queryResult.setMaxPage(maxPage);
	     return queryResult;
	   }
	 
	   @Transactional(readOnly=true)
	   public QueryResult query(int currentPage, BaseEntity parameters) {
	     BeanUtil.checkEmpty2Null(parameters);
	     QueryResult queryResult = new QueryResult();
	     int startingIndex = (currentPage - 1) * this.pageSize;
	     queryResult.setResults(getMybatisDao().queryForList(parameters, 
	       new RowBounds(startingIndex, this.pageSize)));
	     int size = getMybatisDao().queryForCount(parameters);
	     int maxPage = size % this.pageSize != 0 ? size / this.pageSize + 1 : size / 
	       this.pageSize;
	     queryResult.setSize(size);
	 
	     queryResult.setMaxPage(maxPage);
	     return queryResult;
	   }
	 
	   @Transactional(readOnly=true)
	   public QueryResult query(int currentPage, BasePagingEntity parameters) {
	     BeanUtil.checkEmpty2Null(parameters);
	     QueryResult queryResult = new QueryResult();
	     int startingIndex = (currentPage - 1) * this.pageSize;
	     queryResult.setResults(getMybatisDao().queryForList(parameters, 
	       new RowBounds(startingIndex, this.pageSize)));
	     int size = getMybatisDao().queryForCount(parameters);
	     int maxPage = size % this.pageSize != 0 ? size / this.pageSize + 1 : size / 
	       this.pageSize;
	     queryResult.setSize(size);
	 
	     queryResult.setMaxPage(maxPage);
	     return queryResult;
	   }
	 
	   public int getPageSize() {
	     return this.pageSize;
	   }
	 
	   public void setPageSize(int pageSize) {
	     this.pageSize = pageSize;
	   }
	   
}
