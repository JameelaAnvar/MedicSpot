package com.innvent.medicspot.service;

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

	public String fetchCurrentPlaceDetails() {
		String latUri = "https://www.googleapis.com/geolocation/v1/geolocate?key=AIzaSyBrx1Yf2QcskB22aFPBTzlpLWHuesWQXv4";
		ResponseEntity<String> latResponse = restTemplate.postForEntity(latUri, null, String.class);
		String latitude = "", longitude = "";
		JSONObject object;
		try {
			object = new JSONObject(latResponse.getBody());
			JSONObject results = object.getJSONObject("location");
			latitude = (String) results.get("lat").toString();
			longitude = (String) results.get("lng").toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			return "Unknown location !";
		}
		String uri = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + latitude + "," + longitude
				+ "&key=AIzaSyBrx1Yf2QcskB22aFPBTzlpLWHuesWQXv4";
		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
		JSONObject object1;
		try {
			object1 = new JSONObject(response.getBody());
			JSONArray results = object1.getJSONArray("results");
			String location = (String) results.getJSONObject(0).get("formatted_address");
			System.out.println("\n\n Result is : " + location + "\n\n");
			if(location.length()>50)
	        {
	            location = location.substring(0,50);
	            String arr[] = location.split(" ");
	            location = arr[0];
	            for(int i =1;i<arr.length-1;i++)
	            {
	                location = location + " " +arr[i];
	            }
	        }
			return location;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			return "Unknown location !";
		}
	}
}
