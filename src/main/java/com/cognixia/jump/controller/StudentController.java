package com.cognixia.jump.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Student;
import com.cognixia.jump.repository.StudentRepository;
import com.cognixia.jump.requestmodels.StudentAndAddressReqModel;
import com.cognixia.jump.requestmodels.StudentAndUserReqModel;
import com.cognixia.jump.service.StudentService;

// This entry point / exit point for inbound req, and corresponding outbound responses
// This is a specific type of component
// @Component is a generic component to mark a class for handling by Spring Boot
@RestController
@RequestMapping("/students")
public class StudentController {

	// THE OLD way
	// StudentService service = new StudentService();

	// Let's AUTOWIRE when we can (ie. let SB make objects for us)
	@Autowired
	StudentService service;

	@Autowired
	StudentRepository repo;

	// Read from my API
	@GetMapping("/all")
	public List<Student> getAllStudents() throws ResourceNotFoundException, Exception {

		return service.getAllStudents();
	}

	// Read a single student based on a value
	// @GetMapping("/students/{gpa}") -> this is the same as below:
	@RequestMapping(value = "/gpa/{gpa}", method = RequestMethod.GET)
	public List<Student> getStudentsByGpa(@PathVariable double gpa) throws ResourceNotFoundException, Exception {

		return service.getStudentsByGpa(gpa);

	}

	@GetMapping("/name/{firstName}/{lastName}")
	public List<Student> getStudentsByName(@PathVariable String firstName, @PathVariable String lastName)
			throws ResourceNotFoundException, Exception {

		return service.getStudentByName(firstName, lastName);
	}

	@GetMapping("/goodstanding")
	public ResponseEntity<?> getStudentsInGoodStanding() throws ResourceNotFoundException, Exception{
		List<Student> goodStudents = repo.findStudentsInGoodStanding();
		
		if (goodStudents.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		
		return new ResponseEntity<List<Student>>(goodStudents, HttpStatus.OK);
	}

	// Write a method to find a student by both first and last name

	// Write a method to delete a student by both first and last name

	// Write to my API
	@PostMapping("/add")
	public boolean addStudent(@RequestBody Student student) {

		return service.addStudent(student);

	}

	// UPDATE
	@PutMapping("/addaddress")
	public ResponseEntity<?> addAddress(@RequestBody StudentAndAddressReqModel model) {
		if (service.addAddressToStudent(model.getAddressId(), model.getStudentId())) {
			return new ResponseEntity<>("updated student", HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Failed to update student.", HttpStatus.NOT_ACCEPTABLE);
	}

	@DeleteMapping("/delete/{firstName}/{lastName}")
	public boolean deleteStudentByName(@PathVariable String firstName, @PathVariable String lastName) {

		return service.deleteStudentByName(firstName, lastName);
	}

}
