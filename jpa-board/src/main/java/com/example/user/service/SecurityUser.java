package com.example.user.service;

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
public class SecurityUser extends User{

	private static final String ROLE_PREFIX = "ROLE_";
	@SuppressWarnings("unused")
	private Member user;
	
	public SecurityUser(Member user) {
		super(user.getUserEmail(), user.getUserPassword(), makeGrantedAuthority(user.getRoles()));
		this.user = user;
		System.out.println("user : "+user);
	}
	
	private static List<GrantedAuthority> makeGrantedAuthority(List<MemberRole> roles){
		List<GrantedAuthority> list = new ArrayList<>();
		
		roles.forEach(role->{
			list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRole()));
		});
		log.info("get Security Get List : " + list);
		return list;
	}
	
}
