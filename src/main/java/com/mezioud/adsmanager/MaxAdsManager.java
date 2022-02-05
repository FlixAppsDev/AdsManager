package com.mezioud.adsmanager;

import android.app.Activity;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.applovin.sdk.AppLovinSdkUtils;


/**
 * Created by Mezioud on  18,janvier,2022.
 */
public class MaxAdsManager {

    private static  MaxInterstitialAd interstitialAd;
    private static MaxAdView adView;



    public static void initialized(Activity activity) {
        AppLovinSdk.getInstance(activity).setMediationProvider("max");
        AppLovinSdk.initializeSdk(activity, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
            }
        });

    }



    public static void ShowMaxBanner(String bannerId ,LinearLayout bannerLayout, Activity activity) {

        MaxAdViewAdListener maxAdViewAdListener = new MaxAdViewAdListener() {
            @Override
            public void onAdLoaded(MaxAd ad) {

            }

            @Override
            public void onAdDisplayed(MaxAd ad) {

            }

            @Override
            public void onAdHidden(MaxAd ad) {

            }

            @Override
            public void onAdClicked(MaxAd ad) {

            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {

            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {

            }

            @Override
            public void onAdExpanded(MaxAd ad) {

            }

            @Override
            public void onAdCollapsed(MaxAd ad) {

            }
        };
        adView = new MaxAdView(bannerId, activity );
        adView.setListener(maxAdViewAdListener);
        adView.setId(ViewCompat.generateViewId());
        bannerLayout.addView(adView, new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, AppLovinSdkUtils.dpToPx(activity, 50)));
        adView.loadAd();

    }

    public static void ShowMaxNative(String nativeId ,ConstraintLayout nativeLayout, Activity activity) {

        MaxAdViewAdListener maxAdViewAdListener = new MaxAdViewAdListener() {
            @Override
            public void onAdLoaded(MaxAd ad) {

            }

            @Override
            public void onAdDisplayed(MaxAd ad) {

            }

            @Override
            public void onAdHidden(MaxAd ad) {

            }

            @Override
            public void onAdClicked(MaxAd ad) {

            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {

            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {

            }

            @Override
            public void onAdExpanded(MaxAd ad) {

            }

            @Override
            public void onAdCollapsed(MaxAd ad) {

            }
        };
        adView = new MaxAdView( nativeId, MaxAdFormat.MREC, activity );
        adView.setListener(maxAdViewAdListener);
        adView.setId(ViewCompat.generateViewId());
        final int widthPx = AppLovinSdkUtils.dpToPx(activity, 300 );
        final int heightPx = AppLovinSdkUtils.dpToPx(activity, 250 );
        nativeLayout.addView(adView, new ConstraintLayout.LayoutParams(widthPx, heightPx ));
        adView.loadAd();

    }


    public static void ShowMaxInter(String interId ,Activity activity) {

        new Handler().postDelayed(() -> {

            MaxAdListener maxAdListener =new MaxAdListener() {
                @Override
                public void onAdLoaded(MaxAd ad) {


                    if ( interstitialAd.isReady() )
                    {
                        interstitialAd.showAd();
                    }
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {

                }

                @Override
                public void onAdHidden(MaxAd ad) {

                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {

                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                }

            };
            interstitialAd = new MaxInterstitialAd( interId,activity);
            interstitialAd.setListener(maxAdListener);
            interstitialAd.loadAd();
        }, 500);



    }


}

