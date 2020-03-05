package com.example.admin.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.domain.Member;

public interface AdminUserRepository extends CrudRepository<Member, Long>{
	
	/** user check Registe Do */
	@Query("SELECT m FROM Member m where m.userEmail=?1")
	public Member getMember(String email);
	
	@Query("SELECT m FROM Member m where m.userEmail=?1")
	public List<Member> getListByUserEmail(String email);
	/** Use Login Service */
	public Optional<Member> findByUserEmail(String email);
	
}
