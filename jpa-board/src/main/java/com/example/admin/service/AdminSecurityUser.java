package com.example.admin.service;

import java.util.ArrayList;

import java.util.List;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.example.domain.Member;
import com.example.domain.MemberRole;

import lombok.Getter;
import lombok.extern.java.Log;

@Log
@Getter
@SuppressWarnings("serial")
public class AdminSecurityUser extends User{

	private static final String ROLE_PREFIX = "ROLE_";
	@SuppressWarnings("unused")
	private Member user;
	
	public AdminSecurityUser(Member user) {
		super(user.getUserEmail(), user.getUserPassword(), makeGrantedAuthority(user.getRoles()));
		this.user = user;
	}
	
	private static List<GrantedAuthority> makeGrantedAuthority(List<MemberRole> roles){
		List<GrantedAuthority> list = new ArrayList<>();
		
		roles.forEach(role->{
			log.info("Login User : "+role.toString());
			list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRole()));
		});
		return list;
	}
	
}
