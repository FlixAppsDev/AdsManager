package com.mezioud.adsmanager;

import android.app.Activity;
import android.widget.LinearLayout;

import com.ironsource.mediationsdk.ISBannerSize;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.IronSourceBannerLayout;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.sdk.BannerListener;
import com.ironsource.mediationsdk.sdk.InterstitialListener;

/**
 * Created by Mezioud on  18,janvier,2022.
 */
public class IronAdsManager {


    public static void initialized(String ironId, Activity activity) {
        IronSource.init(activity, ironId);
    }

    public static void ShowIronBanner(LinearLayout bannerLayout, Activity activity) {

        IronSourceBannerLayout banner = IronSource.createBanner(activity, ISBannerSize.BANNER);
        bannerLayout.addView(banner);
        banner.setBannerListener(new BannerListener() {
            @Override
            public void onBannerAdLoaded() {

            }

            @Override
            public void onBannerAdLoadFailed(IronSourceError ironSourceError) {

            }

            @Override
            public void onBannerAdClicked() {

            }

            @Override
            public void onBannerAdScreenPresented() {

            }

            @Override
            public void onBannerAdScreenDismissed() {

            }

            @Override
            public void onBannerAdLeftApplication() {

            }
        });
        IronSource.loadBanner(banner);
        IronSource.loadBanner(banner, "DefaultBanner");


    }


    public static void ShowIronInter(Activity activity) {

        IronSource.loadInterstitial();

        IronSource.setInterstitialListener(new InterstitialListener() {
            @Override
            public void onInterstitialAdReady() {

            }

            @Override
            public void onInterstitialAdLoadFailed(IronSourceError ironSourceError) {

            }

            @Override
            public void onInterstitialAdOpened() {

            }

            @Override
            public void onInterstitialAdClosed() {

            }

            @Override
            public void onInterstitialAdShowSucceeded() {

            }

            @Override
            public void onInterstitialAdShowFailed(IronSourceError ironSourceError) {

            }

            @Override
            public void onInterstitialAdClicked() {

            }
        });

        IronSource.showInterstitial(("DefaultInterstitial"));

    }

}
