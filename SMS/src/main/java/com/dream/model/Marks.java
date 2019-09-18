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

import net.bytebuddy.implementation.bind.annotation.Default;

@Entity
@Table(name = "marks")
public class Marks {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "telugu", columnDefinition = " float default 0")
	private Float telugu;

	@Column(name = "hindi", columnDefinition = " float default 0")
	private Float hindi;

	@Column(name = "english", columnDefinition = " float default 0")
	private Float english;

	@Column(name = "maths", columnDefinition = " float default 0")
	private Float maths;

	@Column(name = "science", columnDefinition = " float default 0")
	private Float science;

	@Column(name = "social", columnDefinition = " float default 0")
	private Float social;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "st_id")
	private Student stud;

	public Marks() {
		super();
		this.telugu = 0f;
		this.hindi = 0f;
		this.english = 0f;
		this.maths = 0f;
		this.science = 0f;
		this.social =0f;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Float getTelugu() {
		return telugu;
	}

	public void setTelugu(Float telugu) {
		this.telugu = telugu;
	}

	public Float getHindi() {
		return hindi;
	}

	public void setHindi(Float hindi) {
		this.hindi = hindi;
	}

	public Float getEnglish() {
		return english;
	}

	public void setEnglish(Float english) {
		this.english = english;
	}

	public Float getMaths() {
		return maths;
	}

	public void setMaths(Float maths) {
		this.maths = maths;
	}

	public Float getScience() {
		return science;
	}

	public void setScience(Float science) {
		this.science = science;
	}

	public Float getSocial() {
		return social;
	}

	public void setSocial(Float social) {
		this.social = social;
	}

	public Student getStud() {
		return stud;
	}

	public void setStud(Student stud) {
		this.stud = stud;
	}

	@Override
	public String toString() {
		return "Marks [id=" + id + ", telugu=" + telugu + ", hindi=" + hindi + ", english=" + english + ", maths="
				+ maths + ", science=" + science + ", social=" + social + "]";
	}

}
