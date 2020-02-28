package com.example.admin.persistence;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.Member;

public interface AdminMemberRepository extends CrudRepository<Member, Long>{

}
