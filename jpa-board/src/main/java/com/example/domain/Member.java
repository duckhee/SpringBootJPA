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
/**
 * 
 * @author duckheewon
 * 양방향 설정 유저와 유저 권한 양방향 설정 
 */
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
	
	/** Fetch Type EAGER is Right Now */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="member")
	private List<MemberRole> roles;
}
