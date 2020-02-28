package com.example.admin.persistence;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.Boards;

public interface AdminBoardRepository extends CrudRepository<Boards, Long>{

}
