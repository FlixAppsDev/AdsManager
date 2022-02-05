package com.mezioud.adsmanager;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;


/**
 * Created by Mezioud on  18,janvier,2022.
 */
public class AdmobAdsManager {


    public static void initialized(Context context) {
        MobileAds.initialize(context);
    }

    public static void ShowAdmobBanner(String bannerId ,LinearLayout bannerLayout, Activity activity) {

        AdRequest adRequest = new AdRequest.Builder().build();
        AdView adView = new AdView(activity);
        bannerLayout.addView(adView);
        adView.setAdUnitId(bannerId);
        AdSize adSize = new AdSize(300, 50);
        adView.setAdSize(adSize);
        adView.loadAd(adRequest);

    }


    public static void ShowAdmobInter(String interId,Activity activity) {

        AdRequest adRequest = new AdRequest.Builder().build();
        adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(
                activity,
                interId,
                adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {


                        if (interstitialAd != null) {
                            interstitialAd.show(activity);
                        }

                        interstitialAd.setFullScreenContentCallback(
                                new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {

                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(AdError adError) {

                                    }

                                    @Override
                                    public void onAdShowedFullScreenContent() {
                                    }
                                });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {



                    }
                });


    }


    public static void ShowNative(String nativeId, TemplateView template , Activity activity) {

        AdLoader adLoader = new AdLoader.Builder(activity, nativeId)
                .forNativeAd(nativeAd -> {

                    template.setVisibility(View.VISIBLE);
                    template.setNativeAd(nativeAd);
                })
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());

    }
}
