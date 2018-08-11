package com.medicspot.medicspot_java_apis.model;

public class MedicineBO {
	
	String medicineId;
	
	String medicineDescription;
	
	String medicineComposition;
	
	String medicinePrice;

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

	public String getMedicineComposition() {
		return medicineComposition;
	}

	public void setMedicineComposition(String medicineComposition) {
		this.medicineComposition = medicineComposition;
	}

	public String getMedicinePrice() {
		return medicinePrice;
	}

	public void setMedicinePrice(String medicinePrice) {
		this.medicinePrice = medicinePrice;
	}

	public MedicineBO(String medicineId, String medicineDescription, String medicineComposition, String medicinePrice) {
		super();
		this.medicineId = medicineId;
		this.medicineDescription = medicineDescription;
		this.medicineComposition = medicineComposition;
		this.medicinePrice = medicinePrice;
	}
	
}
