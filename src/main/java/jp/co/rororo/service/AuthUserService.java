package jp.co.rororo.service;

import jp.co.rororo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
public class AuthUserService extends BaseService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		jp.co.rororo.entity.User user = userRepository.findByName(username);
		
		return new User(username, user.getPassword(), Lists.newArrayList(new SimpleGrantedAuthority("role1")));
	}
	
}
