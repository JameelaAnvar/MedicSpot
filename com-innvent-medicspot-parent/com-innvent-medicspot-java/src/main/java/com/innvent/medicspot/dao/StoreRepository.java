package com.innvent.medicspot.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.innvent.medicspot.model.Store;

@Repository
@Transactional
public class StoreRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public Store getById(String placeId) {
		return entityManager.find(Store.class, placeId);
	}

	public Store save(Store store) {

		if (entityManager.find(Store.class, store.getStorePlaceId()) == null) {
			entityManager.persist(store);

		}
		return store;
	}

	public List<Store> saveAll(List<Store> storeList) {

		List<Store> result = new ArrayList<>();
		for (Store store : storeList) {
			result.add(save(store));
		}
		return result;
	}

	
}
