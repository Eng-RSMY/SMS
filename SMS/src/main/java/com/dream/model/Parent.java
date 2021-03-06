package com.dream.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "parent")
public class Parent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "phone")
	private String phone;

	@Column(name = "profession")
	private String profession;
	
	@Column(name = "gender")
	private String gender;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public int getId() {
		return id;
	}

	public Parent setId(int id) {
		this.id = id;
		return this;
	}

	public String getProfession() {
		return profession;
	}

	public Parent setProfession(String profession) {
		this.profession = profession;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public Parent setPhone(String phone) {
		this.phone = phone;
		return this;
	}
	
	

	public String getGender() {
		return gender;
	}

	public Parent setGender(String gender) {
		this.gender = gender;
		return this;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((profession == null) ? 0 : profession.hashCode());
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
		Parent other = (Parent) obj;
		if (id != other.id)
			return false;
		if (profession == null) {
			if (other.profession != null)
				return false;
		} else if (!profession.equals(other.profession))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Parent [id=" + id + ", profession=" + profession + ", gender=" + gender + "]";
	}

}
