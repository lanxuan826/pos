package com.lanxuan.service;

 import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanxuan.dao.UserMybatisDao;
import com.lanxuan.pub.base.repository.mybatis.MybatisDao;
import com.lanxuan.pub.base.service.QueryService;
import com.lanxuan.pub.entity.system.User;
import com.lanxuan.pub.utils.StringUtil;
import com.lanxuan.pub.vo.QueryResult;
 
 @Service
 public class UserService extends QueryService<User, Long>
 {
 
   @Autowired
   private UserMybatisDao userMybatisDao;
 
   public QueryResult queryPageUsers(int currentPage, User criteria)
   {
     return super.query(currentPage, criteria);
   }
 
   public User querySimpleUserById(Long userId) {
     Map<String, String> parameters = new HashMap<String, String>();
     parameters.put("property", "ID");
     parameters.put("parameter", StringUtil.parseAny2String(userId));
     return this.userMybatisDao.queryForUserInfo(parameters);
   }
// 
   public User queryUserById(Long userId) {
     return (User)super.get("ID", StringUtil.parseAny2String(userId));
   }
// 
//   public int saveUserConcern(Long userId, Long concernId) {
//     int count = this.userMybatisDao.checkUserConcern(userId, concernId);
//     if (count > 0) {
//       return 3;
//     }
//     this.userMybatisDao.saveUserConcern(userId, concernId);
//     return 99;
//   }
// 
//   public void deleteUserConcern(Long userId, Long concernId) {
//     this.userMybatisDao.deleteUserConcern(userId, concernId);
//   }
// 
//   public User findByLoginName(String loginName)
//   {
//     return (User)get("LOGIN_NAME", loginName);
//   }
// 
   public  MybatisDao<User, Long> getMybatisDao()
   {
     return this.userMybatisDao;
   }
 }

