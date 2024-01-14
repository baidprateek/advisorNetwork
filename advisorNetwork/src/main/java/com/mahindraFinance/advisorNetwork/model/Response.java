package com.mahindraFinance.advisorNetwork.model;

public class Response {

	private Long id;
	private String jwtToken;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	public Response(Long id, String jwtToken) {
		super();
		this.id = id;
		this.jwtToken = jwtToken;
	}
	
	public Response() {
		super();
	}
	
	
}
