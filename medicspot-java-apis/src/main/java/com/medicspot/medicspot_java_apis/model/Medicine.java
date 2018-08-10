package com.medicspot.medicspot_java_apis.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Medicine")
public class Medicine {
	
	@Id
	@Column(name = "id")
	UUID medicineId;
	
	@Column(name = "name")
	String medicineDescription;

	public UUID getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(UUID medicineId) {
		this.medicineId = medicineId;
	}

	public String getMedicineDescription() {
		return medicineDescription;
	}

	public void setMedicineDescription(String medicineDescription) {
		this.medicineDescription = medicineDescription;
	}

/*	public Medicine(UUID medicineId, String medicineDescription) {
		super();
		this.medicineId = medicineId;
		this.medicineDescription = medicineDescription;
	}*/

}
