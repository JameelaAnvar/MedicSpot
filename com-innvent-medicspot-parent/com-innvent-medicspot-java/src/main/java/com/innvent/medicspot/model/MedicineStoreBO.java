package com.innvent.medicspot.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MedicineStoreBO {
	
	String storePlaceId;
	
	String storeName; 
	
	String storeAddress;
	
	String distance;
	
	String duration;
	
	double possibility;
	
	double score;
	
	double distanceNum;
	
	double durationNum;

	public MedicineStoreBO(String storePlaceId, String storeName, String storeAddress, String distance, String duration,
			double possibility, double score, double distanceNum, double durationNum) {
		super();
		this.storePlaceId = storePlaceId;
		this.storeName = storeName;
		this.storeAddress = storeAddress;
		this.distance = distance;
		this.duration = duration;
		this.possibility = possibility;
		this.score = score;
		this.distanceNum = distanceNum;
		this.durationNum = durationNum;
	}

}
