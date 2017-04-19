package com.redcarpet.customchrometab;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Bitmap mActionCallIcon, mActionCallLightIcon, mActionBackIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        Resources resources = getResources();
        mActionCallIcon = BitmapFactory.decodeResource(resources, R.drawable.ic_action_call);
        mActionCallLightIcon = BitmapFactory.decodeResource(resources, R.drawable.ic_action_call_light);
        mActionBackIcon = BitmapFactory.decodeResource(resources, R.drawable.ic_action_back);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChromeTab();
            }
        });
    }


    private void openChromeTab() {
       String url = "https://developer.chrome.com/multidevice/android/customtabs/";
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        // Set the Toolbar color

        builder.setToolbarColor(Color.parseColor("#32cd32"));



        // Display custom back button

        builder.setCloseButtonIcon(mActionBackIcon);

        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));



    }

}
