package jp.co.rororo.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import jp.co.rororo.entity.User;
import jp.co.rororo.form.UserForm;
import jp.co.rororo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService extends BaseService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User findUser(Long userId) {
		return userRepository.findOne(userId);
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	@Transactional
	public User saveUser(UserForm userForm) {
		User user = new User();
		String encodedPassword = passwordEncoder.encode(userForm.getPassword());
		userForm.setPassword(encodedPassword);
		BeanUtils.copyProperties(userForm, user);
		user.setSystemColums();
		User newUser = userRepository.save(user);
		log.info("newUser: {}", newUser);
		return newUser;
	}
	
	@Transactional
	public User updateUser(Long userId, UserForm userForm) {
		User user = userRepository.findOne(userId);
		BeanUtils.copyProperties(userForm, user);
		user.setUpdateAt(new Date());
		return userRepository.save(user);
	}
	
}
