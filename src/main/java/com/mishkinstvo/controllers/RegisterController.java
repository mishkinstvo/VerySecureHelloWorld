package com.mishkinstvo.controllers;

import com.mishkinstvo.exceptions.DataValidationException;
import com.mishkinstvo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/register")
public class RegisterController {

	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "register";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String store(@RequestParam String login, @RequestParam String password,
							  RedirectAttributes redirectAttributes) {
		userService.persist(login, password);
		redirectAttributes.addFlashAttribute(LOGIN_MESSAGE_PARAM, LOGIN_MESSAGE_TEXT);
		return "redirect:/login";
	}

	@ExceptionHandler(DataValidationException.class)
	public String handleDataValidationException(HttpServletRequest request, DataValidationException exception,
															  RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute(ERROR_MESSAGE_PARAM, exception.getMessage());
		redirectAttributes.addFlashAttribute(LOGIN_PARAM, request.getParameter(LOGIN_PARAM));
		redirectAttributes.addFlashAttribute(PASSWORD_PARAM, request.getParameter(PASSWORD_PARAM));
		return "redirect:/register";
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {
		return userService;
	}

	public static final String LOGIN_PARAM = "login";
	public static final String PASSWORD_PARAM = "password";
	public static final String LOGIN_MESSAGE_PARAM = "loginMessage";
	public static final String ERROR_MESSAGE_PARAM = "errorMessage";
	public static final String LOGIN_MESSAGE_TEXT = "You have successfully created a new account! Now you can log in.";
}
