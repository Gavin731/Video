package com.film.television

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.common.wheel.admanager.AdvertisementManager
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
        AdvertisementManager.getInstance().init(this, "5670955", getString(R.string.app_name));
        AdvertisementManager.getInstance().initConfig();
    }

    companion object {
        lateinit var INSTANCE: MyApplication
    }

}