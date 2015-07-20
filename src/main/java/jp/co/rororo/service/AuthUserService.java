package jp.co.rororo.service;

import java.util.ArrayList;
import java.util.List;

import jp.co.rororo.entity.UserAuthority;
import jp.co.rororo.repository.UserAuthorityRepository;
import jp.co.rororo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService extends BaseService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserAuthorityRepository userAuthorityRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		jp.co.rororo.entity.User user = userRepository.findByName(username);
		List<UserAuthority> authorities = userAuthorityRepository.findByUserId(user.getId());
		
		List<GrantedAuthority> list = new ArrayList<>();
		authorities.stream().forEach(o -> {
			list.add(new SimpleGrantedAuthority(o.getAuthorityType().toStringWithPrefix()));
		});
		
		return new User(username, user.getPassword(), list);
	}
	
}
