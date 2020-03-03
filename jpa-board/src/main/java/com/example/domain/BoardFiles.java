package com.example.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of="idx")
@Entity
@Table(name="tbl_boardFiles")
public class BoardFiles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Idx")
	private Long idx;
	
	@Column(name="fileSize", nullable = false)
	private Float size;
	
	@Column(name="filePath", nullable = false)
	private String path;
	
	//TODO Change Enum Type
	@Column(name="fileType", nullable = false)
	private String type;
	
	@CreationTimestamp
	@Column(name="createdAt")
	private Timestamp createdAt;
	
	@UpdateTimestamp
	@Column(name="udpatedAt")
	private Timestamp updatedAt;
}
