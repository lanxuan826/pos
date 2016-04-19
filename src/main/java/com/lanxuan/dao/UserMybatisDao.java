package com.lanxuan.dao;

import java.util.Map;


import com.lanxuan.pub.base.repository.mybatis.MyBatisRepository;
import com.lanxuan.pub.base.repository.mybatis.MybatisDao;
import com.lanxuan.pub.entity.system.User;

@MyBatisRepository
public abstract interface UserMybatisDao extends MybatisDao<User, Long> {
	
	 public abstract User queryForUserInfo(Map<String, String> paramMap);

}
