package com.example.admin.persistence;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminBoardCustom {

	public Page<Object[]> getPaging(String type, String keyword, Pageable page);
}
