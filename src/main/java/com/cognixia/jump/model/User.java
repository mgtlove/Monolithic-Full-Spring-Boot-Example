package com.cognixia.jump.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;

	@Column(updatable = false, nullable = false, unique = true, length = 25)
	private String username;

	@Column(updatable = true, nullable = false, length = 25)
	private String password;

	@Column(updatable = false, nullable = false, unique = true)
	@Email(message = "Not a valid email format.")
	private String email;

	// Setting up 1 user to 1 student
	@JsonIgnoreProperties("student")
	@OneToOne(mappedBy = "user")
	private Student student;

	public User() {
		this.id = -1L;
		this.username = "N/A";
		this.password = "N/A";
		this.email = "N/A";
		this.student = null;
	}

	public User(Long id, String username, String password, String email, Student student) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.student = student;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		
		this.student = student;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", student=" + student + "]";
	}

}
