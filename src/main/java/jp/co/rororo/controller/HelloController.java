package jp.co.rororo.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import jp.co.rororo.service.RedisEx;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
@RequestMapping("home")
public class HelloController {
	
	
	@Autowired
	private RedisEx redis;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String viewHome(HttpServletRequest request) {
		for (Cookie cookie : request.getCookies()) {
			log.info("cookie name: {}.", cookie.getName());
		}
		log.info("login as {}.", request.getRemoteUser());

		redis.save();
		log.info("zrange: {}", redis.get());
		return "home";
	}
}
