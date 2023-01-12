package com.cognixia.jump.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cognixia.jump.repository.StudentRepository;
import com.cognixia.jump.service.StudentService;

//Annotate the test class with a corresponding SRC code Class
@ExtendWith(SpringExtension.class)
@WebMvcTest(StudentController.class)
class StudentControllerTest {

	// We need to account for all SRC code dependencies injected into the class we annotated above
	// ie. StudentController.java 
	
	@MockBean
	StudentService service;
	
	@MockBean
	StudentRepository repo;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void testGetAllStudents() throws Exception {
		
		String uri = "/students/all";
		
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
	}
	

}
