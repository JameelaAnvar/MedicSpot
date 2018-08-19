package com.innvent.medicspot.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.springframework.beans.factory.annotation.Autowired;
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
	public void addNewStore(StoreBO store) throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		String hashPassword = authenticator.hash(store.getPassword());
		StoreAccount newStore = new StoreAccount(store.getStoreId(),store.getUserName(),
								hashPassword,store.getAddress(),store.getContactNo(),store.getEmail());
		repo.save(newStore);
	}
	
	public String authenticateVendor(LoginBO login)
	{
		StoreAccount store = repo.getStoreDetails(login.getUserId());
		if(store == null)
		{
			return "Store account doesn't exists !";
		}
		return authenticator.authenticate(login.getPassword(), store.getHashPassword())==true?"Authenticated":"UnAuthorished";
	}

}
