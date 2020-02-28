package com.example.admin.persistence;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.Member;

public interface UserRepository extends CrudRepository<Member, Long>{

}
