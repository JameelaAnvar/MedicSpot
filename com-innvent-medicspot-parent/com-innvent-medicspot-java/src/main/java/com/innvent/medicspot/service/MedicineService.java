package com.innvent.medicspot.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.innvent.medicspot.dao.MedicineRepository;
import com.innvent.medicspot.model.Medicine;
import com.opencsv.CSVReader;

@org.springframework.stereotype.Service
public class MedicineService {

	private MedicineRepository repo;

	public MedicineService(@Autowired MedicineRepository repo) {
		super();
		this.repo = repo;
	}

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
}
