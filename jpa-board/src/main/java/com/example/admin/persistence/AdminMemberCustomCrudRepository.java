package com.example.admin.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.domain.Member;

import com.example.domain.QMember;
import com.example.domain.QMemberRole;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface AdminMemberCustomCrudRepository extends CrudRepository<Member, Long>, AdminMemberCustom{

	public default Predicate makePredicate(String type, String keyword) {
		BooleanBuilder builder = new BooleanBuilder();
		QMember user = QMember.member;
		QMemberRole roles = QMemberRole.memberRole;
		builder.and(user.idx.gt(0));
		
		if(type == null) {
			return builder;
		}
		switch(type) {
		case "email":
			builder.and(user.userEmail.like("%" + keyword + "%"));
		break;
		case "name":
			builder.and(user.userName.like("%" + keyword + "%"));
		break;
		case "level":
			builder.and(roles.role.like("%" + keyword  + "%"));
		break;
		}
		return builder;
	}
	
	/** User Detail Page use check first Email */
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
