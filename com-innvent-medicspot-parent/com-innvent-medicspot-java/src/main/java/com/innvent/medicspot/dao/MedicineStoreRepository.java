package com.innvent.medicspot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.innvent.medicspot.model.MedicineStoreDO;

@Repository
public interface MedicineStoreRepository extends CrudRepository<MedicineStoreDO, String> {

	String medicineAvialibilityQuery = "select * from med_store_map where medicine_id\\:\\:text = :medID and store_id in :storeList "
			+ " order by is_confirm desc, count desc ";
	
	@Query(value = medicineAvialibilityQuery, nativeQuery = true)
	public List<MedicineStoreDO> getMedicineAvialibility
		(@Param(value = "medID") String medID, @Param(value = "storeList") List<String> storeList);
}
