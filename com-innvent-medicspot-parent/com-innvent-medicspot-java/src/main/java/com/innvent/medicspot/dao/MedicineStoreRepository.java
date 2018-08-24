package com.innvent.medicspot.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.innvent.medicspot.model.MedicineStoreDO;

@Repository
public interface MedicineStoreRepository extends CrudRepository<MedicineStoreDO, String> {

	String medicineAvialibilityQuery = "select * from med_store_map where medicine_id\\:\\:text = :medID and store_id in :storeList "
			+ " order by is_confirm desc, count desc ";
	
	String addMedicineAvailability = "INSERT INTO med_store_map(\r\n" + 
			"	id, store_id, medicine_id, count, is_confirm)\r\n" + 
			"	VALUES ( :id, :storeId, :medId, 1, :isConfirm)\r\n" + 
			"	ON CONFLICT (id) DO UPDATE set count =  med_store_map.count + 1, is_confirm = :isConfirm ";
	
	String addFeedback = "INSERT INTO med_store_map(\r\n" + 
			"	id, store_id, medicine_id, count)\r\n" + 
			"	VALUES ( :id, :storeId, :medId, 1)\r\n" + 
			"	ON CONFLICT (id) DO UPDATE set count = med_store_map.count  + 1 ";
	
	@Query(value = medicineAvialibilityQuery, nativeQuery = true)
	public List<MedicineStoreDO> getMedicineAvialibility
		(@Param(value = "medID") String medID, @Param(value = "storeList") List<String> storeList);
	
	@Query(value = addMedicineAvailability, nativeQuery = true)
	public void addMedicineAvailability(@Param(value="id")String id, @Param(value="storeId")String storeId, @Param(value="medId")UUID medId,
			@Param(value="isConfirm")boolean isConfirm);
	
	@Query(value = addFeedback, nativeQuery = true)
	public void addFeedback(@Param(value="id")String id, @Param(value="storeId")String storeId, @Param(value="medId")UUID medId);
}
