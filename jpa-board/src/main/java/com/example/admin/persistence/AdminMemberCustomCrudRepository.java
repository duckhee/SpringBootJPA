package com.example.admin.persistence;

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
	
}
