package com.mezioud.adsmanager;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mezioud on  18,janvier,2022.
 */
public class FanAdsManager {


    public static void ShowFanBanner(String bannerId ,LinearLayout bannerLayout, Activity activity) {

        AdView adBannerFace = new AdView(activity, bannerId,AdSize.BANNER_320_50);
        bannerLayout.addView(adBannerFace);
        adBannerFace.loadAd();

    }


    public static void ShowFanInter(String interId ,Activity activity) {

        InterstitialAd interstitialFacebookAd = new InterstitialAd(activity, interId);
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
            }

            @Override
            public void onError(Ad ad, com.facebook.ads.AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {
                interstitialFacebookAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {
            }

            @Override
            public void onLoggingImpression(Ad ad) {
            }
        };
        interstitialFacebookAd.loadAd(
                interstitialFacebookAd.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener)
                        .build());


    }


    public static void ShowFanNative(String nativeId ,NativeAdLayout nativeAdLayout, Activity activity) {


        NativeAd nativeAd = new NativeAd(activity, nativeId);

        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {

            }


            @Override
            public void onError(Ad ad, AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {
                if (nativeAd == null || nativeAd != ad) {
                    return;
                }
                nativeAd.unregisterView();
                nativeAdLayout.setVisibility(View.VISIBLE);
                LayoutInflater inflater = LayoutInflater.from(activity);
                LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.native_fan_layout, nativeAdLayout, false);
                nativeAdLayout.addView(adView);
                LinearLayout adChoicesContainer = activity.findViewById(R.id.ad_choices_container);
                AdOptionsView adOptionsView = new AdOptionsView(activity, nativeAd, nativeAdLayout);
                adChoicesContainer.removeAllViews();
                adChoicesContainer.addView(adOptionsView, 0);
                MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
                TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
                MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
                TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
                TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
                TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
                Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);
                nativeAdTitle.setText(nativeAd.getAdvertiserName());
                nativeAdBody.setText(nativeAd.getAdBodyText());
                nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
                nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
                nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
                sponsoredLabel.setText(nativeAd.getSponsoredTranslation());
                List<View> clickableViews = new ArrayList<>();
                clickableViews.add(nativeAdTitle);
                clickableViews.add(nativeAdCallToAction);
                nativeAd.registerViewForInteraction(
                        adView,
                        nativeAdMedia,
                        nativeAdIcon,
                        clickableViews);
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };


        nativeAd.loadAd(
                nativeAd.buildLoadAdConfig()
                        .withAdListener(nativeAdListener)
                        .build());

    }


}
