package com.innvent.medicspot.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;

import com.innvent.medicspot.dao.MedicineRepository;
import com.innvent.medicspot.dao.MedicineStoreRepository;
import com.innvent.medicspot.model.Medicine;
import com.innvent.medicspot.model.MedicineStoreBO;
import com.innvent.medicspot.model.MedicineStoreDO;
import com.innvent.medicspot.model.Store;
import com.opencsv.CSVReader;

@org.springframework.stereotype.Service
public class MedicineService {

	@Autowired
	private MedicineRepository repo;
	@Autowired
	private StoreService storeService;
	@Autowired
	private LocationService locationService;
	@Autowired
	private MedicineStoreRepository medStoreRepo;

	public List<Medicine> fetchMedicineList() {

		List<Medicine> resDB = repo.getMedicineList();
		return resDB;
	}

	public List<Medicine> saveNewMedicines(List<Medicine> newMedicineList) {
		List<Medicine> returnVal = (List<Medicine>) repo.saveAll(newMedicineList);
		return returnVal;
	}

	public void dumpMedicineList() throws IOException {
		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader("./CombinationGenericDrugs_Info.csv"), ',');
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Medicine> loadMedicines = new ArrayList<Medicine>();

		// read line by line
		String[] record = null;
		// int counter = 0;
		reader.readNext();
		while ((record = reader.readNext()) != null) {
			// if (counter > 100)
			// break;
			// counter++;
			if (record[1] != null && record[2] != null && record[3] != null && record[4] != null && record[5] != null
					&& record[6] != null && record[7] != null && record[8] != null && record[9] != null) {
				Medicine loadMedicine = new Medicine();
				loadMedicine.setMedicineId(null);
				loadMedicine.setDrugComposition(record[1]);
				loadMedicine.setDrugDescription(record[2]);
				loadMedicine.setDrugIntakeQuantity(record[3]);
				loadMedicine.setDrugManufacturer(record[4]);
				loadMedicine.setDrugName(record[5]);
				loadMedicine.setDrugPrice(Double.parseDouble(record[6]));
				loadMedicine.setDrugQuantity(record[7]);
				loadMedicine.setDrugType(record[8]);
				loadMedicine.setGenericMedicineName(record[9]);
				loadMedicines.add(loadMedicine);
			}
		}
		reader.close();

		List<Medicine> returnVal = (List<Medicine>) repo.saveAll(loadMedicines);

	}

	public List<MedicineStoreBO> getMedicineAvailability(String latitude, String longitude, String medicineId) {
		List<Store> nearByStores = storeService.fetchandSaveNearbyStoreGeoDetails(latitude, longitude);
		List<String> storePlaceIdList = new ArrayList<>();
		Map<String, MedicineStoreBO> map = new HashMap<>();
		Map<String, String> distanceTimeMap;
		List<MedicineStoreBO> res = new ArrayList<>();
		for (Store store : nearByStores) {
			distanceTimeMap = locationService.computeDistance(latitude, longitude, store.getStorePlaceId());
			if (distanceTimeMap.get("status").equalsIgnoreCase("ok")) {
				storePlaceIdList.add(store.getStorePlaceId());
				MedicineStoreBO obj = new MedicineStoreBO();
				obj.setStorePlaceId(store.getStorePlaceId());
				obj.setStoreName(store.getStoreName());
				obj.setStoreAddress(distanceTimeMap.get("destination_address"));
				obj.setDistance(distanceTimeMap.get("distance"));
				obj.setDuration(distanceTimeMap.get("duration"));
				obj.setDistanceNum(Double.parseDouble(distanceTimeMap.get("distanceNum")));
				obj.setDurationNum(Double.parseDouble(distanceTimeMap.get("durationNum")));
				obj.setScore((-1) * (obj.getDistanceNum() + obj.getDurationNum()));
				res.add(obj);
				map.put(store.getStorePlaceId(), obj);
			}
		}
		List<MedicineStoreDO> availDetailsList = medStoreRepo.getMedicineAvialibility(medicineId, storePlaceIdList);
		long count = 0;
		long max = 0;
		int confiremed = 0;
		for (MedicineStoreDO obj : availDetailsList) {
			if (obj.isConfirmed()) {
				confiremed++;
				continue;
			}
			count = count + obj.getCount();
			if (max < obj.getCount())
				max = obj.getCount();
		}
		count = count + (max + 1000) * confiremed;
		for (MedicineStoreDO obj : availDetailsList) {
			if (obj.isConfirmed()) {
				map.get(obj.getStoreId()).setPossibility(100.0);
				MedicineStoreBO val = map.get(obj.getStoreId());
				val.setScore(max * 1000.0 - val.getDistanceNum() - val.getDurationNum());
			} else {
				MedicineStoreBO val = map.get(obj.getStoreId());
				val.setPossibility(50.0 + ((double) obj.getCount() / (double) count) * 100);
				if (val.getPossibility() > 100.0) {
					val.setPossibility(85.0 + (val.getPossibility() - 100.0) / 10);
				}
				val.setScore(obj.getCount() * 1000.0 - val.getDistanceNum() - val.getDurationNum());
			}
		}
		return res;
	}
	
	public void saveFeedBack(MedicineStoreDO entity)
	{
		entity.setId(entity.getStoreId()+","+entity.getMedicineId());
		try {
		medStoreRepo.addFeedback(entity.getId(), entity.getStoreId(), UUID.fromString(entity.getMedicineId()));
		}
		catch(Exception e)
		{
			return;
		}
	}
	public void addMedicineToStore(MedicineStoreDO entity)
	{
		entity.setId(entity.getStoreId()+","+entity.getMedicineId());
		entity.setConfirmed(true);
		try {
		medStoreRepo.addMedicineAvailability(entity.getId(), entity.getStoreId(), UUID.fromString(entity.getMedicineId()), true);
		}
		catch(Exception e)
		{
			return;
		}
	}
}
