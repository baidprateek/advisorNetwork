package com.mahindraFinance.advisorNetwork.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mahindraFinance.advisorNetwork.model.Advisor;
import com.mahindraFinance.advisorNetwork.model.Response;
import com.mahindraFinance.advisorNetwork.model.UserAuth;
import com.mahindraFinance.advisorNetwork.model.UserData;
import com.mahindraFinance.advisorNetwork.service.AdvisorService;

import jakarta.validation.Valid;

@RestController
public class AdvisorController {
	
    @Autowired
	AdvisorService service;
	
	@PostMapping("/admin/advisor")
	public void addAdvisor(@Valid @RequestBody Advisor advisor) {
		service.saveAdvisor(advisor);
	}
	
	@PostMapping("/user/register")
	public ResponseEntity<Response> addUSer(@Valid @RequestBody UserData user) {
		Response resp = service.saveUser(user);
		return  ResponseEntity.status(HttpStatus.OK).body(resp);
	}
	
	@PostMapping("/user/login")
	public ResponseEntity<Response> userLogin(@Valid @RequestBody UserAuth userAuth) {
		return service.checkUserAuthentication(userAuth);
	}
	
	@GetMapping("/user/{userId}/advisor")
	public ResponseEntity<List> getAdvisors(@PathVariable Long userId) {
		System.out.println("userId:: "+userId);
		List advisorList = service.fetchAdvisorList();
		return  new ResponseEntity<>(advisorList, HttpStatus.OK);
	}

}
