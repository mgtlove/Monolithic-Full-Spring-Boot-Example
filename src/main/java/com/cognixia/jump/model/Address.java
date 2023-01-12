package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Address implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "address_id")
	private Long id;
	
	@Column(nullable = false, length = 10)
	private String streetNumber;
	
	@Column(nullable = false, length = 30)
	private String streetName;
	
	@Column(nullable = true, length = 10)
	private String suiteNumber;
	
	@Column(nullable = false, length = 30)
	private String city;
	
	@Column(nullable = true, length = 30)
	private String stateOrProvince;
	
	@Column(nullable = false, length = 10)
	private String zipPostalCode;
	
	@Column(nullable = false, length = 30)
	private String country;
	
	// Link for the 1 to Many of student
	@JsonIgnoreProperties("address")
	@OneToMany(mappedBy = "address", targetEntity = Student.class)
	private Set<Student> students = new HashSet<>();
	
	public Address() {
		this.id = -1L;
		this.streetNumber = "N/A";
		this.streetName = "N/A";
		this.suiteNumber = "N/A";
		this.city = "N/A";
		this.stateOrProvince = "N/A";
		this.zipPostalCode = "N/A";
		this.country = "N/A";
		this.students = null;
	}

	public Address(Long id, String streetNumber, String streetName, String suiteNumber, String city,
			String stateOrProvince, String zipOrPostalCode, String country, Set<Student> students) {
		super();
		this.id = id;
		this.streetNumber = streetNumber;
		this.streetName = streetName;
		this.suiteNumber = suiteNumber;
		this.city = city;
		this.stateOrProvince = stateOrProvince;
		this.zipPostalCode = zipOrPostalCode;
		this.country = country;
		this.students = students;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getSuiteNumber() {
		return suiteNumber;
	}

	public void setSuiteNumber(String suiteNumber) {
		this.suiteNumber = suiteNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateOrProvince() {
		return stateOrProvince;
	}

	public void setStateOrProvince(String stateOrProvince) {
		this.stateOrProvince = stateOrProvince;
	}

	public String getZipOrPostalCode() {
		return zipPostalCode;
	}

	public void setZipOrPostalCode(String zipOrPostalCode) {
		this.zipPostalCode = zipOrPostalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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
		return "Address [id=" + id + ", streetNumber=" + streetNumber + ", streetName=" + streetName + ", suiteNumber="
				+ suiteNumber + ", city=" + city + ", stateOrProvince=" + stateOrProvince + ", zipOrPostalCode="
				+ zipPostalCode + ", country=" + country + ", students=" + students + "]";
	}
	
}
