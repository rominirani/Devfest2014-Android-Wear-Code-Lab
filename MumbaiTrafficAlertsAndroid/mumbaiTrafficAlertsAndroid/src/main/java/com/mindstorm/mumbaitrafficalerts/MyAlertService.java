package com.mindstorm.mumbaitrafficalerts;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import android.R;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

public class MyAlertService extends Service {
	
	public static final String TRAFFICALERT_ID = "1";
	
	public static String getCurrentDate() {
		final Calendar c = Calendar.getInstance();
		SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
		String currentDate = SDF.format(new Date(c.getTimeInMillis()));
		return currentDate;
	}
	
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		AppPreferences _Prefs = new AppPreferences(getBaseContext());
		//All the logic for determining if Alerts should be retrieved
		boolean bTrafficAlerts = _Prefs.getTrafficAlertsPreference();
		boolean bDND = _Prefs.getDND();
		
		final Calendar c = Calendar.getInstance();
		int hour_of_day = c.get(Calendar.HOUR_OF_DAY);
		
		//Traffic Alerts
		if (bTrafficAlerts) {
			if ((bDND) && ((hour_of_day >= 7) && (hour_of_day <= 20))) {
				TrafficAlertsRunnable runnable = new TrafficAlertsRunnable();
				Thread t = new Thread(runnable);
				t.start();
			}
		}
		
		return START_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void displayNotification(String alertId, Context ctx, String message)
    {
		
		//Pending Notification Activity
		Intent i = new Intent(ctx, NotificationViewActivity.class);
	    i.setData((Uri.parse("custom://"+System.currentTimeMillis())));
	    i.putExtra("notificationID", alertId);
	    i.putExtra("msg1", message);
	    i.putExtra("alertid", alertId);
	    PendingIntent pendingIntent = PendingIntent.getActivity(ctx, 0, i, 0);

	    //Build the Notification
	    //We give a title, message, Intent to launch in case user selects activity, Notification can be cancelled from anywhere 
	   	 NotificationCompat.Builder builder = new NotificationCompat.Builder(ctx);
	     builder.setContentTitle("Mumbai Traffic Alerts")
	             .setContentText(message)
	             .setContentIntent(pendingIntent)
	             .setAutoCancel(true)
	             .setSmallIcon(R.drawable.ic_dialog_alert);
	
	     NotificationManagerCompat.from(ctx).notify(Integer.parseInt(alertId), builder.build());
   	     playSound(ctx);	
   		
    }
	

	//Retrieve Daily Tide Information
	class TrafficAlertsRunnable implements Runnable { 
		   public TrafficAlertsRunnable() {
			   super();
		   }
		   public void run() { 
				try {
					String result = ApplicationController.getTrafficInformation();
					//Parse it out
					JSONObject jsonResult = new JSONObject(result);
					JSONArray items = jsonResult.getJSONArray("items");
					if (items != null) {
						JSONObject info = (JSONObject)items.get(0);
						String air = info.getString("data_air");
						String rail = info.getString("data_rail");
						String road = info.getString("data_road");
						
						String message = "";
						boolean bChange = false;
						if (!air.equalsIgnoreCase("Normal")) {
							bChange = true;
							message+= "Air Traffic Alert : " + air + "\r\n";
						}
						if (!rail.equalsIgnoreCase("Normal")) {
							bChange = true;
							message+= "Rail Alert : " + rail + "\r\n";
						}
						if (!road.equalsIgnoreCase("Normal")) {
							bChange = true;
							message+= "Road Traffic Alert : " + road + "\r\n";
						}					
						if (bChange) {
							displayNotification(TRAFFICALERT_ID, getBaseContext(),message);
						}
					}
				}
				catch(Exception ex) {
					Log.w("Mumbai Traffic Alerts", "Could not retrieve Alerts. Reason : " + ex.getMessage());
				}
		   } 
		}	
	
	public static void playSound(Context ctx) {
		Uri defaultRingtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

   		MediaPlayer mediaPlayer = new MediaPlayer();

   		try {
   		      mediaPlayer.setDataSource(ctx, defaultRingtoneUri);
   		      mediaPlayer.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
   		      mediaPlayer.prepare();
   		      mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

   		         @Override
   		         public void onCompletion(MediaPlayer mp)
   		         {
   		            mp.release();
   		         }
   		      });
   		  mediaPlayer.start();
   		} catch (Exception e) {
   		}
	}
}
