package com.mindstorm.mumbaitrafficalerts;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Application;

public class ApplicationController extends Application {

	// Preferable to expose them via getter/setter methods
	@Override
	public void onCreate() {
		super.onCreate();
		// Do Application initialization over here
	}

	// Get Traffic Information
	public static String getTrafficInformation() {

		String urlStr = "https://mumbaimonsoonalerts.appspot.com/_ah/api/bmctrafficalertsendpoint/v1/bmctrafficalerts";
		String response = "";
		HttpClient client = new DefaultHttpClient();

		try {
			HttpResponse hr = client.execute(new HttpGet(urlStr));
			HttpEntity entity = hr.getEntity();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					entity.getContent()));

			String buff = null;
			while ((buff = br.readLine()) != null)
				response += buff;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}