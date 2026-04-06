package com.amazesk.a295metaaudencenetworkallads;
import android.app.Application;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkAds;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AudienceNetworkAds.initialize(this);
        AdSettings.addTestDevice("35098827-575c-4440-8690-20d4affad9f3");
        AdSettings.addTestDevice("6f83db7d-f9b2-464c-9580-b19cddc54b1f");


    }
}
