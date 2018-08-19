package com.innvent.medicspot.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.innvent.medicspot.dao.StoreRepository;
import com.innvent.medicspot.model.Store;

@org.springframework.stereotype.Service
public class StoreService {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private StoreRepository storeRepository;

	final String nearbyStoresUri = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=value&radius=5000&keyword=pharmacy&key=keyValue";
	final String googleAPIKey = "AIzaSyBrx1Yf2QcskB22aFPBTzlpLWHuesWQXv4";

	public List<Store> fetchNearbyStoreGeoDetails(String lat, String lng) {

		List<Store> responseList = null;
		String uri = nearbyStoresUri.replace("value", lat + "," + lng).replace("keyValue", googleAPIKey);
		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
		List<Store> storeList = new ArrayList<>();
		JSONObject object;
		try {
			object = new JSONObject(response.getBody());
			JSONArray results = (JSONArray) object.get("results");
			for (int i = 0; i < results.length(); i++) {

				JSONObject result = results.getJSONObject(i);
				JSONObject geometry = (JSONObject) result.get("geometry");
				JSONObject location = (JSONObject) geometry.get("location");
				storeList.add(new Store(null, (String) result.get("id"), (String) result.get("place_id"),
						(String) result.get("name"), ((Double) location.get("lat")).toString(),
						((Double) location.get("lng")).toString()));

			}
			responseList = (List<Store>) storeRepository.saveAll(storeList);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return responseList;

	}
}
