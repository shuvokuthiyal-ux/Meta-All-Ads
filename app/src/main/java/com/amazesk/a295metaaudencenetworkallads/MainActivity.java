package com.amazesk.a295metaaudencenetworkallads;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeAdView;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private AdView adViewBanner;
    LinearLayout banner_container;
    int clicksBanner, clicksFullSAdd;
    Button showFullScreenAdsBtn,rewardedVideoAds;
    private InterstitialAd interstitialAd;
    private NativeAd nativeAd;
    private RewardedVideoAd rewardedVideoAd;


    private final String TAG = MainActivity.class.getSimpleName();
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    HashMap<String,String>hashMap;
    RecyclerView recyclerViewId;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Alhamdulillah fully working version Meta Audence Network using 2 minutes timer set using SharedPreferences Banner,FullScreen
        //Alhamdulillah fully working version Meta Audence Network using 2 minutes timer set using SharedPreferences Banner,FullScreen
        //Alhamdulillah fully working version Meta Audence Network using 2 minutes timer set using SharedPreferences Banner,FullScreen
        //Compleately OOPS concepts

        //all elements find hear start
        banner_container = findViewById(R.id.banner_container);
        showFullScreenAdsBtn = findViewById(R.id.showFullScreenAdsBtn);
        rewardedVideoAds = findViewById(R.id.rewardedVideoAds);
        recyclerViewId = findViewById(R.id.recyclerViewId);

        //all elements find hear start
        loadFullScreenAds();


        // SharedPreferences setup
        SharedPreferences prefs = getSharedPreferences("AdsControl", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        //Banner ads start
        adViewBanner = new AdView(this, "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID", AdSize.BANNER_HEIGHT_50);
        banner_container.addView(adViewBanner);
        loadBannerAds();

        // Banner ads timer reset start
        long lastTime = prefs.getLong("lastTime", 0);
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime > 120000) {
            editor.putInt("clickCount", 0);
            editor.putLong("lastTime", currentTime);
            editor.apply();
        }
        //Banner ads ends

        // Fullscreen ads timer reset
        long lastTimeFAds = prefs.getLong("lastTimeFAds", 0);
        long currentTimeFAds = System.currentTimeMillis();
        if (currentTimeFAds - lastTimeFAds > 120000) {
            editor.putInt("clickCountFAds", 0);
            editor.putLong("lastTimeFAds", currentTimeFAds);
            editor.apply();
        }

        //Full screen start
        showFullScreenAdsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (interstitialAd!=null && interstitialAd.isAdLoaded()){
                    interstitialAd.show();
                }else {
                    Toast.makeText(MainActivity.this, "Sorry Full screen ads currently not able to show", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Full screen start
        loadRewardedVideoAds();

        //Rewarded ads start
        rewardedVideoAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rewardedVideoAd!=null){
                    rewardedVideoAd.show();
                }
            }
        });
        //Rewarded ads start


        //Native ads start

        arrayList = ContentList.getContentList();

        recyclerViewId.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewId.setAdapter(new MyAdapter());

        loadNativeAd();

        //Native ads end
    }




    //BannerAds load start
    private void loadBannerAds() {
        SharedPreferences prefs = getSharedPreferences("AdsControl", MODE_PRIVATE);
        clicksBanner = prefs.getInt("clickCount", 0);

        if (clicksBanner >= 2) {
            banner_container.setVisibility(GONE);
            Toast.makeText(this, "Ads limit reached. Ads will show again after reset.", Toast.LENGTH_SHORT).show();
            return;
        }

        AdListener adListener = new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
// Ad error callback
                Toast.makeText(
                                MainActivity.this,
                                "Error: " + adError.getErrorMessage(),
                                Toast.LENGTH_LONG)
                        .show();
            }

            @Override
            public void onAdLoaded(Ad ad) {
// Ad loaded callback
            }

            @Override
            public void onAdClicked(Ad ad) {
                SharedPreferences prefs = getSharedPreferences("AdsControl", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();

                int bannerAdsClicked = prefs.getInt("clickCount", 0);
                bannerAdsClicked++;
                editor.putInt("clickCount", bannerAdsClicked);
                if (bannerAdsClicked == 1) {
                    editor.putLong("lastTime", System.currentTimeMillis());
                }
                editor.apply();
                Toast.makeText(MainActivity.this, "Clicked"+bannerAdsClicked, Toast.LENGTH_SHORT).show();
                if (bannerAdsClicked >= 2 && adViewBanner != null) {
                    adViewBanner.destroy();
                    banner_container.removeAllViews();
                    banner_container.setVisibility(GONE);
                    adViewBanner = null;
                }
            }

            @Override
            public void onLoggingImpression(Ad ad) {
// Ad impression logged callback
// Please refer to Monetization Manager or Reporting API for final impression numbers
            }
        };

