package com.innvent.medicspot.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "store")
public class Store implements Serializable {
	private static final long serialVersionUID = 1L;


	@Column(name = "store_id", columnDefinition = "uuid")
	@org.hibernate.annotations.Type(type = "pg-uuid")
	@GeneratedValue
	UUID storeId;

	@Column(name = "store_gid")
	String storeGid;

	@Id
	@Column(name = "store_placeid")
	String storePlaceId;

	@Column(name = "store_name")
	String storeName;

	@Column(name = "store_location_latitude")
	String storeLocationLatitude;

	@Column(name = "store_location_longitude")
	String storeLocationLongitude;

	public UUID getStoreId() {
		return storeId;
	}

	public void setStoreId(UUID storeId) {
		this.storeId = storeId;
	}

	public Store(UUID storeId, String storeGid, String storePlaceId, String storeName, String storeLocationLatitude,
			String storeLocationLongitude) {
		super();
		this.storeId = storeId;
		this.storeGid = storeGid;
		this.storePlaceId = storePlaceId;
		this.storeName = storeName;
		this.storeLocationLatitude = storeLocationLatitude;
		this.storeLocationLongitude = storeLocationLongitude;
	}

}
