package com.example.admin.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.domain.Boards;

public interface AdminBoardRepository extends CrudRepository<Boards, Long>{
	
	@Query("SELECT b FROM Boards b WHERE title = ?1")
	public List<Boards> findByTitle(String title);
}
