package com.redcarpet.customchrometab;

import android.app.PendingIntent;
import android.content.Intent;
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

        // Add custom entrance/exit animations

        builder.setStartAnimations(this, R.anim.slide_in_right, R.anim.slide_out_left);
        builder.setExitAnimations(this, android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        // Add action button

        Intent actionIntent = new Intent(Intent.ACTION_DIAL);
        actionIntent.setData(Uri.parse("tel:18001234567"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, actionIntent, 0);
        builder.setActionButton(mActionCallIcon, "Call", pendingIntent);

        // Add custom menu items

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this website: " + "");
        PendingIntent menuIntent = PendingIntent.getActivity(this, 0, shareIntent, 0);
        builder.addMenuItem("Share Textx", menuIntent);


        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));



    }

}
