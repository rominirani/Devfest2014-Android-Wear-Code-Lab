package com.mindstorm.mumbaitrafficalerts;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyAlertBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {		
		
		//Start the Service
		Intent i = new Intent(context,MyAlertService.class);
		PendingIntent pi = PendingIntent.getService(context,0, i, PendingIntent.FLAG_UPDATE_CURRENT);
		
		//Alarm Manager
		AlarmManager AM = (AlarmManager) context.getSystemService(android.content.Context.ALARM_SERVICE);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		
		Calendar updateTime = Calendar.getInstance();

        updateTime.set(Calendar.SECOND, 5);

		//Cancel any existing intent if there is one, this will ensure that only one is there.
		AM.cancel(pi);
		long MINS_3 = 180000L;
		AM.setInexactRepeating(AlarmManager.RTC_WAKEUP, updateTime.getTimeInMillis(),MINS_3, pi);
	}
}
