package jp.co.rororo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("home")
public class HelloController {
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String viewHome(HttpServletRequest request) {
		log.info("login as {}.", request.getRemoteUser());
		return "home";
	}
}
