package com.dream.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Dileep
 *
 *         User model Created by Dileep on 16/05/2019
 * 
 */
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "role")
	private int role;

	@Column(name = "type_user")
	private String typeUser;

	public User() {

	}

	public User(User user) {
		this.id = user.id;
		this.name = user.name;
		this.email = user.email;
		this.lastName = user.name;
		this.password = user.password;
		this.role = user.role;
		this.typeUser = user.typeUser;
	}

	public int getId() {
		return id;
	}

	public User setId(int id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public User setName(String name) {
		this.name = name;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public User setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public int getRole() {
		return role;
	}

	public User setRole(int roles) {
		this.role = roles;
		return this;
	}

	public String getTypeUser() {
		return typeUser;
	}

	public void setTypeUser(String typeUser) {
		this.typeUser = typeUser;
	}

}
