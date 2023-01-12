package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.ranges.RangeException;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Student;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.StudentRepository;
import com.cognixia.jump.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	StudentRepository studentRepo;

	public List<User> getAllUsers() throws ResourceNotFoundException, Exception {
		// TODO Auto-generated method stub
		List<User> search = userRepo.findAll();
		
		if (search.isEmpty()) {
			throw new ResourceNotFoundException("No User records in System.");
		}
		
		return search;
	}

	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		userRepo.save(user);
		
		if (userRepo.findUserByEmail(user.getEmail()).isPresent()) {
			return true;
		}
		
		return false;
	}
	
	// set both sides of the enities to complete the relationship
	public boolean addStudentToUser(Long userId, Long studentId) {
		
		Optional<User> userAdd = userRepo.findById(userId);
		Optional<Student> studentAdd = studentRepo.findById(studentId);
		
		if (userAdd.isPresent() && studentAdd.isPresent()) {
			
			userAdd.get().setStudent(studentAdd.get());
			studentAdd.get().setUser(userAdd.get());
			
			userRepo.save(userAdd.get());
			studentRepo.save(studentAdd.get());
			
			return true;
		}
		
		return false;
	}

}
