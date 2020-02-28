package com.example.admin.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.Member;

public interface AdminUserRepository extends CrudRepository<Member, Long>{
	
	public List<Member> findByUserEmail(String email);
}
