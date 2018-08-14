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


}
