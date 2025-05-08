plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.film.television"
    compileSdk = 35

    
    defaultConfig {
        applicationId = "com.film.television"
        minSdk = 24
        targetSdk = 35
        versionCode = 3
        versionName = "1.3"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    signingConfigs {
        create("config") {
            storeFile = File("D:\\project\\ad\\juduo.jks")
            storePassword = "juduo123"
            keyAlias = "juduo"
            keyPassword = "juduo123"
        }
    }
    buildTypes {
        debug {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("config")
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isZipAlignEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("config")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    packagingOptions {
        exclude("AndroidManifest.xml") // 忽略 .aar 文件中的 AndroidManifest.xml
    }
}

dependencies {
    implementation(fileTree("libs") { include("*.jar", "*.aar") })
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.constrain)
    implementation(libs.androidx.viewpager2)
    implementation(libs.androidx.viewmodel)
    implementation(libs.androidx.livedata)
    implementation(libs.androidx.viewmodel.savedstate)
    implementation(libs.androidx.lifecycle.process)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.datastore)
    implementation(libs.androidx.swiperefreshlayout)
    implementation(libs.androidx.paging)

    implementation(libs.flexbox)
    implementation(libs.gson)

    implementation(libs.retrofit)
    implementation(libs.gson.converter)
    implementation(libs.glide)

    // 友盟
    implementation("com.umeng.umsdk:common:+")
    implementation("com.umeng.umsdk:asms:+")
    implementation("com.umeng.umsdk:apm:+")

    implementation("com.maven.local:Baidu_MobAds_SDK_v9.37:9.37.0")
    implementation("com.maven.local:GDTSDK:4.611.1481")
    implementation("com.maven.local:kssdk-ad:3.3.71.3")
    implementation("com.maven.local:mbridge_chinasame:16.6.57")
    implementation("com.maven.local:mbridge_dycreator:16.6.57")
    implementation("com.maven.local:mbridge_interstitial:16.6.57")
    implementation("com.maven.local:mbridge_interstitialvideo:16.6.57")
    implementation("com.maven.local:mbridge_mbbanner:16.6.57")
    implementation("com.maven.local:mbridge_mbbid:16.6.57")
    implementation("com.maven.local:mbridge_mbjscommon:16.6.57")
    implementation("com.maven.local:mbridge_mbnative:16.6.57")
    implementation("com.maven.local:mbridge_mbnativeadvanced:16.6.57")
    implementation("com.maven.local:mbridge_mbsplash:16.6.57")
    implementation("com.maven.local:mbridge_nativeex:16.6.57")
    implementation("com.maven.local:mbridge_newinterstitial:16.6.57")
    implementation("com.maven.local:mbridge_playercommon:16.6.57")
    implementation("com.maven.local:mbridge_reward:16.6.57")
    implementation("com.maven.local:mbridge_videocommon:16.6.57")
    implementation("com.maven.local:mbridge_videojs:16.6.57")
    implementation("com.maven.local:mediation_admob_adapter:17.2.0.65")
    implementation("com.maven.local:mediation_baidu_adapter:9.37.3")
    implementation("com.maven.local:mediation_gdt_adapter:4.611.1481.0")
    implementation("com.maven.local:mediation_ks_adapter:3.3.71.3.0")
    implementation("com.maven.local:mediation_mintegral_adapter:16.6.57.8")
    implementation("com.maven.local:mediation_sigmob_adapter:4.19.5.1")
    implementation("com.maven.local:mediation_unity_adapter:4.3.0.32")
    implementation("com.maven.local:open_ad_sdk:6.7.0.6")
//    implementation("com.maven.local:tools-release:1.0.0")
    implementation("com.maven.local:unity-ads:4.3.0")
    implementation("com.maven.local:windAd:4.19.5")
    implementation("com.maven.local:windAd-common:1.7.2")

//glide
    api("com.github.bumptech.glide:glide:4.8.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.8.0")
    //SharedPreferences
    implementation("com.orhanobut:hawk:2.0.1")
    //okhttp
    implementation("com.squareup.okhttp3:okhttp:4.7.2")
    //rxjava和retrofit
    implementation("io.reactivex.rxjava3:rxjava:3.0.4")
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")
    implementation("com.squareup.retrofit2:retrofit:2.8.1")
    implementation("com.squareup.retrofit2:converter-gson:2.8.1")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.8.1")
    //添加HttpLoggingInterceptor
    implementation("com.squareup.okhttp3:logging-interceptor:3.9.0")

    implementation("com.android.support:support-v4:28.0.0")

}