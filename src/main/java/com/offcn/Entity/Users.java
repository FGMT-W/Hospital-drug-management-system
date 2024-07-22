package com.offcn.Entity;

import java.util.Date;

public class Users {

	private String username;
	private String password;
	
	private int uid;
	private String name;
	private String email;
	private int status;
	private Date modifytime;

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Users(String name, String email, String username, String password, Date modifytime) {
		super();
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.modifytime = modifytime;
	}
	
	public Users(String username, String password, int uid, String name, String email, int status, Date modifytime) {
		super();
		this.username = username;
		this.password = password;
		this.uid = uid;
		this.name = name;
		this.email = email;
		this.status = status;
		this.modifytime = modifytime;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}
	
}

