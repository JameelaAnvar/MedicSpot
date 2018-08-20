package com.innvent.medicspot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.innvent.medicspot.dao.StoreRepository;
import com.innvent.medicspot.model.Store;
import com.innvent.medicspot.model.StoreDetails;

@org.springframework.stereotype.Service
public class StoreService {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private StoreRepository storeDao;

	final String nearbyStoresUri = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=value&radius=5000&types=pharmacy&key=keyValue";
	final String googleAPIKey = "AIzaSyBrx1Yf2QcskB22aFPBTzlpLWHuesWQXv4";
	final String placeDetailsUri = "https://maps.googleapis.com/maps/api/place/details/json?placeid=placeValue&key=keyValue";
	final String geoLocateUri = "https://www.googleapis.com/geolocation/v1/geolocate?key=keyValue";

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
		String uri = placeDetailsUri.replace("placeValue", placeId).replace("keyValue", googleAPIKey);
		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
		try {
			JSONObject object = new JSONObject(response.getBody());
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

	public Map<String, String> getGeoLocationCordinates() {
		Map<String, String> locationMap = new HashMap<>();
		String uri = geoLocateUri.replace("keyValue", googleAPIKey);
		// HttpHeaders headers = new HttpHeaders();
		// headers.setContentType(MediaType.APPLICATION_JSON);
		// HttpEntity<String> entity = new HttpEntity<String>("", headers);
		ResponseEntity<String> response = restTemplate.postForEntity(uri, null, String.class);
		// exchange(uri, HttpMethod.POST, entity, String.class);

		try {
			JSONObject object = new JSONObject(response.getBody());
			if (object != null) {
				JSONObject location = object.getJSONObject("location");
				if (location != null && location.get("lat") != null && location.get("lng") != null) {
					locationMap.put("lat", ((Double) location.get("lat")).toString());
					locationMap.put("lng", ((Double) location.get("lng")).toString());
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return locationMap;
	}

	public List<StoreDetails> fetchNearbyStoreDetails(String placeId) {
		String lat = "", lng = "";
		List<StoreDetails> storeDetailsList = null;
		try {

			if (placeId != null) {
				String uri = placeDetailsUri.replace("placeValue", placeId).replace("keyValue", googleAPIKey);
				ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
				JSONObject location;
				JSONObject object = new JSONObject(response.getBody());
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
				Map<String, String> locationMap = getGeoLocationCordinates();
				lat = locationMap.get("lat");
				lng = locationMap.get("lng");
			}

			List<Store> storeList = fetchNearbyStoreGeoDetails(lat, lng);
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
