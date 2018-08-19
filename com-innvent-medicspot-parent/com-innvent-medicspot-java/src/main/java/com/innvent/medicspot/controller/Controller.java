package com.innvent.medicspot.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.innvent.medicspot.model.LoginBO;
import com.innvent.medicspot.model.Medicine;
import com.innvent.medicspot.model.Store;
import com.innvent.medicspot.model.StoreBO;
import com.innvent.medicspot.service.MedicineService;
import com.innvent.medicspot.service.StoreRegisterService;
import com.innvent.medicspot.service.StoreService;

@RestController
@RequestMapping(value = "/MedicSpot/v1")
public class Controller {

	@Autowired
	MedicineService service;

	@Autowired
	StoreService storeService;
	
	@Autowired
	StoreRegisterService storeRegService;

	@GetMapping("/list/Medicines")
	public ResponseEntity<?> getMedicinesList() {
		List<Medicine> medicineList = service.fetchMedicineList();
		return new ResponseEntity<List<Medicine>>(medicineList, HttpStatus.OK);
	}

	@PostMapping("/save/Medicines")
	public ResponseEntity<?> enterNewMedicines(@RequestBody List<Medicine> medicineList) {
		List<Medicine> response = service.saveNewMedicines(medicineList);
		return new ResponseEntity<List<Medicine>>(response, HttpStatus.OK);
	}

	@PostMapping("/nearbystores/geodetails")
	public ResponseEntity<?> fetchNearbyStoreGeoDetails(@RequestParam("lat") String lat,
			@RequestParam("lng") String lng) {

		List<Store> storeList = storeService.fetchNearbyStoreGeoDetails(lat, lng);
		return new ResponseEntity<List<Store>>(storeList, HttpStatus.OK);
	}

	@GetMapping("/hello")
	public String getHello() {
		return "Hello World";
	}

	@GetMapping("/dataLoad")
	public String dataDump() throws IOException {
		service.dumpMedicineList();

		return "Dump Success";
	}

	@PostMapping("/register/NewStore")
	public ResponseEntity<?> saveNewStore(@RequestBody StoreBO storePayload)
	{
		ResponseEntity<?> res = null;
		try {
			res = storeRegService.addNewStore(storePayload);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>("Exception Occured", HttpStatus.BAD_REQUEST);
		}
		return res;
	}
	
	@PostMapping("/login/Store")
	public ResponseEntity<?> validateUserLogin(@RequestBody LoginBO login)
	{
		String response = storeRegService.authenticateVendor(login);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
}
