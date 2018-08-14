package com.innvent.medicspot.model;
import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;

@Entity
@Table(name = "Medicine")
@TypeDefs({ @TypeDef(name = "string-array", typeClass = StringArrayType.class),
		@TypeDef(name = "int-array", typeClass = IntArrayType.class) })
public class Medicine implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", columnDefinition = "uuid")
	@org.hibernate.annotations.Type(type = "pg-uuid")
	@GeneratedValue
	UUID medicineId;

	@Column(name = "name")
	String medicineDescription;

	@Type(type = "string-array")
	@Column(name = "composition", columnDefinition = "pg-text[]")
	String[] medicineComposition;

	@Column(name = "price")
	double medicinePrice;

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

	public String[] getMedicineComposition() {
		return medicineComposition;
	}

	public void setMedicineComposition(String[] medicineComposition) {
		this.medicineComposition = medicineComposition;
	}

	public double getMedicinePrice() {
		return medicinePrice;
	}

	public void setMedicinePrice(double medicinePrice) {
		this.medicinePrice = medicinePrice;
	}

	/*
	 * public Medicine(UUID medicineId, String medicineDescription) { super();
	 * this.medicineId = medicineId; this.medicineDescription = medicineDescription;
	 * }
	 */

}
