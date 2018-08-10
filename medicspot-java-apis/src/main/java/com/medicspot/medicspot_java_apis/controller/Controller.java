package com.medicspot.medicspot_java_apis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medicspot.medicspot_java_apis.model.MedicineBO;
import com.medicspot.medicspot_java_apis.service.Service;

@RestController
@RequestMapping(value = "/MedicSpot/v1")
public class Controller {
	
	@Autowired Service service;
	@GetMapping("/Medicines")
	public ResponseEntity<?> getMedicinesList()
	{
		List<MedicineBO> medicineList = service.fetchMedicineList();
		return new ResponseEntity<List<MedicineBO>>(medicineList,HttpStatus.OK);
	}

}
