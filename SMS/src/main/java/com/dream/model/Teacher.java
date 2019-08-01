package com.dream.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author dileep
 * 
 * 		Teacher model Created by Dileep on 15/07/2019
 *
 */

@Entity
@Table(name = "teacher")
public class Teacher{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "dob")
	private String dob;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "subject")
	private String subject;
	
	@Column(name = "experience")
	private int experience;
	
	@Column(name = "language")
	private String language;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "address_id")
	private int addressId;

	public Teacher() {
		super();
	}

	public Teacher(User user) {
		super();
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public Teacher setId(int id) {
		this.id = id;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public Teacher setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public String getDob() {
		return dob;
	}

	public Teacher setDob(String dob) {
		this.dob = dob;
		return this;
	}

	public String getGender() {
		return gender;
	}

	public Teacher setGender(String gender) {
		this.gender = gender;
		return this;
	}

	public String getSubject() {
		return subject;
	}

	public Teacher setSubject(String subject) {
		this.subject = subject;
		return this;
	}

	public int getExperience() {
		return experience;
	}

	public Teacher setExperience(int experience) {
		this.experience = experience;
		return this;
	}

	public String getLanguage() {
		return language;
	}

	public Teacher setLanguage(String language) {
		this.language = language;
		return this;
	}

	public User getUser() {
		return this.user;
	}

	public Teacher setUser(User user) {
		this.user = user;
		return this;
	}

	public int getAddressId() {
		return addressId;
	}

	public Teacher setAddressId(int addressId) {
		this.addressId = addressId;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Teacher other = (Teacher) obj;
		if (id != other.id)
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", phone=" + phone + ", gender=" + gender + ", subject=" + subject
				+ ", experience=" + experience + "]";
	}
	
	

}
