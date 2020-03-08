package com.example.admin.persistence;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.example.domain.Member;
import com.example.domain.QMember;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface AdminMemberRepository extends CrudRepository<Member, Long>, QuerydslPredicateExecutor<Member>{
	/** Query dsl */
	public default Predicate makePredicate(String type, String keyword) {
		BooleanBuilder builder = new BooleanBuilder();
		QMember user = QMember.member;
		
		builder.and(user.idx.gt(0));
		if(type == null) {
			return builder;
		}
		switch(type) {
			
		}
		return builder;
	}

}
