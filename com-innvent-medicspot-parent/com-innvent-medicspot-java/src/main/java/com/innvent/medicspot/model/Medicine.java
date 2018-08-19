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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
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

	@Column(name = "drug_composition")
	String drugComposition;

	
	@Column(name = "drug_description")
	String drugDescription;

	@Column(name = "drug_intake_quantity")
	String drugIntakeQuantity;

	@Column(name = "drug_manufacturer")
	String drugManufacturer;

	@Column(name = "drug_name")
	String drugName;

	@Column(name = "drug_price")
	double drugPrice;

	@Column(name = "drug_quantity")
	String drugQuantity;

	@Column(name = "drug_type")
	String drugType;

	@Column(name = "generic_medicine_name")
	String genericMedicineName;

	public UUID getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(UUID medicineId) {
		this.medicineId = medicineId;
	}

	public String getDrugComposition() {
		return drugComposition;
	}

	public void setDrugComposition(String drugComposition) {
		this.drugComposition = drugComposition;
	}

	public String getDrugDescription() {
		return drugDescription;
	}

	public void setDrugDescription(String drugDescription) {
		this.drugDescription = drugDescription;
	}

	public String getDrugIntakeQuantity() {
		return drugIntakeQuantity;
	}

	public void setDrugIntakeQuantity(String drugIntakeQuantity) {
		this.drugIntakeQuantity = drugIntakeQuantity;
	}

	public String getDrugManufacturer() {
		return drugManufacturer;
	}

	public void setDrugManufacturer(String drugManufacturer) {
		this.drugManufacturer = drugManufacturer;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public double getDrugPrice() {
		return drugPrice;
	}

	public void setDrugPrice(double drugPrice) {
		this.drugPrice = drugPrice;
	}

	public String getDrugQuantity() {
		return drugQuantity;
	}

	public void setDrugQuantity(String drugQuantity) {
		this.drugQuantity = drugQuantity;
	}

	public String getDrugType() {
		return drugType;
	}

	public void setDrugType(String drugType) {
		this.drugType = drugType;
	}

	public String getGenericMedicineName() {
		return genericMedicineName;
	}

	public void setGenericMedicineName(String genericMedicineName) {
		this.genericMedicineName = genericMedicineName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
