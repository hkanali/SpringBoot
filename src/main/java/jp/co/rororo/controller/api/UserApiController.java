package jp.co.rororo.controller.api;

import java.util.List;

import javax.validation.Valid;

import jp.co.rororo.entity.User;
import jp.co.rororo.form.UserForm;
import jp.co.rororo.service.UserService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "api/user")
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "{userId:[0-9]+}", method = RequestMethod.GET)
	public User getOne(@PathVariable("userId") Long userId) {
		User user = userService.findUser(userId);
		if (user == null) {
			log.info("user(userId={}) does not exist.", userId);
		}
		return user;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<User> getAll() {
		List<User> users = userService.findAll();
		if (users == null || users.isEmpty()) {
			log.info("there is no user.");
		}
		return users;
	}

	@RequestMapping(value = "new", method = RequestMethod.POST)
	public User save(@Valid UserForm userForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			log.info("validation error");
			return null;
		}
		return userService.saveUser(userForm);
	}

	@RequestMapping(value = "{userId:[0-9]+}", method = RequestMethod.POST) //TODO 本当はPUTがいいな〜
	public User insert(@Valid UserForm userForm, @PathVariable("userId") Long userId, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			log.info("validation error");
			return null;
		}
		return userService.updateUser(userId, userForm);
	}
}
