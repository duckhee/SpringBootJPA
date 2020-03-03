package com.example.admin.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.domain.Member;

public interface AdminUserRepository extends CrudRepository<Member, Long>{
	
	@Query("SELECT m.userEmail FROM Member m where m.userEmail=?1")
	public String getMember(String email);
	
	public List<Member> findByUserEmail(String email);
}