// Request an ad
        adViewBanner.loadAd(adViewBanner.buildLoadAdConfig().withAdListener(adListener).build());

    }
    //BannerAds load ends

    //Full screen ads method start
    private void loadFullScreenAds() {
        // Instantiate an InterstitialAd object.
        // NOTE: the placement ID will eventually identify this as your App, you can ignore it for
        // now, while you are testing and replace it later when you have signed up.
        // While you are using this temporary code you will only get test ads and if you release
        // your code like this to the Google Play your users will not receive ads (you will get a no fill error).
        SharedPreferences prefs = getSharedPreferences("AdsControl", MODE_PRIVATE);
        clicksFullSAdd = prefs.getInt("clickCountFAds", 0);

        if (clicksFullSAdd >= 2) {
            Toast.makeText(this, "Ads limit reached FAds. Ads will show again after reset.", Toast.LENGTH_SHORT).show();
            return;
        }

        interstitialAd = new InterstitialAd(this, "YOUR_PLACEMENT_ID");
        // Create listeners for the Interstitial Ad
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                Log.e(TAG, "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                Log.e(TAG, "Interstitial ad dismissed.");
                loadFullScreenAds();
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d(TAG, "Interstitial ad clicked!");
//                SharedPreferences prefs = getSharedPreferences("AdsControl", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();

                clicksFullSAdd = prefs.getInt("clickCountFAds", 0);
                clicksFullSAdd++;

                editor.putInt("clickCountFAds", clicksFullSAdd);
                if (clicksFullSAdd == 1) {
                    editor.putLong("lastTimeFAds", System.currentTimeMillis());
                }
                editor.apply();
                Toast.makeText(MainActivity.this, "Clicked FullScreen ads", Toast.LENGTH_SHORT).show();
                if (clicksFullSAdd >= 2 && interstitialAd != null) {
                    interstitialAd.destroy();
                }
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                // Please refer to Monetization Manager or Reporting API for final impression numbers
                Log.d(TAG, "Interstitial ad impression logged!");
            }
        };

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        // load the ad
        interstitialAd.loadAd(
                interstitialAd.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener)
                        .build());

    }
    //Full screen ads method end
    //Load native ads start
    private void loadNativeAd() {
        nativeAd = new NativeAd(this, "YOUR_PLACEMENT_ID");

        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {
                // Native ad finished downloading all assets
                Log.e(TAG, "Native ad finished downloading all assets.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Native ad failed to load
                Log.e(TAG, "Native ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Native ad is loaded and ready to be displayed
                Log.d(TAG, "Native ad is loaded and ready to be displayed!");
                recyclerViewId.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Native ad clicked
                Log.d(TAG, "Native ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Native ad impression
                Log.d(TAG, "Native ad impression logged!");
            }
        };

        // Request an ad
        // Initiate a request to load an ad.
        nativeAd.loadAd(
                nativeAd.buildLoadAdConfig()
                        .withAdListener(nativeAdListener)
                        .withMediaCacheFlag(NativeAdBase.MediaCacheFlag.ALL)
                        .build());
    }


    //Load native ads end

    //Load Rewarded video ads start
    private void loadRewardedVideoAds() {
        // Instantiate a RewardedVideoAd object.
        // NOTE: the placement ID will eventually identify this as your App, you can ignore it for
        // now, while you are testing and replace it later when you have signed up.
        // While you are using this temporary code you will only get test ads and if you release
        // your code like this to the Google Play your users will not receive ads (you will get
        // a no fill error).
        rewardedVideoAd = new RewardedVideoAd(this, "YOUR_PLACEMENT_ID");
        RewardedVideoAdListener rewardedVideoAdListener = new RewardedVideoAdListener() {
            @Override
            public void onError(Ad ad, AdError error) {
                // Rewarded video ad failed to load
                Log.e(TAG, "Rewarded video ad failed to load: " + error.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Rewarded video ad is loaded and ready to be displayed
                Log.d(TAG, "Rewarded video ad is loaded and ready to be displayed!");
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Rewarded video ad clicked
                Log.d(TAG, "Rewarded video ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Rewarded Video ad impression - the event will fire when the
                // video starts playing
                Log.d(TAG, "Rewarded video ad impression logged!");
            }

            @Override
            public void onRewardedVideoCompleted() {
                // Rewarded Video View Complete - the video has been played to the end.
                // You can use this event to initialize your reward
                Log.d(TAG, "Rewarded video completed!");
                Toast.makeText(MainActivity.this, "Congratulations you get", Toast.LENGTH_SHORT).show();
                // Call method to give reward
                // giveReward();
            }

            @Override
            public void onRewardedVideoClosed() {
                // The Rewarded Video ad was closed - this can occur during the video
                // by closing the app, or closing the end card.
                Log.d(TAG, "Rewarded video ad closed!");
            }
        };
        rewardedVideoAd.loadAd(
                rewardedVideoAd.buildLoadAdConfig()
                        .withAdListener(rewardedVideoAdListener)
                        .build());
    }
    //Load Rewarded video ads end

    //Adapter recyclerview start
    private class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        int PROFILE = 0;
        int ADS = 1;
        private class ProfileAViewHolder extends RecyclerView.ViewHolder{
            TextView profile_name;
            LinearLayout layoutProfileId;

            public ProfileAViewHolder(@NonNull View itemView) {
                super(itemView);
                profile_name = itemView.findViewById(R.id.profile_name);
                layoutProfileId = itemView.findViewById(R.id.layoutProfileId);
            }
        }
        private class AdsViewHolder extends RecyclerView.ViewHolder{
            LinearLayout native_ad_container;
            public AdsViewHolder(@NonNull View itemView) {
                super(itemView);
                native_ad_container = itemView.findViewById(R.id.native_ad_container);
            }
        }
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();
            if (viewType==PROFILE){
                View view = layoutInflater.inflate(R.layout.layout_profile,parent,false);
                return new ProfileAViewHolder(view);
            }else {
                View view = layoutInflater.inflate(R.layout.layout_ads,parent,false);
                return new AdsViewHolder(view);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (getItemViewType(position)==PROFILE){
                ProfileAViewHolder profileAViewHolder = (ProfileAViewHolder) holder;
                hashMap = arrayList.get(position);
                String name = hashMap.get("name");
                profileAViewHolder.profile_name.setText(name);

                int[] colors = {
                        Color.parseColor("#F44336"),
                        Color.parseColor("#E91E63"),
                        Color.parseColor("#9C27B0"),
                        Color.parseColor("#3F51B5"),
                        Color.parseColor("#03A9F4"),
                        Color.parseColor("#009688"),
                        Color.parseColor("#4CAF50"),
                        Color.parseColor("#FF9800")
                };

                Random random = new Random();
                int color = colors[random.nextInt(colors.length)];
                profileAViewHolder.layoutProfileId.setBackgroundColor(color);

            }else {

                AdsViewHolder adsViewHolder = (AdsViewHolder) holder;
                if (nativeAd == null || !nativeAd.isAdLoaded()) {
                    adsViewHolder.native_ad_container.setVisibility(View.GONE);
                    return;
                }
                adsViewHolder.native_ad_container.setVisibility(VISIBLE);
                adsViewHolder.native_ad_container.removeAllViews();
                View adView = NativeAdView.render(MainActivity.this, nativeAd);
                adsViewHolder.native_ad_container.addView(adView, new ViewGroup.LayoutParams(MATCH_PARENT, 800));

            }
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        @Override
        public int getItemViewType(int position) {
            hashMap = arrayList.get(position);
            String type = hashMap.get("type");
            if (type.contains("profile")){
                return PROFILE;
            }else {
                return ADS;
            }
        }
    }

    //Adapter recyclerview start

    @Override
    protected void onDestroy() {
        if (adViewBanner != null) {
            adViewBanner.destroy();
        }
        if (interstitialAd != null) {
            interstitialAd.destroy();
        }
        super.onDestroy();
    }

}