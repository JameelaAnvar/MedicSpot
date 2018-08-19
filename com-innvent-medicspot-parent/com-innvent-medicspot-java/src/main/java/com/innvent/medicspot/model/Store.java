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


@Getter
@Setter
@Entity
@Table(name = "store")
public class Store implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "store_id", columnDefinition = "uuid")
	@org.hibernate.annotations.Type(type = "pg-uuid")
	@GeneratedValue
	UUID storeId;

	@Column(name = "store_gid")
	String storeGid;

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

	public String getStoreGid() {
		return storeGid;
	}

	public void setStoreGid(String storeGid) {
		this.storeGid = storeGid;
	}

	public String getStorePlaceId() {
		return storePlaceId;
	}

	public void setStorePlaceId(String storePlaceId) {
		this.storePlaceId = storePlaceId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreLocationLatitude() {
		return storeLocationLatitude;
	}

	public void setStoreLocationLatitude(String storeLocationLatitude) {
		this.storeLocationLatitude = storeLocationLatitude;
	}

	public String getStoreLocationLongitude() {
		return storeLocationLongitude;
	}

	public void setStoreLocationLongitude(String storeLocationLongitude) {
		this.storeLocationLongitude = storeLocationLongitude;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
