package com.mindstorm.mumbaitrafficalerts;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.os.Bundle;
import android.widget.TextView;

public class NotificationViewActivity extends Activity {
	
	private String notificationMessage="";
	String alertId = "";
	AlertDialog alert = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification);
		
		//---look up the notification manager service---
		NotificationManager nm = (NotificationManager) 
                                        getSystemService(NOTIFICATION_SERVICE);
		
		//We get the handle to the TextView
		TextView tv1 = (TextView)findViewById(R.id.txtNotificationMessage);
		
		// Then we set the message retrieved from the Intent.
		String msg = getIntent().getExtras().getString("msg1");
		alertId = getIntent().getExtras().getString("alertid");
		notificationMessage = msg;
		tv1.setText(notificationMessage);
		
		//Important : cancel the notification that we started so that it goes away 
        //from the Notification Bar
		nm.cancel(Integer.parseInt(alertId));
	}
}

