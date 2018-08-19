package com.innvent.medicspot.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class StoreDetails {

	String storeId;
	String storeGid;
	String storePlaceId;
	String storeName;
	String storeLocationLatitude;
	String storeLocationLongitude;	
	String address;	
	String phoneNumber;
	
	public StoreDetails(String storeId, String storeGid, String storePlaceId, String storeName,
			String storeLocationLatitude, String storeLocationLongitude, String address, String phoneNumber) {
		super();
		this.storeId = storeId;
		this.storeGid = storeGid;
		this.storePlaceId = storePlaceId;
		this.storeName = storeName;
		this.storeLocationLatitude = storeLocationLatitude;
		this.storeLocationLongitude = storeLocationLongitude;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	
	
}
