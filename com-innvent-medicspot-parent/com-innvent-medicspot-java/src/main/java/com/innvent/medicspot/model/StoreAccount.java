package com.innvent.medicspot.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "store_account")
@Getter
@Setter
public class StoreAccount implements Serializable {
	
	private static final long serialVersionUID = 7572695659099236697L;

	@Id
	@Column(name = "store_id", columnDefinition = "uuid")
	@org.hibernate.annotations.Type(type = "pg-uuid")
	@GeneratedValue //to be removed & set from storeId from store table
	UUID storeId;

	@Column(name = "username")
	String userName;
	
	@Column(name = "hashed_password")
	String hashPassword;
	
	@Column(name = "store_address")
	String storeAddress;
	
	@Column(name = "contact_no")
	String contactNo;
	
	@Column(name = "email_id")
	String emailId;

	public StoreAccount(UUID storeId, String userName, String hashPassword, String storeAddress,
			String contactNo, String emailId) {
		this.storeId = storeId;
		this.userName = userName;
		this.hashPassword = hashPassword;
		this.storeAddress = storeAddress;
		this.contactNo = contactNo;
		this.emailId = emailId;
	}
	
}
