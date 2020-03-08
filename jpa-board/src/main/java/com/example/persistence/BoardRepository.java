package com.example.persistence;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.Boards;
/**
 * Board Repository
 * @author duckheewon
 *
 */
public interface BoardRepository extends CrudRepository<Boards, Long>{

}
