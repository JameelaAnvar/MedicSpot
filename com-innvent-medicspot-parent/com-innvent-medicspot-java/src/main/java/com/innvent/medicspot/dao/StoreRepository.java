package com.innvent.medicspot.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.innvent.medicspot.model.Store;

@Repository(value = "StoreRepository")
public interface StoreRepository extends CrudRepository<Store, String> {

}
