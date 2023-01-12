package com.cognixia.jump.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Address;
import com.cognixia.jump.model.User;
import com.cognixia.jump.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	AddressService service;

	// CREATE
	@PostMapping("/add")
	public ResponseEntity<?> addAddress(@RequestBody Address address) {
		if (service.addAddress(address)) {
			return new ResponseEntity<>("Created address: " + address, HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Failed to create address: " + address, HttpStatus.NOT_ACCEPTABLE);
	}

	// READ
	@GetMapping("/all")
	public ResponseEntity<?> getAllAddresses() throws ResourceNotFoundException, Exception {
		return new ResponseEntity<>(service.getAllAddresses(), HttpStatus.OK);
	}
	
	// UPDATE
	
	// DELETE
}
