package com.example.admin.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.admin.persistence.AdminBoardRepository;
import com.example.domain.Boards;

import lombok.extern.java.Log;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardTests {

	@Autowired
	private AdminBoardRepository repo;
	
	@Test
	public void InsertTests() {
		log.info("Board Insert Tests");
		Boards board = new Boards();
		board.setTitle("test");
		board.setContent("<p>test</p>");
		repo.save(board);
		repo.findListByTitle(board.getTitle()).forEach(items->{
			log.info(items.toString());
		});
	}
	
	@Test
	public void ModifyTests() {
		log.info("Modify Board Tests");
		Boards board = repo.findByTitle("test");
		log.info("Find Boards : " + board.toString());
		board.setTitle("test1");
		repo.save(board);
		log.info("Update : " + repo.findByTitle("test1").toString());
	}
	
	@Test
	public void SelectTests() {
		log.info("Select Board Tests");
	}
	
	@Test
	public void ListTests() {
		log.info("Select Board Paging Tests");
	}
	
	@Test
	public void AddFilesTests() {
		log.info("add file upload Tests");
	}
	
	@Test
	public void AddFilesViewTests() {
		log.info("add file View Tests");
	}
	
	@Test
	public void ViewTests() {
		log.info("Board View Tests");
	}
	
	@Test
	public void ReplyAddTests() {
		log.info("Board Reply Add Tests");
	}
	
	@Test
	public void ReplyDeleteTests() {
		log.info("Board Reply Delete Tests");
	}
	
	@Test
	public void ReplyListTests() {
		log.info("Board Reply List Tests");
	}
}
