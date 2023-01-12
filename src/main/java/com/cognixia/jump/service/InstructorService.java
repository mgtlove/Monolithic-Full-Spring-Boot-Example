package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Address;
import com.cognixia.jump.model.Instructor;
import com.cognixia.jump.model.Student;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.InstructorRepository;
import com.cognixia.jump.repository.StudentRepository;

@Service
public class InstructorService {
	
	@Autowired
	InstructorRepository instructorRepo;
	
	@Autowired
	StudentRepository studentRepo;

	public List<Instructor> getAllInstructors() throws ResourceNotFoundException, Exception {
		// TODO Auto-generated method stub
		List<Instructor> search = instructorRepo.findAll();
		if (search.isEmpty()) {
			throw new ResourceNotFoundException("No Instructor Records in system.");
		}
		
		return search;
	}

	public boolean addInstructor(Instructor instructor) {
		// TODO Auto-generated method stub
		instructorRepo.save(instructor);
		
		
		return true;
	}
	
	// making the link for students to instructors
	public boolean addStudentToInstructor(Long instructorId, Long studentId) {
		
		Optional<Instructor> instructorAdd = instructorRepo.findById(instructorId);
		Optional<Student> studentAdd = studentRepo.findById(studentId);
		
		if (instructorAdd.isPresent() && studentAdd.isPresent()) {
			
			instructorAdd.get().addStudent(studentAdd.get());
			studentAdd.get().addInstructor(instructorAdd.get());
			
			instructorRepo.save(instructorAdd.get());
			studentRepo.save(studentAdd.get());
			
			return true;
		}
		
		return false;
	}
	
}
