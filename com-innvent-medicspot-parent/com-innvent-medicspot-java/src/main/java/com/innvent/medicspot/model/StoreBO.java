package com.innvent.medicspot.model;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class StoreBO {
	
	UUID storeId;
	String userName;
	String password;
	String address;
	String contactNo;
	String email;
	
	public StoreBO(UUID storeId, String userName, String password, String address, String contactNo, String email) {
		super();
		this.storeId = storeId;
		this.userName = userName;
		this.password = password;
		this.address = address;
		this.contactNo = contactNo;
		this.email = email;
	}
}
