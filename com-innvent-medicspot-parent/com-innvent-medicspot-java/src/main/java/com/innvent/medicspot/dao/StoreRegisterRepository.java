package com.innvent.medicspot.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.innvent.medicspot.model.StoreAccount;

public interface StoreRegisterRepository extends CrudRepository<StoreAccount, UUID> {

	String getStore = "select * from store_account where :userId is NULL OR username = :userId";
	
	@Query(value = getStore, nativeQuery = true)
	public StoreAccount getStoreDetails(@Param(value = "userId") String userId);
}
