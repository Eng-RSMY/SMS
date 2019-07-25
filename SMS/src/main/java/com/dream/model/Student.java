package com.dream.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * @author dileep
 *
 *	Student created by Dileep on 16/07/2019
 */
@Entity
@Table(name = "student")
public class Student {

	@Transient
	private int count = 1;
	
	@Transient
	private final String school = "SSSS";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "st_id")
	private String studentId;
	
	@Column(name = "dob")
	private String dob;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "class")
	private int classOfStudy;
	
	@Column(name = "section")
	private String section;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Parent parent;
	
	@Column(name = "address_id")
	private int addressId;

	public Student() {
		super();
		this.studentId= school+this.count;
		count++;
	}

	public Student(User user) {
		super();
		this.user = user;
		this.studentId= school+this.count;
		count++;
	}

	public int getId() {
		return id;
	}

	public Student setId(int id) {
		this.id = id;
		return this;
	}

	public String getStudentId() {
		return studentId;
	}

	public Student setStudentId(String studentId) {
		this.studentId = studentId;
		return this;
	}

	public String getDob() {
		return dob;
	}

	public Student setDob(String dob) {
		this.dob = dob;
		return this;
	}

	public String getGender() {
		return gender;
	}

	public Student setGender(String gender) {
		this.gender = gender;
		return this;
	}

	public int getClassOfStudy() {
		return classOfStudy;
	}

	public Student setClassOfStudy(int classOfStudy) {
		this.classOfStudy = classOfStudy;
		return this;
	}

	public String getSection() {
		return section;
	}

	public Student setSection(String section) {
		this.section = section;
		return this;
	}

	public User getUser() {
		return user;
	}

	public Student setUser(User user) {
		this.user = user;
		return this;
	}

	public Parent getParent() {
		return parent;
	}

	public Student setParent(Parent parent) {
		this.parent = parent;
		return this;
	}

	public int getAddressId() {
		return addressId;
	}

	public Student setAddressId(int addressId) {
		this.addressId = addressId;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + id;
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
		Student other = (Student) obj;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", gender=" + gender + ", classOfStudy=" + classOfStudy
				+ ", section=" + section + ", addressId=" + addressId + "]";
	}
	
	
}
