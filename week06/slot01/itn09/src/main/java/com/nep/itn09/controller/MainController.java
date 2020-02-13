package com.nep.itn09.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@RequestMapping(value="/")
	@ResponseBody
	public String index() {
		return "<h1>Hello</h1>";
	}
	
	@RequestMapping(value="/home")
	@ResponseBody
	public String home() {
		return "<h1>World</h1>";
	}
	
}
