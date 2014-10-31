package com.mindstorm.mumbaitrafficalerts;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class AppPreferencesActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener {

	public static final String KEY_TRAFFICEALERTS = "subscribetotrafficalerts";
	public static final String KEY_DND = "dnd";
	private CheckBoxPreference mTrafficAlerts;
	private CheckBoxPreference mDND;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		mTrafficAlerts = (CheckBoxPreference) getPreferenceScreen().findPreference(KEY_TRAFFICEALERTS);
		mDND = (CheckBoxPreference) getPreferenceScreen().findPreference(KEY_DND);

		Intent i = new Intent(getBaseContext(),MyAlertService.class);
		PendingIntent pi = PendingIntent.getService(getBaseContext(),0, i, PendingIntent.FLAG_UPDATE_CURRENT);
		
		//Alarm Manager
		AlarmManager AM = (AlarmManager)getSystemService(ALARM_SERVICE);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		
		//Cancel any existing intent if there is one, this will ensure that only one is there.
		AM.cancel(pi);
		long MINS_3 = 180000L;
		AM.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),MINS_3, pi);
		
	}
	
	@Override
    protected void onResume() {
        super.onResume();
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        
        // Set up a listener whenever a key changes
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the listener whenever a key changes
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
	}
}
