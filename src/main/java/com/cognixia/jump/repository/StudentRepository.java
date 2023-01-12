package com.cognixia.jump.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

	public List<Student> findStudentByGpa(double gpa);
	
	public List<Student> findStudentByfirstNameAndLastName(String firstName, String lastName); 
	
	// get a range of gpa and use jpql query notation
	@Query("SELECT u FROM Student u WHERE u.gpa >= 2.0")
	public List<Student> findStudentsInGoodStanding();
}
