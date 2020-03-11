package com.example.persistence;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.Boards;

public interface BoardCustomCrudRepository extends CrudRepository<Boards, Long>, BoardCustom{

}
