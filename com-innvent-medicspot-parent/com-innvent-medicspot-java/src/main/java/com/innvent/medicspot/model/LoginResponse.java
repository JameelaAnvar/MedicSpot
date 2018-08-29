package com.innvent.medicspot.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LoginResponse {

	String status;
	String userName;
	String storeAddress;
	String contactNo;
	String emailId;
	
	public LoginResponse(String status, String userName, String storeAddress, String contactNo, String emailId) {
		super();
		this.status = status;
		this.userName = userName;
		this.storeAddress = storeAddress;
		this.contactNo = contactNo;
		this.emailId = emailId;
	}
	
}
