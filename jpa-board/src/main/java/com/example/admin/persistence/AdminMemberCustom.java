package com.example.admin.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.domain.Member;
import com.querydsl.core.Tuple;

public interface AdminMemberCustom {

	public Page<Object[]> getPaging(String type, String keyword, Pageable page);
	
	/** Member Detail Get */
	//public List<Tuple> getDetail(String email);
	public Object[] getDetail(String email);
}
