package com.mahindraFinance.advisorNetwork.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mahindraFinance.advisorNetwork.dao.AdvisorDao;
import com.mahindraFinance.advisorNetwork.dao.UserDao;
import com.mahindraFinance.advisorNetwork.jwtToken.JwtTokenUtil;
import com.mahindraFinance.advisorNetwork.model.Advisor;
import com.mahindraFinance.advisorNetwork.model.Response;
import com.mahindraFinance.advisorNetwork.model.UserAuth;
import com.mahindraFinance.advisorNetwork.model.UserData;

@Service
public class AdvisorService {
	
	@Autowired
	AdvisorDao advisordao;
	
	@Autowired
	UserDao userdao;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	public void saveAdvisor(Advisor advisor) {
		advisordao.save(advisor);
	}
	
	public Response saveUser(UserData user) {
		user = userdao.save(user);
		/*
		 * JWT Token Creation
		 */
		String jwtToken = jwtTokenUtil.generateToken(user.toString());
		Response resp = new Response(user.getId(),jwtToken);
		return resp;
	}

	public ResponseEntity<Response> checkUserAuthentication(UserAuth userAuth) {
	  List<UserData> userList = new ArrayList<>();
	  Response resp = new Response();
	  userList = userdao.findByEmailAndPassword(userAuth.getEmail(), userAuth.getPassword());
	  if(userList.size()==0) {
		  return  new ResponseEntity<>(resp, HttpStatus.UNAUTHORIZED);
	  }else {
		    /*
			 * JWT Token Creation
			 */
		  String jwtToken = jwtTokenUtil.generateToken(userAuth.toString());
		  resp.setId(userList.get(0).getId());
		  resp.setJwtToken(jwtToken);
		  return  new ResponseEntity<>(resp, HttpStatus.OK);
	  }
	}
	
	public List fetchAdvisorList() {
		return advisordao.findAll();
	}
}
