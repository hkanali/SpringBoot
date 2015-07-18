package jp.co.rororo.controller;

import jp.co.rororo.form.UserForm;
import jp.co.rororo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String viewEntry(@ModelAttribute UserForm userform) {
		return "user/new";
	}

	@RequestMapping(value = "{userId:[0-9]+}", method = RequestMethod.GET)
	public String viewEdit(@ModelAttribute UserForm userform, @PathVariable("userId") Integer userId, Model model) {
		model.addAttribute("user", userService.findUser(userId));
		return "user/update";
	}
}
