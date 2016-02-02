package com.eventz.events.login.model;

import java.util.Date;

public class User {

	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private boolean active = false;
	
	private String password;
	
	private Date createdOn;
	
	private Date lastLoginOn;
	
	private String rememberCookie;
	
	private String confirmKey;
	
	private Date confirmedOn;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getLastLoginOn() {
		return lastLoginOn;
	}

	public void setLastLoginOn(Date lastLoginOn) {
		this.lastLoginOn = lastLoginOn;
	}

	public String getRememberCookie() {
		return rememberCookie;
	}

	public void setRememberCookie(String rememberCookie) {
		this.rememberCookie = rememberCookie;
	}

	public String getConfirmKey() {
		return confirmKey;
	}

	public void setConfirmKey(String confirmKey) {
		this.confirmKey = confirmKey;
	}

	public Date getConfirmedOn() {
		return confirmedOn;
	}

	public void setConfirmedOn(Date confirmedOn) {
		this.confirmedOn = confirmedOn;
	}

}
