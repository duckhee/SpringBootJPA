package com.example.admin.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.admin.persistence.AdminMemberCustomCrudRepository;
import com.example.admin.persistence.AdminMemberRepository;
import com.example.domain.Member;
import com.example.util.PageMaker;
import com.example.util.PageVo;
import com.querydsl.core.Tuple;

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
		
		PageVo pageVo = new PageVo();
		pageVo.setType(null);
		pageVo.setKeyword(null);
		Pageable page = pageVo.makePageable(0, "idx");
		Page<Object[]> result = repo.getPaging(null, null, page);
		log.info("total page number : " + result.getTotalPages());
		log.info("content:" + result);
		result.getContent().forEach(items->{
			for(int i = 0; i < items.length; i++) {
				if(items[i] == null) {
					items[i] = "";
				}
				log.info(""+items[i]);
			}
		});
		
	}
	
	@Test
	public void MemberDetailTests() {
		log.info("member detail View Tests");

		Object[] get = repo.getDetail("admin@co.kr");
		
		for(int i = 0; i < get.length; i++) {
			log.info("object[]" + get[i]);
		}
			
		
	
	}
	
	@Test
	public void UpdateMemberTests() {
		
	}
	
	@Test
	public void DeleteMemberTests() {
		
	}
}
