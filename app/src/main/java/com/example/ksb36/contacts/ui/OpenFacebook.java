package com.example.ksb36.contacts.ui;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


public class OpenFacebook extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isAppInstalled()) {
            Toast.makeText(getApplicationContext(), "Facebook App already installed", Toast.LENGTH_SHORT).show();
            Intent fbIntent = new Intent(Intent.ACTION_VIEW);
            String facebookUrl = getFacebookPageUrl(context);
            fbIntent.setData(Uri.parse(facebookUrl));
            startActivity(fbIntent);
        } else {
            Toast.makeText(getApplicationContext(), "Facebook App not installed", Toast.LENGTH_SHORT).show();
        }
    }

    public static String FACEBOOK_URL = "https://www.facebook.com/";
    public static String FACEBOOK_PAGE_ID = "pg/edakent/about/?ref=page_internal";

    public String getFacebookPageUrl(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.orca", 0).versionCode;
            if (versionCode >= 3002850) {
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else {
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL;
        }
    }

    public boolean isAppInstalled() {
        try {
            getApplicationContext().getPackageManager().getApplicationInfo("com.facebook.katana", 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
