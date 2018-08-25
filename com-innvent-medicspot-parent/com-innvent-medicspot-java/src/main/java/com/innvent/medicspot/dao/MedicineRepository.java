package com.innvent.medicspot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.innvent.medicspot.model.Medicine;

@Repository(value = "MedicineRepository")
public interface MedicineRepository extends CrudRepository<Medicine, String>
{
	String fetchAllMedicineList = "select * from Medicine";
	
	String fetchMedicinesinStore = "select * from Medicine where Medicine.id "
			+ " in (select medicine_id from med_store_map where store_id = :storeId) ";
	
	
	@Query(value = fetchAllMedicineList, nativeQuery = true)
	public List<Medicine> getMedicineList();
	
	@Query(value = fetchMedicinesinStore, nativeQuery = true)
	public List<Medicine> getMedicinesinStoreList(@Param(value = "storeId")String storeId);

}
