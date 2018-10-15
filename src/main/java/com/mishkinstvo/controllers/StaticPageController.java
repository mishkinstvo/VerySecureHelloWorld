package com.mishkinstvo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StaticPageController {

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(path = "/helloWorld", method = RequestMethod.GET)
	public String helloWorld() {
		return "helloWorld";
	}
}
