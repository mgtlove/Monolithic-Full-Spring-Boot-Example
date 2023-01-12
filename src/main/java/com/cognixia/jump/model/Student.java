package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

// we will annotate later when we have a DB
@Entity
public class Student implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//attributes
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "student_id")
	private Long id;
	
	@Column(name="first_name", nullable = false)
	private String firstName;
	
	@Column(name="last_name", nullable = false)
	private String lastName;
	
	// Dates in JPA and SQL require some extra attention
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	@Column(columnDefinition = "float default 0.0")
	private double gpa;
	
	// Link for 1 to 1 with a User
	
	//@JsonIgnore
//	@JsonManagedReference
	@JsonIgnoreProperties("student")
	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;
	
	// Link for 1 to Many to address
	@JsonIgnoreProperties("students")
	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	//Many To Many with Instructor
	@JsonIgnoreProperties("students")
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
			name = "student_instructor",
			joinColumns = {@JoinColumn(name = "student_id")},
			inverseJoinColumns = {@JoinColumn(name = "instructor_id")}
	)
	private Set<Instructor> instructors = new HashSet<>();
	
	//constructors Spring Boot needs these!  **SpringBoot** needs a () constructor
	public Student() {
		this.id = -1L;
		this.firstName = "N/A";
		this.lastName = "N/A";
		this.dob = new Date();
		this.gpa = 0.0;
		this.user = null;
		this.address = null;
		this.instructors = null;
		
	}
	
	public Student(Long id, String firstName, String lastName, Date dob, 
			double gpa, User user, Address address, Set<Instructor> instructors) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.gpa = gpa;
		this.user = user;
		this.address = address;
		this.instructors = instructors;
	}

	
	//methods (getters and setters) Spring Boot needs these!
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<Instructor> getInstructors() {
		return instructors;
	}

	public void setInstructors(Set<Instructor> instructors) {
		this.instructors = instructors;
	}
	
	public void addInstructor(Instructor instructor) {
		this.instructors.add(instructor);
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + ", gpa="
				+ gpa + ", user=" + user + ", address=" + address + ", instructors=" + instructors + "]";
	}
	
}
