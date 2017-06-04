package com.example.user.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class InterstitialGoogle extends AppCompatActivity {
    private InterstitialAd adView;  // The ad
    private Handler mHandler;       // Handler to display the ad on the UI thread
    private Runnable displayAd;     // Code to execute to perform this operation
    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial_google);}

    private void loadInterstitial() {
        // Disable the next level button and load the ad.
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        mInterstitialAd.loadAd(adRequest);
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
           goToEndGame();
        }
    }

    public void goToEndGame() {
        Intent intent = new Intent(this, EndGame.class);
        startActivity(intent);
    }

    private InterstitialAd newInterstitialAd() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
        interstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdClosed() {
                goToEndGame();

            }
        });
        return interstitialAd;
    }
}

//    mInterstitialAd = newInterstitialAd();
//    loadInterstitial();
//    }
//
//    private void loadInterstitial() {
//        AdRequest adRequest = new AdRequest.Builder()
//                .setRequestAgent("android_studio:ad_template").build();
//        mInterstitialAd.loadAd(adRequest);
//    }
//
//    public void showInterstitial() {
//        if (mInterstitialAd.isLoaded()) {
//            mInterstitialAd.show();
//        }else{
//            GoToEndGame();
//        }
//    }
//
//    public void GoToEndGame(){
//        Intent intent = new Intent(this, EndGame.class);
//        startActivity(intent);
//
//    }
//    private InterstitialAd newInterstitialAd() {
//        InterstitialAd interstitialAd = new InterstitialAd(this);
//        interstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
//        interstitialAd.setAdListener(new AdListener() {
//
//            public void onAdLoaded() {
//                showInterstitial();
//            }
//            @Override
//            public void onAdClosed() {
//                GoToEndGame();
//            }
//        });
//        return interstitialAd;
//    }

