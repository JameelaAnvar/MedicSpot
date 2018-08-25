package com.innvent.medicspot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.innvent.medicspot.model.Medicine;

@Repository(value = "MedicineRepository")
public interface MedicineRepository extends CrudRepository<Medicine, String> {
	String fetchAllMedicineList = "select * from Medicine";

	String fetchMedicinesinStore = "select * from Medicine where Medicine.id "
			+ " in (select medicine_id from med_store_map where store_id = :storeId) ";

	String fetchBrandMedicines = "select * from medicine where drug_name ilike CONCAT(' ',:query,'%') order by drug_name limit :limit offset :offset ";

	String fetchMedicineSalt = "select * from medicine where drug_composition ilike CONCAT(:query,'%') order by drug_composition limit :limit offset :offset ";

	String fetchBrandMedicinesCount = "select count(*) from medicine where drug_name ilike CONCAT(' ',:query,'%')";

	String fetchMedicineSaltCount = "select count(*) from medicine where drug_composition ilike CONCAT(:query,'%') ";

	@Query(value = fetchAllMedicineList, nativeQuery = true)
	public List<Medicine> getMedicineList();

	@Query(value = fetchMedicinesinStore, nativeQuery = true)
	public List<Medicine> getMedicinesinStoreList(@Param(value = "storeId") String storeId);

	@Query(value = fetchBrandMedicines, nativeQuery = true)
	public List<Medicine> getBrandMedicines(@Param(value = "query") String query,
			@Param(value = "offset") Integer offset, @Param(value = "limit") Integer limit);

	@Query(value = fetchMedicineSalt, nativeQuery = true)
	public List<Medicine> getMedicineSalt(@Param(value = "query") String query, @Param(value = "offset") Integer offset,
			@Param(value = "limit") Integer limit);

	@Query(value = fetchBrandMedicinesCount, nativeQuery = true)
	public Integer getBrandMedicinesCount(@Param(value = "query") String query);

	@Query(value = fetchMedicineSaltCount, nativeQuery = true)
	public Integer getMedicineSaltCount(@Param(value = "query") String query);

}
