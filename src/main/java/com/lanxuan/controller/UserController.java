package com.lanxuan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lanxuan.pub.entity.system.User;
import com.lanxuan.service.UserManager;
import com.lanxuan.service.UserService;
/**
 * ”√ªßøÿ÷∆
 * @author lanxuan
 *
 */
@Controller
@RequestMapping("/platform/user/")
public class UserController {
	
	private static String ACTION_PATH = "/platform/";
	
	@Autowired
	public UserManager userManager;
	
	@Autowired
	public UserService userService;
	
	@RequestMapping("index")
	public String index(Model model){
		String userName = "";
		User user = userManager.get(1L);
		if(user != null){
			userName = user.getUserName();
		}
		model.addAttribute("userName", userName);
		return ACTION_PATH +"listUser";
	}
	
	
	@RequestMapping("show")
	public String show(Model model){
		String userName = "";
		User user = userService.querySimpleUserById(2L);
		if(user != null){
			userName = user.getUserName();
		}
		model.addAttribute("userName", userName);
		return ACTION_PATH +"listUser";
	}
}
