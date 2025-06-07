package com.film.television

import android.app.Application
import android.util.Log
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.bytedance.sdk.openadsdk.TTCustomController
import com.bytedance.sdk.openadsdk.mediation.init.MediationPrivacyConfig
import com.common.wheel.admanager.AdvertisementManager
import com.common.wheel.admanager.InitCallback
import com.film.television.utils.DataStoreUtil
import com.film.television.utils.UMUtil
import com.umeng.commonsdk.UMConfigure
import kotlinx.coroutines.launch

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        UMConfigure.preInit(this, UMUtil.APP_KEY, UMUtil.CHANNEL)
        ProcessLifecycleOwner.get().lifecycleScope.launch {
            if (DataStoreUtil.getGranted()) {
                UMConfigure.init(
                    this@MyApplication,
                    UMUtil.APP_KEY,
                    UMUtil.CHANNEL,
                    UMConfigure.DEVICE_TYPE_PHONE,
                    ""
                )
            }
        }
        AdvertisementManager.getInstance().init(this, "5558135", getString(R.string.app_name),
            object : InitCallback {
                override fun success() {
                    Log.i("","初始化成功回调")
                }

                override fun error() {
                    Log.i("","初始化失败回调")
                }
            }, getTTCustomController());
        AdvertisementManager.getInstance().initConfig("TEST0000001");
    }

    private fun getTTCustomController(): TTCustomController {
        return object : TTCustomController() {
            override fun isCanUseLocation(): Boolean {  //是否授权位置权限
                return true
            }

            override fun isCanUsePhoneState(): Boolean {  //是否授权手机信息权限
                return true
            }

            override fun isCanUseWifiState(): Boolean {  //是否授权wifi state权限
                return true
            }

            override fun isCanUseWriteExternal(): Boolean {  //是否授权写外部存储权限
                return true
            }

            override fun isCanUseAndroidId(): Boolean {  //是否授权Android Id权限
                return true
            }

            override fun getMediationPrivacyConfig(): MediationPrivacyConfig? {
                return object : MediationPrivacyConfig() {
                    override fun isLimitPersonalAds(): Boolean {  //是否限制个性化广告
                        return false
                    }

                    override fun isProgrammaticRecommend(): Boolean {  //是否开启程序化广告推荐
                        return true
                    }
                }
            }
        }
    }

    companion object {
        lateinit var INSTANCE: MyApplication
    }

}