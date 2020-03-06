package com.example.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.example.user.service.UserService;

import lombok.extern.java.Log;

@Log
@Configuration
@EnableWebSecurity
@Order(value=2)
public class UserSecurityConfiguratiion extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserService UserSerives;


	public UserSecurityConfiguratiion() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		log.info("User Spring Security Configuration");
		/** Admin Login Logic */
		/*
		http.authorizeRequests()
		.antMatchers("/users/login").permitAll()
		.antMatchers("/users/logout").permitAll()
		.antMatchers("/boards/list").hasAnyRole("USER", "ADMIN")
		.and()
		.formLogin()
		.loginPage("/users/login")
		.loginProcessingUrl("/users/login")
		.usernameParameter("email")
		.passwordParameter("password")
		.defaultSuccessUrl("/")
		.and()
		.logout().logoutUrl("/users/logout").invalidateHttpSession(true)
		.and()
		.sessionManagement()
		.maximumSessions(1)
		.expiredUrl("/")
		.maxSessionsPreventsLogin(false);
		*/
		/** User Login Logic */
		http.userDetailsService(UserSerives);
		//super.configure(http);
		}
}
