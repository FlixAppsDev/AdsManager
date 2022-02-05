package com.mezioud.adsmanager;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;

import com.applovin.adview.AppLovinAdView;
import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;

/**
 * Created by Mezioud on  18,janvier,2022.
 */
public class ApplovinAdsManager {

    private static AppLovinInterstitialAdDialog interstitialAdMax;




    public static void ShowMaxBanner(String bannerId ,LinearLayout bannerLayout, Activity activity) {
        AppLovinAdView adView = new AppLovinAdView(AppLovinAdSize.BANNER,  bannerId , activity);
        adView.setId(ViewCompat.generateViewId());
        bannerLayout.addView(adView, new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, AppLovinSdkUtils.dpToPx(activity, 50)));
        adView.loadNextAd();
    }


    public static void ShowMaxInter(String interId ,Activity activity) {


        new Handler().postDelayed(() -> {

            AppLovinAdLoadListener appLovinAdLoadListener =new AppLovinAdLoadListener() {
                @Override
                public void adReceived(AppLovinAd appLovinAd) {
                    if (appLovinAd != null) {
                        interstitialAdMax.showAndRender(appLovinAd);
                    }
                }

                @Override
                public void failedToReceiveAd(int errorCode) {

                }
            };
            interstitialAdMax = AppLovinInterstitialAd.create( AppLovinSdk.getInstance(activity), activity);
            interstitialAdMax.setAdLoadListener(appLovinAdLoadListener);

            AppLovinSdk.getInstance(activity).getAdService().loadNextAdForZoneId(interId, appLovinAdLoadListener);


        }, 500);



    }


}

