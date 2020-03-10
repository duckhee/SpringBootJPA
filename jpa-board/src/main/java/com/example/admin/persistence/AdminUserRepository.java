package com.example.admin.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.domain.Member;

public interface AdminUserRepository extends CrudRepository<Member, Long>{
	
	public Optional<Member> findByUserEmail(String email);
	/** user check Registe Do */
	@Query("SELECT m FROM Member m WHERE m.userEmail=?1")
	public Member getMember(String email);
	@Query("SELECT m FROM Member m WHERE m.userEmail=?1")
	public List<Member> getListByUserEmail(String email);
	/** User Search by Role */
	@Query("SELECT m FROM Member m ")
	public List<Member> getListByRole(String roles);

}
