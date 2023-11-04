package com.dino.security.domain;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "authority")
/**
 * Possible authorities are:
 * 	ROLE_ADMIN -> highest role, can delete other users
 *  ROLE_CLIENT -> same as ROLE_RENTER (we will only have ROLE_CLIENT), can rent scooter
 */
public class Authority implements GrantedAuthority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JsonIgnore
	private User user;
	private String authority;

	public Authority(String authority, User user) {
		this.authority = authority;
		this.user = user;
	}
	
	public Authority() {}
	
	@Override
	public String getAuthority() {
		return authority;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}	
}
