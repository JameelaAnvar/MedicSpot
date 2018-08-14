package com.innvent.medicspot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innvent.medicspot.model.Medicine;
import com.innvent.medicspot.model.MedicineBO;
import com.innvent.medicspot.service.MedicineService;






@RestController
@RequestMapping(value = "/MedicSpot/v1")
public class Controller {
	
	@Autowired 
	MedicineService service;
	
	@GetMapping("/list/Medicines")
	public ResponseEntity<?> getMedicinesList()
	{
		List<MedicineBO> medicineList = service.fetchMedicineList();
		return new ResponseEntity<List<MedicineBO>>(medicineList,HttpStatus.OK);
	}
	
	@PostMapping("/save/Medicines")
	public ResponseEntity<?> enterNewMedicines(@RequestBody List<Medicine> medicineList)
	{
		List<Medicine> response = service.saveNewMedicines(medicineList);
		return new ResponseEntity<List<Medicine>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/hello")
	public String getHello()
	{
		return "Hello World";
	}

}
