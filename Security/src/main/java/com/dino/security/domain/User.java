package com.dino.security.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity(name = "users")
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private String nickname;
	private String password;
	private String cardNumber;
	
	/**
	 * email will be used as username
	 */
	private String email;
	private byte[] idCard;
	private byte[] criminalRecord;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Authority> authorities = new HashSet<>();
	
	public User() {}
	
	public User(String firstName, String lastName, String nickname, String password, String cardNumber,
			String email, byte[] idCard, byte[] criminalRecord) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickname = nickname;
		this.password = password;
		this.cardNumber = cardNumber;
		this.email = email;
		this.idCard = idCard;
		this.criminalRecord = criminalRecord;
	}

	public void addAuthority(String authority) {
		Authority newAuthority = new Authority();
		newAuthority.setAuthority(authority);
		newAuthority.setUser(this);
		authorities.add(newAuthority);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	@Override
	public String getUsername() {
		return email;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getIdCard() {
		return idCard;
	}

	public void setIdCard(byte[] idCard) {
		this.idCard = idCard;
	}

	public byte[] getCriminalRecord() {
		return criminalRecord;
	}

	public void setCriminalRecord(byte[] criminalRecord) {
		this.criminalRecord = criminalRecord;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}
}
