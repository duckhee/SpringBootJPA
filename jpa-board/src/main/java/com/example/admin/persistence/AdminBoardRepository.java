package com.example.admin.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.example.domain.Boards;
import com.example.domain.QBoards;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface AdminBoardRepository extends CrudRepository<Boards, Long>, QuerydslPredicateExecutor<Boards>{
	/** Query dsl */
	public default Predicate makePredicate(String type, String keyword) {
		BooleanBuilder builder = new BooleanBuilder();
		QBoards board =QBoards.boards;
		builder.and(board.idx.gt(0));
		return builder;
	}
	@Query("SELECT b FROM Boards b WHERE title = ?1")
	public List<Boards> findListByTitle(String title);
	/** Find By Use Title */
	public Boards findByTitle(String title);
}
