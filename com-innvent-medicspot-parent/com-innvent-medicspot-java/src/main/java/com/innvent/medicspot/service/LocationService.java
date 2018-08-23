package com.innvent.medicspot.service;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class LocationService {

	@Autowired
	RestTemplate restTemplate;

	final String nearbyStoresUri = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=value&radius=5000&types=pharmacy&key=keyValue";
	final String googleAPIKey = "AIzaSyBrx1Yf2QcskB22aFPBTzlpLWHuesWQXv4";
	final String placeDetailsUri = "https://maps.googleapis.com/maps/api/place/details/json?placeid=placeValue&key=keyValue";
	final String geoLocationCoordinatesUri = "https://www.googleapis.com/geolocation/v1/geolocate?key=keyValue";
	final String geoLocationAddressUri = "https://maps.googleapis.com/maps/api/geocode/json?latlng=value";

	public String fetchNearbyStoreGeoDetails(String lat, String lng) {
		String uri = nearbyStoresUri.replace("value", lat + "," + lng).replace("keyValue", googleAPIKey);
		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
		return response.getBody();
	}

	public String fetchPlaceDetails(String placeId) {

		String uri = placeDetailsUri.replace("placeValue", placeId).replace("keyValue", googleAPIKey);
		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
		return response.getBody();
	}

	public Map<String, String> fetchGeoLocationCoordinates() {
		Map<String, String> locationMap = new HashMap<>();
		String uri = geoLocationCoordinatesUri.replace("keyValue", googleAPIKey);
		ResponseEntity<String> response = restTemplate.postForEntity(uri, null, String.class);
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

	public Map<String, String> fetchCurrentAddress(String lat, String lng) {

		
		String uri = geoLocationAddressUri.replace("value", lat + "," + lng);
		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
		JSONObject object;
		Map<String, String> addressMap = new HashMap<>();
		try {
			object = new JSONObject(response.getBody());
			JSONArray results = object.getJSONArray("results");
			addressMap.put("address", (String) results.getJSONObject(0).get("formatted_address"));
			addressMap.put("placeId", (String) results.getJSONObject(0).get("place_id"));

		} catch (JSONException e) {
			// TODO Auto-generated catch block

		}
		return addressMap;
	}
}
