package com.example.admin.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.domain.QBoardReply;
import com.example.domain.QBoards;
import com.example.domain.Boards;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.java.Log;

@Log
public class AdminBoardCustomCrudRepositoryImpl extends QuerydslRepositorySupport implements AdminBoardCustom{

	public AdminBoardCustomCrudRepositoryImpl() {
		// TODO Auto-generated constructor stub
		super(Boards.class);
	}

	@Override
	public Page<Object[]> getPaging(String type, String keyword, Pageable page) {
		// TODO Auto-generated method stub
		log.info("====================================");
		log.info("TYPE : " + type);
		log.info("KEYWORD : " + keyword);
		log.info("PAGE : " + page);
		log.info("====================================");
		
		QBoards board = QBoards.boards;
		QBoardReply reply = QBoardReply.boardReply;
		
		JPQLQuery<Boards> query = from(board);
		/** Make Select */
		JPQLQuery<Tuple> tuple = query.select(board.idx, board.title, board.content, board.writer,reply.count(), board.createdAt, board.updatedAt);
		/** LEFT JOIN */
		tuple.leftJoin(reply);
		tuple.on(board.idx.eq(reply.boardIdx));
		tuple.where(board.idx.gt(0L));
		/** Search Option Type */
		if(type != null) {
			switch(type.toLowerCase()) {
			case "t":
				tuple.where(board.title.like("%" + keyword + "%"));
				break;
			case "w":
				tuple.where(board.writer.like("%" + keyword + "%"));
				break;
			case "c":
				tuple.where(board.content.like("%" + keyword + "%"));
				break;
			}
		}
		/** Group by and Order */
		tuple.groupBy(board.idx);
		tuple.orderBy(board.idx.desc());
		/** Paging */
		tuple.offset(page.getOffset());
		tuple.limit(page.getPageSize());
		/** get Fetch */
		List<Tuple> list = tuple.fetch();
		/** return list */
		List<Object[]> resultList = new ArrayList<>();
		/** And Reply */
		list.forEach(t->{
			resultList.add(t.toArray());
		});
		
		long total = tuple.fetchCount();
		
		return new PageImpl<>(resultList, page, total);
	}

}
