package com.example.admin.persistence;

import org.springframework.data.repository.CrudRepository;


import com.example.domain.Boards;
import com.example.domain.QBoards;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface AdminBoardCustomCrudRepository extends CrudRepository<Boards, Long>, AdminBoardCustom{

	public default Predicate makePredicate(String type, String keyword) {
		BooleanBuilder builder = new BooleanBuilder();
		QBoards board = QBoards.boards;
		
		builder.and(board.idx.gt(0));
		
		if(type == null) {
			return builder;
		}
		switch(type) {
		case "t":
			builder.and(board.title.like("%" + keyword + "%"));
		break;
		case "c":
			builder.and(board.content.like("%" + keyword + "%"));
		break;
		case "w":
			builder.and(board.writer.like("%" + keyword + "%"));
		break;
		}
		return builder;
	}
}
