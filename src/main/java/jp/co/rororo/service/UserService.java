package jp.co.rororo.service;

import java.util.Date;
import java.util.List;

import jp.co.rororo.entity.User;
import jp.co.rororo.form.UserForm;
import jp.co.rororo.repository.UserRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService extends BaseService {
	
	@Autowired UserRepository userRepository;
	
	public User findUser(Integer userId) {
		return userRepository.findOne(userId);
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User saveUser(UserForm userForm) {
		User user = new User();
		BeanUtils.copyProperties(userForm, user);
		user.setSystemColums();
		return userRepository.save(user);
	}
	
	public User updateUser(Integer userId, UserForm userForm) {
		User user = userRepository.findOne(userId);
		BeanUtils.copyProperties(userForm, user);
		user.setUpdateAt(new Date());
		return userRepository.save(user);
	}
	
}
