package com.example.admin.persistence;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.domain.Boards;

public interface AdminBoardCustom {

	public Page<Object[]> getPaging(String type, String keyword, Pageable page);
	/** view Board Update View Cnt */
	public Boards getView(Long bno);
}
