package jp.co.rororo.config;

import javax.sql.DataSource;

import jp.co.rororo.service.AuthUserService;
import jp.co.rororo.type.AuthorityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final static String AUTH_KEY = "auth_key";
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private AuthUserService authUserService;
	
	@Autowired
	private PersistentTokenRepository persistentTokenRepository;
	
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
				.antMatchers("/", "/css/**", "/js/**", "/images/**", "/font/**")
				.permitAll();
		http
			.authorizeRequests()
				.antMatchers("/api/**").hasAnyRole(AuthorityType.ROOT.toString(), AuthorityType.ADMIN.toString())
				.anyRequest().authenticated();
		http
			.formLogin()
				.loginPage("/auth/login")
				.permitAll();
		http
			.logout()
				.logoutUrl("/auth/logout")
				.permitAll();
		http
			.rememberMe()
			.key(AUTH_KEY)
			.rememberMeServices(rememberMeServices());
		/*@formatter:on*/
	}
	
	public RememberMeServices rememberMeServices() {
		PersistentTokenBasedRememberMeServices services = new PersistentTokenBasedRememberMeServices(AUTH_KEY,
			authUserService, persistentTokenRepository);
		services.setCookieName("JSESSSIONID");
		services.setTokenValiditySeconds(1 * 30 * 24 * 60 * 60);
		services.setAlwaysRemember(true);
		return services;
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
