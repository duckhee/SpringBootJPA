package com.example.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardCustom {
	/** Custom Paging */
	public Page<Object[]> getPaging(String type, String keyword, Pageable page);
}
