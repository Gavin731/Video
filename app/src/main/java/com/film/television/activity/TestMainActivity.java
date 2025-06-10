package com.film.television.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.common.wheel.admanager.AdvertisementManager;
import com.common.wheel.admanager.InfoAdCallBack;
import com.common.wheel.admanager.InformationFlowAdCallback;
import com.common.wheel.admanager.OpenScreenAdCallBack;
import com.common.wheel.admanager.RewardAdCallBack;
import com.common.wheel.util.DeviceUtil;
import com.film.television.R;
import com.orhanobut.hawk.Hawk;

public class TestMainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_ad);

        AdvertisementManager.getInstance().requestPermissionIfNecessary(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
        }
        boolean isRoot = DeviceUtil.isRoot();
        boolean isAdb = DeviceUtil.isAdb(this);
        boolean isDl = DeviceUtil.isDl(this);
        boolean isVpn = DeviceUtil.isVpnActive(this);

        String text = "是否开启root:"+isRoot+"，是否开始adb:"+isAdb+"，是否开始代理:"+isDl+"，是否开始Vpn:"+isVpn;

        Button init_ad = (Button)findViewById(R.id.init_ad);
        init_ad.setText(text);
        TextView tv_content = (TextView)findViewById(R.id.tv_content);


        var interstitial_perss_ad_config = Hawk.get("interstitial_perss_ad_config", false);
        var interstitial_perss_ad_config_value = Hawk.get("interstitial_perss_ad_config_value");
        var perss_img_url = Hawk.get("perss_img_url");

        tv_content.setText(interstitial_perss_ad_config + " ====" + interstitial_perss_ad_config_value + "==="+perss_img_url);

        findViewById(R.id.init_ad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
//        findViewById(R.id.load_ad).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                interstitialAdManager = new InterstitialAdManager();
////                interstitialAdManager.loadAd(MainActivity.this, "964568346");
//            }
//        });
        findViewById(R.id.show_ad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdvertisementManager.getInstance().showInterstitialAd(TestMainActivity.this, "102935580", new InfoAdCallBack() {
                    @Override
                    public void onError() {
                        Log.i("", "------插屏广告获取失败");
                    }

                    @Override
                    public void onLoadSuccess() {
                        Log.i("", "------插屏广告获取成功");
                    }

                    @Override
                    public void onStartShow() {
                        Log.i("", "------插屏广告开始显示");
                    }

                    @Override
                    public void onAdShow() {
                        Log.i("", "------插屏广告已展示");
                    }

                    @Override
                    public void onAdVideoBarClick() {
                        Log.i("", "------插屏广告被点击");
                    }

                    @Override
                    public void onAdClose() {
                        Log.i("", "------插屏广告被关闭");
                    }

                    @Override
                    public void onVideoComplete() {
                        Log.i("", "------插屏广告视频完成");
                    }

                    @Override
                    public void onSkippedVideo() {
                        Log.i("", "------插屏广告视频跳过");
                    }
                });
            }
        });
        FrameLayout splashContainer = findViewById(R.id.splashContainer);
        FrameLayout infoContainer = findViewById(R.id.infoContainer);
        FrameLayout infoContainer2 = findViewById(R.id.infoContainer2);
        findViewById(R.id.show_kp_ad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdvertisementManager.getInstance().showOpenScreenAd(TestMainActivity.this, "102936358", splashContainer, 1000, 1920, new OpenScreenAdCallBack() {
                    @Override
                    public void onAdClose() {
                        Log.i("", "------开屏广告关闭");
                        splashContainer.removeAllViews();
                    }

                    @Override
                    public void onSplashAdClick() {
                        Log.i("", "------开屏广告被点击");
                    }

                    @Override
                    public void onSplashAdShow() {
                        Log.i("", "------开屏广告已显示");
                    }

                    @Override
                    public void onSplashLoadFail() {
                        Log.i("", "------开屏广告获取失败");
                    }

                    @Override
                    public void onSplashRenderFail() {
                        Log.i("", "------开屏广告渲染失败");
                    }
                });
            }
        });
        findViewById(R.id.show_info_image_ad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdvertisementManager.getInstance().showInfoFlowAd(TestMainActivity.this, "102934170", infoContainer, 800, 400, null);
            }
        });
        findViewById(R.id.show_info_image_ad2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdvertisementManager.getInstance().showInfoFlowAd(TestMainActivity.this, "102934170", infoContainer2, 900, 300, new InformationFlowAdCallback() {
                    @Override
                    public void onError() {
                        Log.i("", "------信息流广告获取失败");
                    }

                    @Override
                    public void onFeedAdLoad() {
                        Log.i("", "------信息流广告获取成功");
                    }

                    @Override
                    public void onRenderSuccess() {
                        Log.i("", "------信息流广告渲染成功");
                    }

                    @Override
                    public void onAdClick() {
                        Log.i("", "------信息流广告被点击");
                    }

                    @Override
                    public void onRenderFail() {
                        Log.i("", "------信息流广告显示失败");
                    }
                });
            }
        });
        findViewById(R.id.http_request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdvertisementManager.getInstance().showRewardAd(TestMainActivity.this, "102935581", new RewardAdCallBack() {
                    @Override
                    public void onAdClose() {
                        Log.i("", "------激励广告关闭");
                    }

                    @Override
                    public void onVideoComplete() {
                        Log.i("", "------激励广告视频完成");
                    }

                    @Override
                    public void onAdVideoBarClick() {
                        Log.i("", "------激励广告视频被点击");
                    }

                    @Override
                    public void onVideoError() {
                        Log.i("", "------激励广告视频获取失败");
                    }

                    @Override
                    public void onRewardArrived() {
                        Log.i("", "------激励广告激励发放");
                    }

                    @Override
                    public void onSkippedVideo() {
                        Log.i("", "------激励广告跳过");
                    }

                    @Override
                    public void onAdShow() {
                        Log.i("", "------激励广告已显示");
                    }

                    @Override
                    public void onError() {
                        Log.i("", "------激励广告加载失败");
                    }
                });
            }
        });
    }
}