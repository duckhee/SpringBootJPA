package com.example.admin.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.admin.persistence.AdminMemberCustomCrudRepository;
import com.example.admin.persistence.AdminMemberRepository;
import com.example.util.PageVo;

import lombok.extern.java.Log;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberTests {

	@Autowired
	private AdminMemberCustomCrudRepository repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void InsertListMemberTests() {
		
	}
	
	@Test
	public void ListMemberTests() {
		log.info("member Paging List Tests");
		Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "idx");
		
		repo.getPaging(null, null, page).forEach(items->{
			log.info("member : " + items);
		});
	}
	
	@Test
	public void UpdateMemberTests() {
		
	}
	
	@Test
	public void DeleteMemberTests() {
		
	}
}
