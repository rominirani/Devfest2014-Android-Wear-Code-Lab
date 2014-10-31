package com.mindstorm.awn;

import android.app.Activity;
import android.app.Notification;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    protected NotificationManagerCompat mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNotificationManager = NotificationManagerCompat.from(getBaseContext());
    }

    public void raiseNotification(View view) {
        //Toast.makeText(getBaseContext(), "Raise Notification",Toast.LENGTH_SHORT).show();
        step1();
        //step2();
        //step3();
        //step4();
    }

    public List<AuctionItem> getAuctionItems() {
        List items = new ArrayList<AuctionItem>();
        items.add(new AuctionItem("1","Moto G Phone","8,999","3 hours","5"));
        items.add(new AuctionItem("2","Moto X Phone","22,999","2 hours","2"));
        items.add(new AuctionItem("3","Samsung Note Phone","30,999","1 hours","7"));
        return items;
    }

    public void step1() {
        //Retrieve from Server but here we will build it locally
        List<AuctionItem> items = getAuctionItems();
        int notificationId = 1;
        //For each of the items raise a notification
        for (AuctionItem item : items) {

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_stat_action_alarm_on)
                    .setContentTitle(this.getString(R.string.title_auction_update))
                    .setContentText(item.getTitle());

            mNotificationManager.notify(notificationId++, notificationBuilder.build());
        }
    }

    public void step2() {
        //Retrieve from Server but here we will build it locally
        List<AuctionItem> items = getAuctionItems();
        int notificationId = 1;

        //Let's build a Wearable Extender with a background
        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender();

        wearableExtender.setBackground(
                BitmapFactory.decodeResource(
                        getResources(), R.drawable.notification_background));
        //For each of the items raise a notification
        for (AuctionItem item : items) {

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_stat_action_alarm_on)
                    .setContentTitle(this.getString(R.string.title_auction_update))
                    .setContentText(item.getTitle())
                    .extend(wearableExtender);

            mNotificationManager.notify(notificationId++, notificationBuilder.build());
        }
    }

    public void step3() {
        //Retrieve from Server but here we will build it locally
        List<AuctionItem> items = getAuctionItems();
        int notificationId = 1;

        //Notification Group Key
        String NOTIFICATION_GROUP_KEY = "1";

        //Let's build a Wearable Extender with a background
        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender();

        wearableExtender.setBackground(
                BitmapFactory.decodeResource(
                        getResources(), R.drawable.notification_background));
        //For each of the items raise a notification
        for (AuctionItem item : items) {

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_stat_action_alarm_on)
                    .setContentTitle(this.getString(R.string.title_auction_update))
                    .setContentText(item.getTitle())
                    .setGroup(NOTIFICATION_GROUP_KEY)
                    .extend(wearableExtender);

            mNotificationManager.notify(notificationId++, notificationBuilder.build());
        }
    }

    public void step4() {
        //Retrieve from Server but here we will build it locally
        List<AuctionItem> items = getAuctionItems();
        int notificationId = 1;

        //Notification Group Key
        String NOTIFICATION_GROUP_KEY = "1";

        //For each of the items raise a notification
        for (AuctionItem item : items) {

            NotificationCompat.BigTextStyle autionDetailsPageStyle =
                    new NotificationCompat.BigTextStyle()
                            .setBigContentTitle(this.getString(R.string.title_auction_details))
                            .bigText(item.toString());

            Notification detailsPageNotification = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_stat_action_alarm_on)
                    .setStyle(autionDetailsPageStyle)
                    .build();

            //Let's build a Wearable Extender with a background
            NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender();

            wearableExtender.setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.notification_background));
            wearableExtender.addPage(detailsPageNotification);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_stat_action_alarm_on)
                    .setContentTitle(this.getString(R.string.title_auction_update))
                    .setContentText(item.getTitle())
                    .setGroup(NOTIFICATION_GROUP_KEY)
                    .extend(wearableExtender);

            mNotificationManager.notify(notificationId++, notificationBuilder.build());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
