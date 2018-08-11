package com.medicspot.medicspot_java_apis.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.medicspot.medicspot_java_apis.dao.MedicineRepository;
import com.medicspot.medicspot_java_apis.model.Medicine;
import com.medicspot.medicspot_java_apis.model.MedicineBO;

@org.springframework.stereotype.Service
public class Service {
	
	private MedicineRepository repo;
	
	public Service(@Autowired MedicineRepository repo) {
		super();
		this.repo = repo;
	}

	public List<MedicineBO> fetchMedicineList()
	{
		List<Medicine> resDB = repo.getMedicineList();
		List<MedicineBO> res = new ArrayList<MedicineBO>();
		List<Medicine> resCRUD = (List<Medicine>) repo.findAll();
		System.out.println(resCRUD.toString());
		
		for(Medicine obj : resDB)
		{
				String [] arr = obj.getMedicineComposition();
				StringBuilder compStr = new StringBuilder();
				for(String val : arr)
					compStr.append(val);
			MedicineBO boObj = new MedicineBO(obj.getMedicineId().toString(),
					obj.getMedicineDescription(),compStr.toString(),
					String.valueOf(obj.getMedicinePrice()));
			res.add(boObj);
		}
		return res;
	}
	
	public List<Medicine> saveNewMedicines(List<Medicine> newMedicineList)
	{
		List<Medicine> returnVal = (List<Medicine>) repo.saveAll(newMedicineList);
		return returnVal;
	}

}