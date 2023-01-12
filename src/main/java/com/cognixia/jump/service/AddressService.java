package com.cognixia.jump.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Address;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	AddressRepository repo;
	
	public List<Address> getAllAddresses() throws ResourceNotFoundException, Exception {
		// TODO Auto-generated method stub
		List<Address> search = repo.findAll();
		
		if (search.isEmpty()) {
			throw new ResourceNotFoundException("No Address records in system.");
		}
		return search;
	}

	public boolean addAddress(Address address) {
		// TODO Auto-generated method stub
		repo.save(address);
		
		if (repo.existsByStreetNumberAndStreetNameAndSuiteNumberAndZipPostalCode(
				address.getStreetNumber(), 
				address.getStreetName(), 
				address.getSuiteNumber(), 
				address.getZipOrPostalCode())) {
			return true;
		}
		
		return false;
	}
	
}
