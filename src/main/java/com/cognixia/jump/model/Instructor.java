package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Instructor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "instructor_id")
	private Long id;

	@Column(nullable = false, length = 25)
	private String firstName;
	
	@Column(nullable = false, length = 25)
	private String lastName;
	
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	@Column(nullable = false, length = 25)
	private String specialty;
	
	@Column(nullable = true, length = 250)
	private String aboutMe;
	
	//Many to Many with Student
	@JsonIgnoreProperties("instructors")
	@ManyToMany(mappedBy = "instructors")
	private Set<Student> students = new HashSet<>();
	
	public Instructor() {
		this.id = -1L;
		this.firstName = "N/A";
		this.lastName = "N/A";
		this.dob = null;
		this.specialty = "N/A";
		this.aboutMe = "N/A";
		this.students = null;
	}

	public Instructor(Long id, String firstName, String lastName, Date dob, String specialty, String aboutMe,
			Set<Student> students) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.specialty = specialty;
		this.aboutMe = aboutMe;
		this.students = students;
	}

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

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
	public void addStudent(Student student) {
		this.students.add(student);
	}

	@Override
	public String toString() {
		return "Instructor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob
				+ ", specialty=" + specialty + ", aboutMe=" + aboutMe + ", students=" + students + "]";
	}
	
}
