package jp.co.rororo.service;

import java.util.Set;

import jp.co.rororo.entity.BaseEntity;
import jp.co.rororo.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisEx {
	
	@Autowired
	private RedisTemplate<String, BaseEntity> template;
	
	public void save() {
		User user = new User();
		user.setBio("よろしく");
		template.opsForZSet().add("key", user, 1);
	}
	
	public Set<BaseEntity> get() {
		return template.opsForZSet().range("key", 0, 100);
	}
	
}
