package com.medicspot.medicspot_java_apis.model;

public class MedicineBO {
	
	String medicineId;
	
	String medicineDescription;

	public String getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(String medicineId) {
		this.medicineId = medicineId;
	}

	public String getMedicineDescription() {
		return medicineDescription;
	}

	public void setMedicineDescription(String medicineDescription) {
		this.medicineDescription = medicineDescription;
	}

	public MedicineBO(String medicineId, String medicineDescription) {
		super();
		this.medicineId = medicineId;
		this.medicineDescription = medicineDescription;
	}

	
}
