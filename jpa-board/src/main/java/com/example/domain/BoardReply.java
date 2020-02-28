package com.example.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;

@Log
@Setter
@Getter
@ToString(exclude = "board")
@EqualsAndHashCode(of="idx")
@Entity
@Table(name = "tbl_Replies")
public class BoardReply {

	@Id
	@Column(name="Idx")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	
	@Column(name="boardIdx")
	private Long boardIdx;
	@Column(name="replyer")
	private String replyer;
	@Column(name="reContent")
	private String reContent;
	@CreationTimestamp
	@Column(name="createdAt")
	private Timestamp createdAt;
	@UpdateTimestamp
	@Column(name="updatedAt")
	private Timestamp updatedAt;
	
	@ManyToOne
	private Boards board;
}
