package com.cognixia.jump.service;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Address;
import com.cognixia.jump.model.Student;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.AddressRepository;
import com.cognixia.jump.repository.StudentRepository;

// This is also a specific type of @component
@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepo;
	
	@Autowired
	AddressRepository addressRepo;

	//logic abstracted from controller to return all students
	public List<Student> getAllStudents() throws ResourceNotFoundException, Exception {

		List<Student> search = studentRepo.findAll();
		
		if (search.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		
		return search;
	}
	
	//List students by first and last name
	public List<Student> getStudentByName(String firstName, String lastName) 
			throws ResourceNotFoundException, Exception {
		
		List<Student> search = studentRepo.findStudentByfirstNameAndLastName(firstName, lastName);
		
		if (search.isEmpty()) {

			throw new ResourceNotFoundException(firstName, lastName);
		}
		
		return search;
	}
	
	public List<Student> getStudentsByGpa(double gpa) throws ResourceNotFoundException, Exception {

		List<Student> search = studentRepo.findStudentByGpa(gpa);
		
		//return students.stream().filter(s -> s.getGpa() == gpa).collect(Collectors.toList());
		if (search.isEmpty()) {
			throw new ResourceNotFoundException(gpa);
		}
		
		return search;
	}
	
	//add a student
	public boolean addStudent(Student student) {
		
		if (student != null) {
			
			studentRepo.save(student);
			return true;
			
		}
		
		return false;
	}
	
	public boolean addAddressToStudent(Long addressId, Long studentId) {
		
		Optional<Address> addressAdd = addressRepo.findById(addressId);
		Optional<Student> studentAdd = studentRepo.findById(studentId);
		
		if (addressAdd.isPresent() && studentAdd.isPresent()) {
			
			addressAdd.get().addStudent(studentAdd.get());
			studentAdd.get().setAddress(addressAdd.get());
			
			addressRepo.save(addressAdd.get());
			studentRepo.save(studentAdd.get());
			
			return true;
		}
		
		return false;
	}


	public boolean deleteStudentByName(String firstName, String lastName) {
		
		List<Student> found = studentRepo.findStudentByfirstNameAndLastName(firstName, lastName);
		
		if (!found.isEmpty()) {
			studentRepo.delete(found.get(0));
			return true;
		}
		
		return false;
		
	}
	
	
	
}
