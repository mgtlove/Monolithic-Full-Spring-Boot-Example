package com.cognixia.jump.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Instructor;
import com.cognixia.jump.model.User;
import com.cognixia.jump.requestmodels.StudentAndInstructorReqModel;
import com.cognixia.jump.requestmodels.StudentAndUserReqModel;
import com.cognixia.jump.service.InstructorService;

@RestController
@RequestMapping("/instructor")
public class InstructorController {
	
	@Autowired
	InstructorService service;

	// CREATE
	@PostMapping("/add")
	public ResponseEntity<?> addInstructor(@RequestBody Instructor instructor) {
		if (service.addInstructor(instructor)) {
			return new ResponseEntity<>("Created instructor: " + instructor, HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Failed to create instructor: " + instructor, HttpStatus.NOT_ACCEPTABLE);
	}

	// READ
	@GetMapping("/all")
	public ResponseEntity<?> getAllInstructors() throws ResourceNotFoundException, Exception{
		return new ResponseEntity<>(service.getAllInstructors(), HttpStatus.OK);
	}
	
	// UPDATE
	@PutMapping("/addstudent")
	public ResponseEntity<?> addStudent(@RequestBody StudentAndInstructorReqModel model) {
		if (service.addStudentToInstructor(model.getInstructorId(), model.getStudentId())) {
			return new ResponseEntity<>("updated student", HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Failed to update student.", HttpStatus.NOT_ACCEPTABLE);
	}
	
	// DELETE
}
