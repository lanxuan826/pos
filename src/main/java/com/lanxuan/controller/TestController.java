package com.lanxuan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system/welcome")
public class TestController {
	
	private static String ACTION_PATH = "";
	
	@RequestMapping(value="")
	public String index(Model model){
		return ACTION_PATH+"index";
	}
}
