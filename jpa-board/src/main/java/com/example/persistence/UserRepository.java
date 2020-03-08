package com.example.persistence;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.Member;
/**
 * 
 * @author duckheewon
 *
 */
public interface UserRepository extends CrudRepository<Member, Long>{

}
