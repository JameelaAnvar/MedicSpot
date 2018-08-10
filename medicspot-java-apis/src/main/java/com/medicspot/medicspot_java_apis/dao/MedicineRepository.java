package com.medicspot.medicspot_java_apis.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.medicspot.medicspot_java_apis.model.Medicine;
@Repository(value = "MedicineRepository")
public interface MedicineRepository extends CrudRepository<Medicine, String>
{
	String getMedicineList = "select * from Medicine";
	
	@Query(value = getMedicineList, nativeQuery = true)
	public List<Medicine> getMedicineList();

}
