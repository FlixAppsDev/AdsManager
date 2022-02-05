package com.mezioud.adsmanager;

import android.app.Activity;
import android.os.Handler;
import android.widget.LinearLayout;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.BannerErrorInfo;
import com.unity3d.services.banners.BannerView;
import com.unity3d.services.banners.UnityBannerSize;

/**
 * Created by Mezioud on  18,janvier,2022.
 */
public class UnityAdsManager {


    public static void initialized(String unityId ,Activity activity) {
        UnityAds.initialize(activity, unityId);
    }

    public static void ShowUnityBanner(String bannerId ,LinearLayout bannerLayout, Activity activity) {
        IUnityBannerListener bannerListener = new  IUnityBannerListener();
        BannerView bannerView = new BannerView (activity, bannerId, new UnityBannerSize(320,  50));
        bannerView.setListener(bannerListener);
        bannerView.load();
        bannerLayout.addView(bannerView);
    }


    public static void ShowUnityInter(String interId ,Activity activity) {
        new Handler().postDelayed(() -> {

            if(UnityAds.isReady(interId))
                UnityAds.show(activity, interId);
        }, 500);

    }

    public static void ShowUnityReword(String rewordId,Activity activity) {
        if(UnityAds.isReady(rewordId))
            UnityAds.show(activity, rewordId);
    }


    private static class IUnityBannerListener implements BannerView.IListener {
        @Override
        public void onBannerLoaded(BannerView bannerAdView) {
        }

        @Override
        public void onBannerFailedToLoad(BannerView bannerAdView, BannerErrorInfo errorInfo) {
        }

        @Override
        public void onBannerClick(BannerView bannerAdView) {
        }

        @Override
        public void onBannerLeftApplication(BannerView bannerAdView) {
        }
    }

}
