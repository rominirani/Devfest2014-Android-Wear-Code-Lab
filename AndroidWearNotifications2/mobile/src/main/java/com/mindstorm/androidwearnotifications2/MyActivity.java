package com.mindstorm.androidwearnotifications2;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.RemoteInput;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MyActivity extends Activity {
    NotificationManagerCompat nManager;

    public static final int SIMPLE_NOT = 1;
    public static final int BIG_NOT = 2;
    public static final int MULTIPLE_PAGES_NOT = 3;
    public static final int ACTION_NOT = 4;
    public static final int STACKED_NOT_1 = 5;
    public static final int STACKED_NOT_2 = 6;
    public static final int STACKED_GROUP = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        nManager = NotificationManagerCompat.from(this);

    }

    public void send_simple_notification (View v) {
        Intent directoryEvent = new Intent(Intent.ACTION_VIEW);
        directoryEvent.setData(Uri.parse("http://gdgindore.in/devfest14"));

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, directoryEvent, 0);

        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this)
                .setContentText("Check out GDG Indore!")
                .setContentTitle("GDG Indore")
                .addAction(R.drawable.ic_stat_action_alarm_on, "Open Page", pendingIntent)
                .setSmallIcon(R.drawable.ic_stat_action_alarm_on);

        nManager.notify(SIMPLE_NOT, nBuilder.build());
    }

    public void send_simple_notification2 (View v) {
        Intent directoryEvent = new Intent(Intent.ACTION_VIEW);
        directoryEvent.setData(Uri.parse("http://gdgindore.in/devfest14"));

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, directoryEvent, 0);

        //Let's build a Wearable Extender with a background
        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender();
        wearableExtender.setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.notification_background));

        NotificationCompat.Action wearable_action = new NotificationCompat.Action.Builder(
                R.drawable.ic_stat_action_alarm_on,
                "Check out page",
                pendingIntent).build();
        wearableExtender.addAction(wearable_action);

        /*NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this)
                .setContentText("Check out GDG Indore!")
                .setContentTitle("GDG Indore")
                .addAction(R.drawable.ic_stat_action_alarm_on, "Open Page", pendingIntent)
                .setSmallIcon(R.drawable.ic_stat_action_alarm_on)
                .extend(wearableExtender);*/

        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this)
                .setContentText("Check out GDG Indore!")
                .setContentTitle("GDG Indore")
                .setSmallIcon(R.drawable.ic_stat_action_alarm_on)
                .extend(wearableExtender);

        nManager.notify(SIMPLE_NOT, nBuilder.build());
    }


    public void send_big_notification (View v) {

        Intent intent = new Intent(this,MyActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        String eventDescription = "This is supposed to  be a content that will not fit the normal " +
           "content screen usually a bigger text, by example a long text message or email.";
        NotificationCompat.BigTextStyle bigStyle = new NotificationCompat.BigTextStyle();
        bigStyle.bigText(eventDescription); // bigText will override setContentText
        bigStyle.setBigContentTitle("Override Title"); // bigContentTitle Override the contentTitle

        //Let's build a Wearable Extender with a background
        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender();
        wearableExtender.setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.notification_background));

        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this)
                .setStyle(bigStyle)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_stat_action_alarm_on)
                .extend(wearableExtender);


        nManager.notify(BIG_NOT, nBuilder.build());
    }


    public void send_pages_notification (View v) {

        //First, we are creating content for each of the notification pages

        //Page 1
        String string1 = "String 1";
        NotificationCompat.BigTextStyle bigStyle1 = new NotificationCompat.BigTextStyle();
        bigStyle1.setBigContentTitle("Title 1").bigText(string1);

        //Page 2
        String string2 = "String 2";
        NotificationCompat.BigTextStyle bigStyle2 = new NotificationCompat.BigTextStyle();
        bigStyle2.setBigContentTitle("Title 2").bigText(string2);

        //Page 3
        String string3 = "String 3";
        NotificationCompat.BigTextStyle bigStyle3 = new NotificationCompat.BigTextStyle();
        bigStyle3.setBigContentTitle("Title 3").bigText(string3);

        //Now we are creating the 3 Notification objects (Children)

        //Notification 1
        Notification notification1 = new NotificationCompat.Builder(this)
                .setStyle(bigStyle1)
                .setSmallIcon(R.drawable.ic_stat_action_alarm_on)
                .build();

        //Notification 2
        Notification notification2 = new NotificationCompat.Builder(this)
                .setStyle(bigStyle2)
                .setSmallIcon(R.drawable.ic_stat_action_alarm_on)
                .build();

        //Notification 3
        Notification notification3 = new NotificationCompat.Builder(this)
                .setStyle(bigStyle3)
                .setSmallIcon(R.drawable.ic_stat_action_alarm_on)
                .build();

        //Let's build a Wearable Extender with a background and pages
        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender();
        wearableExtender.setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.notification_background));
        wearableExtender.addPage(notification1);
        wearableExtender.addPage(notification2);
        wearableExtender.addPage(notification3);

        //Root Notification Builder
        NotificationCompat.Builder rootNotificationBuilder = new NotificationCompat.Builder(this)
                .setContentText("Check out multiple pages per notification")
                .setContentTitle("Root Notification")
                .setSmallIcon(R.drawable.ic_stat_action_alarm_on)
                .extend(wearableExtender);

        nManager.notify(MULTIPLE_PAGES_NOT, rootNotificationBuilder.build());
    }

    public void send_notification_with_action(View view) {

        //Default Intent : MyActivity
        Intent intent = new Intent(this,MyActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);


        // Intent pointing to our second activity
        Intent photoIntent = new Intent(this, SecondActivity.class);
        // Extra String to be passed to a intent
        String intentExtra = "This is an extra String!";
        // Set the extra message that will open in the next activity
        photoIntent.putExtra("message", intentExtra);
        // Send the photo to the next activity
        photoIntent.putExtra("photo", R.drawable.ic_launcher);

        // set a new pending intent
        PendingIntent photoPending = PendingIntent.getActivity(this, 0, photoIntent, 0);
        NotificationCompat.BigTextStyle bigStyle = new NotificationCompat.BigTextStyle();
        bigStyle.setBigContentTitle("Mr. Android Wear"); // title for the Big Text
        bigStyle.bigText("Check out this picture!! :D"); // Message in the Big Text
        Notification notif = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_action_alarm_on) // Small icon for our notification
                .setContentIntent(pendingIntent) // This will be the default OPEN button.
                .addAction(R.drawable.ic_stat_action_alarm_on, "See Photo", photoPending) //Extra Action
                .setAutoCancel(true)
                .setStyle(bigStyle) // Add the bigStyle
                .build();

        NotificationManagerCompat notificationManager =  NotificationManagerCompat.from(this);
        notificationManager.notify(ACTION_NOT, notif);

    }

    public void send_stacked_notification(View view) {

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // Create an InboxStyle notification
        Notification summaryNotification = new NotificationCompat.Builder(this)
                .setContentTitle("2 new messages")
                .setSmallIcon(R.drawable.ic_stat_action_alarm_on)
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("Aryan Irani  Get toys")
                        .addLine("Neil Irani   Get more toys")
                        .setBigContentTitle("2 new messages")
                        .setSummaryText("romin.k.irani@gmail.com"))
                .setGroup("GROUP_KEY_1")
                .setGroupSummary(true)
                .build();

        notificationManager.notify(STACKED_GROUP, summaryNotification);

        // Build the notification, setting the group appropriately
        Notification notif1 = new NotificationCompat.Builder(this)
                .setContentTitle("Neil Irani")
                .setContentText("Get more toys for me.")
                .setSmallIcon(R.drawable.ic_stat_action_alarm_on)
                .setGroup("GROUP_KEY_1")
                .build();

        notificationManager.notify(STACKED_NOT_1, notif1);

        Notification notif2 = new NotificationCompat.Builder(this)
                .setContentTitle("Aryan Irani")
                .setContentText("Get toys for me.")
                .setSmallIcon(R.drawable.ic_stat_action_alarm_on)
                .setGroup("GROUP_KEY_1")
                .build();

        notificationManager.notify(STACKED_NOT_2, notif2);

    }
}