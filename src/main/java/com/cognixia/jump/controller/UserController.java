package com.cognixia.jump.controller;

import javax.validation.Valid;

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
import com.cognixia.jump.model.User;
import com.cognixia.jump.requestmodels.StudentAndUserReqModel;
import com.cognixia.jump.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService service;

	// CREATE
	@PostMapping("/add")
	public ResponseEntity<?> addUser(@RequestBody @Valid User user) {
		if (service.addUser(user)) {
			return new ResponseEntity<>("Created user: " + user, HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Failed to create user: " + user, HttpStatus.NOT_ACCEPTABLE);
	}

	// READ
	@GetMapping("/all")
	public ResponseEntity<?> getAllUsers() throws ResourceNotFoundException, Exception {
		return new ResponseEntity<>(service.getAllUsers(), HttpStatus.CREATED);
	}

	// UPDATE
	@PutMapping("/addstudent")
	public ResponseEntity<?> addStudent(@RequestBody StudentAndUserReqModel model) {
		if (service.addStudentToUser(model.getUserId(), model.getStudentId())) {
			return new ResponseEntity<>("update user", HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Failed to update user.", HttpStatus.NOT_ACCEPTABLE);
	}

	// DELETE

}
