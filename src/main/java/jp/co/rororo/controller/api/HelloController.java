package jp.co.rororo.controller.api;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "api/hello")
public class HelloController {

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        log.info("ApiController");
        return "Greetings from Spring Boot!";
    }

}
