package com.mindstorm.androidwearnotifications2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView mTextView = (TextView) findViewById(R.id.extraMessage); // TextView to retrieve the message
        ImageView mImageView = (ImageView) findViewById(R.id.extraPhoto); // ImageView to retrieve the picture

        // Get the intent information
        Intent extraIntent = getIntent();

// Get the intent information based on the names passed by your notificaiton "message" and
        mTextView.setText(extraIntent.getStringExtra("message")); // Retrieve the text and set it to our TextView
        mImageView.setImageResource(extraIntent.getIntExtra("photo", 0)); // The zero is a default value in case the intent extra is empty.
    }

}
