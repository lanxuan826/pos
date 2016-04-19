package com.lanxuan.pub.base.repository.mybatis;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.lanxuan.pub.base.entity.BaseEntity;
import com.lanxuan.pub.base.entity.BasePagingEntity;

@MyBatisRepository
public abstract interface MybatisDao<T, PK extends Serializable>
{
  public abstract List<T> queryForList(Map<String, ? extends Object> paramMap, RowBounds paramRowBounds);

  public abstract int queryForCount(Map<String, ? extends Object> paramMap);

  public abstract List<T> queryForList(BaseEntity paramBaseEntity, RowBounds paramRowBounds);

  public abstract int queryForCount(BaseEntity paramBaseEntity);

  public abstract List<T> queryForList(BasePagingEntity paramBasePagingEntity, RowBounds paramRowBounds);

  public abstract int queryForCount(BasePagingEntity paramBasePagingEntity);

  public abstract List<T> queryForList(BaseEntity paramBaseEntity);

  public abstract List<T> queryForList(BasePagingEntity paramBasePagingEntity);

  public abstract List<T> queryForList(Map<String, ? extends Object> paramMap);

  public abstract T queryForObject(Map<String, ? extends Object> paramMap);

  public abstract T queryForObject(BaseEntity paramBaseEntity);

  public abstract T queryForObject(BasePagingEntity paramBasePagingEntity);

  public abstract int checkForExist(Map<String, ? extends Object> paramMap);
}

