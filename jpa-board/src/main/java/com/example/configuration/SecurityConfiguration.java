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

import com.example.admin.service.AdminUserService;

import lombok.extern.java.Log;

@Log
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AdminUserService AdminService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		log.info("Spring Security Configuration");
		/** Admin Login Logic */
		http.authorizeRequests()
		.antMatchers("/admin/users/login").permitAll()
		.antMatchers("/admin/users/logout").permitAll()
		.antMatchers("/admin/**").hasAnyRole("ADMIN")
		.and()
		.formLogin()
		.loginPage("/admin/users/login")
		.loginProcessingUrl("/admin/users/login")
		.usernameParameter("email")
		.passwordParameter("password")
		.defaultSuccessUrl("/admin")
		.and()
		.logout().logoutUrl("/admin/users/logout").invalidateHttpSession(true)
		.and()
		.sessionManagement()
		.maximumSessions(1)
		.expiredUrl("/")
		.maxSessionsPreventsLogin(false);
		/** User Login Logic */
		http.userDetailsService(AdminService);
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
