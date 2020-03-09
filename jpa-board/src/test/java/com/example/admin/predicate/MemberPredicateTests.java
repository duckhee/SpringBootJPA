package com.example.admin.predicate;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.admin.persistence.AdminMemberRepository;

import com.example.domain.Member;

import lombok.extern.java.Log;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberPredicateTests {

	@Autowired
	private AdminMemberRepository repo;
	
	@Test
	public void MemberSearchTests() {
		log.info("Default Tests");
		/** Pageable Tests */
		Pageable pageable = PageRequest.of(0, 20, Direction.DESC, "idx");
		/** get find result */
		Page<Member> result = repo.findAll(repo.makePredicate("t", "10"), pageable);
		log.info("Page : " + result.getPageable());
		/** Get Member detail */
		result.getContent().forEach(members->{
			log.info(""+members);
			
		});
		
	}
	
	@Test
	@Transactional
	public void JoinFindAllTests() {
		log.info("Join Get Tests");
		repo.getJoinList().forEach(members->{
			log.info("member : " + members);
			
		});
	}
}
