package jp.co.rororo.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
@RequestMapping("home")
public class HelloController {
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String viewHome(HttpServletRequest request) {
		for (Cookie cookie : request.getCookies()) {
			log.info("cookie name: {}.", cookie.getName());
		}
		log.info("login as {}.", request.getRemoteUser());
		return "home";
	}
}
