package jp.co.rororo.app;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = { "jp.co.rororo" })
@EnableJpaRepositories(basePackages = { "jp.co.rororo.repository" })
@EntityScan(basePackages = { "jp.co.rororo.entity" })
public class Application {
	
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		
		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			log.info(beanName);
		}
	}
	
}
