package com.example.admin.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminMemberCustom {

	Page<Object[]> getPaging(String type, String keyword, Pageable page);
}
