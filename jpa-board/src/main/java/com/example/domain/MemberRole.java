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


@Getter
@Setter
@EqualsAndHashCode(of="idx")
@ToString
@Entity
@Table(name="tbl_member_role")
public class MemberRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Idx")
	private Long idx;
	
	private String role;
	
	@CreationTimestamp
	@Column(name="createdAt")
	private Timestamp createdAt;
	
	@UpdateTimestamp
	@Column(name="updatedAt")
	private Timestamp updatedAt;
	
}
