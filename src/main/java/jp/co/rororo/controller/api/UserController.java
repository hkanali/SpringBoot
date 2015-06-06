package jp.co.rororo.controller.api;

import java.util.List;

import jp.co.rororo.entity.User;
import jp.co.rororo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "api/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "{userId:[0-9]+}", method = RequestMethod.GET)
	public User getOne(@PathVariable("userId") Integer userId) {
		User user = userRepository.findOne(userId);
		if (user == null) {
			log.info("user(userId={}) does not exist.", userId);
		}
		;
		return user;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<User> getAll() {
		List<User> users = userRepository.findAll();
		if (users == null || users.isEmpty()) {
			log.info("there is no user.");
		}
		;
		return users;
	}
	
}
