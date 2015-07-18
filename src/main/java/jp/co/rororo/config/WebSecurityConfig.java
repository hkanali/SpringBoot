package jp.co.rororo.config;

import javax.sql.DataSource;

import jp.co.rororo.service.AuthUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private AuthUserService authUserService;
	
	@Autowired
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new StandardPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*@formatter:off*/
		http
			.authorizeRequests()
				.antMatchers("/", "/api/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/auth/login")
				.permitAll()
				.and()
			.logout()
				.logoutUrl("/auth/logout")
				.permitAll();
		/*@formatter:on*/
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*@formatter:off*/
		auth
			.userDetailsService(authUserService)
				.passwordEncoder(passwordEncoder());
		/*@formatter:on*/
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		/*@formatter:off*/
		auth
			.jdbcAuthentication()
				.dataSource(dataSource);
		/*@formatter:on*/
	}
}
