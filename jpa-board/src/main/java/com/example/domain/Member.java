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
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of="idx")
@Entity
@Table(name="tbl_members")
public class Member {
	
	@Id
	@Column(name="Idx")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	
	@Column(name="UserEmail", nullable = false, unique = true)
	private String userEmail;
	
	@Column(name="UserPassword", nullable=false)
	private String userPassword;
	@Column(name="UserName")
	private String userName;
	@Column(name="createdAt")
	@CreationTimestamp
	private Timestamp createdAt;
	@Column(name="updatedAt")
	@UpdateTimestamp
	private Timestamp updatedAt;
}
