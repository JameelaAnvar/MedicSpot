package com.innvent.medicspot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "med_store_map")
public class MedicineStoreDO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	String id;
	
	@Column(name = "store_id")
	String storeId;
	
	@Column(name = "medicine_id")
	String medicineId;
	
	@Column(name = "count")
	long count;
	
	@Column(name = "is_confirmed")
	boolean isConfirmed;
}
