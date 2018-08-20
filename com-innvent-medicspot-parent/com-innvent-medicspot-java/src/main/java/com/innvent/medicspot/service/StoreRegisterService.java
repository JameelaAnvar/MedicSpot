package com.innvent.medicspot.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.innvent.medicspot.dao.StoreRegisterRepository;
import com.innvent.medicspot.model.LoginBO;
import com.innvent.medicspot.model.StoreAccount;
import com.innvent.medicspot.model.StoreBO;
import com.innvent.medicspot.util.PasswordAuthentication;

@Service
public class StoreRegisterService {
	
	@Autowired StoreRegisterRepository repo;
	@Autowired PasswordAuthentication authenticator;
	public ResponseEntity<?> addNewStore(StoreBO store) throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		UUID storeId = (store.getStoreId()!=null)?store.getStoreId():new UUID(0, 0);
		boolean res = repo.existsById(storeId);
		if(!res)
		{
			StoreAccount storePresent = repo.getStoreDetails(store.getUserName());
			if(storePresent != null)
				return new ResponseEntity<String>("UserId already exists !",HttpStatus.BAD_REQUEST);
		}
		else
		{
			return new ResponseEntity<String>("Store account already exists !",HttpStatus.BAD_REQUEST);
		}
		String hashPassword = authenticator.hash(store.getPassword());
		StoreAccount newStore = new StoreAccount(store.getStoreId(),store.getUserName(),
								hashPassword,store.getAddress(),store.getContactNo(),store.getEmail());
		repo.save(newStore);
		return new ResponseEntity<String>("Store account added successfully !",HttpStatus.OK);
	}
	
	public String authenticateVendor(LoginBO login)
	{
		StoreAccount store = repo.getStoreDetails(login.getUserId());
		if(store == null)
		{
			return "Store account doesn't exists !";
		}
		return authenticator.authenticate(login.getPassword(), store.getHashPassword())==true?"Authenticated:"+store.getStoreAddress():"UnAuthenticated";
	}

}
