package com.innvent.medicspot.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.innvent.medicspot.dao.StoreRegisterRepository;
import com.innvent.medicspot.model.LoginBO;
import com.innvent.medicspot.model.LoginResponse;
import com.innvent.medicspot.model.StoreAccount;
import com.innvent.medicspot.model.StoreBO;
import com.innvent.medicspot.util.PasswordAuthentication;

@Service
public class StoreRegisterService {
	
	@Autowired StoreRegisterRepository repo;
	@Autowired PasswordAuthentication authenticator;
	public ResponseEntity<?> addNewStore(StoreBO store) throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		Map<String,String> response = new HashMap<>();
		UUID storeId = (store.getStoreId()!=null)?store.getStoreId():new UUID(0, 0);
		boolean res = repo.existsById(storeId);
		if(!res)
		{
			StoreAccount storePresent = repo.getStoreDetails(store.getUserName());
			if(storePresent != null) {
				response.put("Status", "UserId already exists !");
				return new ResponseEntity<Map<String,String>>(response,HttpStatus.BAD_REQUEST);
			}
		}
		else
		{
			response.put("Status", "Store account already exists !");
			return new ResponseEntity<Map<String,String>>(response,HttpStatus.BAD_REQUEST);
		}
		String hashPassword = authenticator.hash(store.getPassword());
		StoreAccount newStore = new StoreAccount(store.getStoreId(),store.getUserName(),
								hashPassword,store.getAddress(),store.getContactNo(),store.getEmail());
		repo.save(newStore);
		response.put("Status", "Store account added successfully !");
		return new ResponseEntity<Map<String,String>>(response,HttpStatus.OK);
	}
	
	public LoginResponse authenticateVendor(LoginBO login)
	{
		StoreAccount store = repo.getStoreDetails(login.getUserId());
		LoginResponse response;
		if(store == null)
		{
			response = new LoginResponse();
			response.setStatus("Store account doesn't exists !");
			return response;
		}
		String status = authenticator.authenticate(login.getPassword(), store.getHashPassword())==true?
				"Authenticated":"UnAuthenticated";
		if(status.equalsIgnoreCase("UnAuthenticated"))
		{
			response = new LoginResponse();
			response.setStatus("UnAuthenticated");
			return response;
		}
		response = new LoginResponse(status, store.getUserName(), store.getStoreAddress(),
				store.getContactNo(), store.getEmailId());
		return response;
	}

}
