package br.edu.ifsp.arq.model.entity;

import at.favre.lib.crypto.bcrypt.BCrypt;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String username;
	private String email;
	private String password;
	
	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		setHashPassword(password);
	}
	
	public User(Long id, String username, String email) {
		this.id = id;
		this.username = username;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String p) {
		this.password = p;
	}

	public void setHashPassword(String p) {
		this.password = BCrypt.withDefaults().hashToString(12, p.toCharArray());
	}

	public boolean checkPassword(String p) {
		BCrypt.Result result = BCrypt.verifyer().verify(p.toCharArray(), this.password);
		return result.verified;
	}
	
	@Override
	public String toString() {
	    return id + ";" + username + ";" + email + ";" + password;
	}
}
