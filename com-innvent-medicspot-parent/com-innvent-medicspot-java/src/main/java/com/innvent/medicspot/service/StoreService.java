package com.innvent.medicspot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;


import com.innvent.medicspot.dao.StoreRepository;
import com.innvent.medicspot.model.Store;
import com.innvent.medicspot.model.StoreDetails;

@org.springframework.stereotype.Service
public class StoreService {

	@Autowired
	private StoreRepository storeDao;
	
	@Autowired
	private LocationService locationService;
	
	
	public List<Store> fetchandSaveNearbyStoreGeoDetails(String lat, String lng) {

		List<Store> responseList = null;
		String response = locationService.fetchNearbyStoreGeoDetails(lat, lng);
		List<Store> storeList = new ArrayList<>();
		JSONObject object;
		try {
			object = new JSONObject(response);
			JSONArray results = (JSONArray) object.get("results");
			for (int i = 0; i < results.length(); i++) {

				JSONObject result = results.getJSONObject(i);
				JSONObject geometry = (JSONObject) result.get("geometry");
				JSONObject location = (JSONObject) geometry.get("location");
				if (result != null && geometry != null && location != null && result.get("id") != null
						&& result.get("place_id") != null && result.get("name") != null && location.get("lat") != null
						&& location.get("lng") != null) {

					storeList.add(new Store(UUID.randomUUID(), (String) result.get("id"),
							(String) result.get("place_id"), (String) result.get("name"),
							((Double) location.get("lat")).toString(), ((Double) location.get("lng")).toString()));
				}

			}
			responseList = (List<Store>) storeDao.saveAll(storeList);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return responseList;

	}

	public StoreDetails fetchPlaceDetails(String placeId) {
		StoreDetails storeDetails = null;
		String response = locationService.fetchPlaceDetails(placeId);
		try {
			JSONObject object = new JSONObject(response);
			JSONObject result = (JSONObject) object.get("result");
			JSONObject geometry = (JSONObject) result.get("geometry");
			JSONObject location = (JSONObject) geometry.get("location");
			storeDetails = new StoreDetails();
			storeDetails.setStoreGid((String) result.get("id"));
			storeDetails.setStorePlaceId((String) result.get("place_id"));
			storeDetails.setStoreName((String) result.get("name"));
			storeDetails.setStoreLocationLatitude(((Double) location.get("lat")).toString());
			storeDetails.setStoreLocationLongitude(((Double) location.get("lng")).toString());
			storeDetails.setAddress((String) result.get("formatted_address"));
			storeDetails.setPhoneNumber((String) result.get("formatted_phone_number"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

		return storeDetails;
	}

	

	public List<StoreDetails> fetchNearbyStoreDetails(String placeId) {
		String lat = "", lng = "";
		List<StoreDetails> storeDetailsList = null;
		try {

			if (placeId != null) {
				String response = locationService.fetchPlaceDetails(placeId);
				JSONObject location;
				JSONObject object = new JSONObject(response);
				JSONObject result = (JSONObject) object.get("result");
				if (result != null) {
					JSONObject geometry = (JSONObject) result.get("geometry");
					if (geometry != null) {
						location = (JSONObject) geometry.get("location");
						if (location != null && location.get("lat") != null && location.get("lng") != null) {
							lat = ((Double) location.get("lat")).toString();
							lng = ((Double) location.get("lng")).toString();
						}
					}
				}
			} else {
				Map<String,String> locationMap = locationService.fetchGeoLocationCoordinates();
				lat = locationMap.get("lat");
				lng = locationMap.get("lng");
			}

			List<Store> storeList = fetchandSaveNearbyStoreGeoDetails(lat, lng);
			storeDetailsList = storeList.stream().map(s -> {
				StoreDetails storeDetails = fetchPlaceDetails(s.getStorePlaceId());
				storeDetails.setStoreId(s.getStoreId().toString());
				return storeDetails;
			}).collect(Collectors.toList());

		} catch (

		JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return storeDetailsList;
	}
}
