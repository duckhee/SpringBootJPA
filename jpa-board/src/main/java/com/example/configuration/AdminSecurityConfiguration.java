package com.example.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.user.service.UserService;

import lombok.extern.java.Log;

@Log
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
@Order(value=1)
public class AdminSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserService UserSerives;

	public AdminSecurityConfiguration() {
		super();
		// TODO Auto-generated constructor stub
	}



	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		log.info("Admin Spring Security Configuration");
		/** Admin Login Logic */
		/*
		http.antMatcher("/admin/**")
		.authorizeRequests()
		.antMatchers("/admin/users/login")
		.hasRole("ADMIN")
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.loginPage("/admin/users/login")
		.loginProcessingUrl("/admin/users/login")
		.usernameParameter("email")
		.passwordParameter("password")
		.defaultSuccessUrl("/admin")
		.permitAll(true)
		.and()
		.logout()
		.logoutUrl("/admin/users/logout")
		.logoutSuccessUrl("/")
		.invalidateHttpSession(true)
		.permitAll()
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
		
		
		
		@Override
		public void configure(WebSecurity web) throws Exception {
			// TODO Auto-generated method stub
			//super.configure(web);
			web.ignoring().antMatchers("/admin/bootstrap/**/**","/admin/img/**", "/admin/dist/**/**", "/admin/plugins/**/**");
		}
		
		/*
		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			// TODO Auto-generated method stub
			auth.inMemoryAuthentication().
			withUser("admin@co.kr").
			password("1111").
			roles("ADMIN");
			//super.configure(auth);
		}
		*/
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		/** Password Encoder */
		return new BCryptPasswordEncoder();
		/** Test User Encoder Use Memory */
		/*
		return new PasswordEncoder() {
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				// TODO Auto-generated method stub
				return rawPassword.equals(encodedPassword);
			}
			
			@Override
			public String encode(CharSequence rawPassword) {
				// TODO Auto-generated method stub
				return rawPassword.toString();
			}
		};
		*/

	}
}
