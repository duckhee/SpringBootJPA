package com.example.admin.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.domain.Member;
import com.example.domain.QMember;
import com.example.domain.QMemberRole;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.java.Log;

@Log
public class AdminMemberCustomCrudRepositoryImpl extends QuerydslRepositorySupport implements AdminMemberCustom{

	public AdminMemberCustomCrudRepositoryImpl() {
		// TODO Auto-generated constructor stub
		super(Member.class);
	}

	
	@Override
	public Page<Object[]> getPaging(String type, String keyword, Pageable page) {
		// TODO Auto-generated method stub
		log.info("====================================");
		log.info("TYPE : " + type);
		log.info("KEYWORD : " + keyword);
		log.info("PAGE : " + page);
		log.info("====================================");
		QMember user = QMember.member;
		QMemberRole roles = QMemberRole.memberRole;
		
		JPQLQuery<Member> query = from(user);
		/** Get Tuple user.idx, userEmail, userName, role, createdAt, updatedAt */
		JPQLQuery<Tuple> tuple = query.select(user.idx, user.userEmail, user.userName,  roles.role, user.createdAt, user.updatedAt);
		/** Left JOIN */
		tuple.leftJoin(user.roles, roles);
		tuple.where(user.idx.gt(0L));
		
		/** Search Option Type */
		if(type != null) {
			switch(type.toLowerCase()) {
			case "email":
				tuple.where(user.userEmail.like("%" + keyword + "%"));
				break;
			case "name":
				tuple.where(user.userName.like("%" + keyword + "%"));
				break;
			case "level":
				tuple.where(roles.role.like("%" + keyword + "%"));
				break;
			}
		}
		/** Group By and order */
		//tuple.groupBy(user.idx);
		tuple.orderBy(user.idx.desc());
		/** Paging */
		tuple.offset(page.getOffset());
		tuple.limit(page.getPageSize());
		/** Query do */
		List<Tuple> list = tuple.fetch();
		
		/** Return Value List */
		List<Object[]> resultList = new ArrayList<>();
		/** Make Return List */
		list.forEach(users->{
			resultList.add(users.toArray());
			
		});
		long total = tuple.fetchCount();
		
		return new PageImpl<>(resultList, page, total);
	}


	@Override
	public Object[] getDetail(String email) {
		// TODO Auto-generated method stub
		QMember user = QMember.member;
		QMemberRole roles = QMemberRole.memberRole;
		
		JPQLQuery<Member> query = from(user);
		/** Get Tuple user.idx, userEmail, userName, role, createdAt, updatedAt */
		JPQLQuery<Tuple> tuple = query.select(user.idx, user.userEmail, user.userName,  roles.role, user.createdAt, user.updatedAt);
		/** Left JOIN */
		tuple.leftJoin(user.roles, roles);
		tuple.where(user.idx.gt(0L));
		tuple.where(user.userEmail.eq(email));
		return tuple.fetchOne().toArray();
	}


	

}
