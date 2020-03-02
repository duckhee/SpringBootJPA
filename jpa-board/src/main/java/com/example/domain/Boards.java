package com.example.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "replies")
@EqualsAndHashCode(of="idx")
@Entity
@Table(name="tbl_board")
public class Boards {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Idx")
	private Long idx;
	
	@Column(name="title", nullable = false)
	private String title;
	@Column(name="content")
	private String content;
	@CreationTimestamp
	@Column(name="createdAt")
	private Timestamp createdAt;
	@UpdateTimestamp
	@Column(name="updatedAt")
	private Timestamp updatedAt;
	
	@OneToMany(mappedBy="board", fetch=FetchType.LAZY)
	private List<BoardReply> replies;
	
	@OneToMany
	@JoinColumn(name = "board_idx")
	private List<BoardFiles> files;
}
