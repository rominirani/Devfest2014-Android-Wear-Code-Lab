package com.mindstorm.mumbaitrafficalerts;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class AppPreferences {

	public static final String KEY_TRAFFICEALERTS = "subscribetotrafficalerts";
	public static final String KEY_DND = "dnd";
	public static final String KEY_AIRTRAFFIC  = "airtraffic";
	public static final String KEY_RAILTRAFFIC = "railtraffic";
	public static final String KEY_ROADTRAFFIC = "roadtraffic";
	
	private SharedPreferences _sharedPrefs;
	private Editor _prefsEditor;

	public AppPreferences(Context context) {
		this._sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
		this._prefsEditor = _sharedPrefs.edit();
	}

	public boolean getTrafficAlertsPreference() {
		return _sharedPrefs.getBoolean(KEY_TRAFFICEALERTS, false);
	}
	
	public String getRoadTraffic() {
		return _sharedPrefs.getString(KEY_ROADTRAFFIC, "");
	}
	public String getAirTraffic() {
		return _sharedPrefs.getString(KEY_AIRTRAFFIC, "");
	}
	public String getRailTraffic() {
		return _sharedPrefs.getString(KEY_RAILTRAFFIC, "");
	}
	public boolean getDND() {
		return _sharedPrefs.getBoolean(KEY_DND, true);
	}
	
	public void saveAirTraffic(String text) {
		_prefsEditor.putString(KEY_AIRTRAFFIC, text);
		_prefsEditor.commit();
	}
	public void saveRailTraffic(String text) {
		_prefsEditor.putString(KEY_RAILTRAFFIC, text);
		_prefsEditor.commit();
	}
	public void saveRoadTraffic(String text) {
		_prefsEditor.putString(KEY_ROADTRAFFIC, text);
		_prefsEditor.commit();
	}

}