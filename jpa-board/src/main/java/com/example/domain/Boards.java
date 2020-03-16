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
@ToString(exclude = {"replies", "files"})
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
	@Column(name="cnt")
	private Long cnt;
	@Column(name="writer", nullable = false)
	private String writer;
	@CreationTimestamp
	@Column(name="createdAt")
	private Timestamp createdAt;
	@UpdateTimestamp
	@Column(name="updatedAt")
	private Timestamp updatedAt;
	
	/** mappedBy="value" value is MemberRole's member
	 * mappedBy : 양뱡향 관계에서 주체가 되는 쪽(Many쪽, 외래키가 있는 쪽)을 정의
	 * 여기서는 MemberRole에서 Member를 객체로 가지는 이름을 써줘야한다.
	 */
	@OneToMany(mappedBy="board", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<BoardReply> replies;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "board_Idx")
	private List<BoardFiles> files;
}
