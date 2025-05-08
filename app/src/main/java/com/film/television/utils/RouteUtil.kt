package com.film.television.utils

import android.content.Context
import android.content.Intent
import com.film.television.activity.TeenModeContentActivity
import com.film.television.activity.VideoDetailActivity
import com.film.television.activity.WebActivity
import com.film.television.model.VideoBean

object RouteUtil {

    fun goToVideoDetailActivity(context: Context, videoBean: VideoBean) {
        val intent = Intent(context, VideoDetailActivity::class.java)
        intent.putExtra(Constants.KEY_VIDEO_BEAN, videoBean)
        context.startActivity(intent)
    }

    fun goToWebActivity(context: Context, title: String?, url: String) {
        val intent = Intent(context, WebActivity::class.java)
        intent.putExtra(Constants.KEY_TITLE, title)
        intent.putExtra(Constants.KEY_URL, url)
        context.startActivity(intent)
    }

    fun goToTeenModeContentActivity(context: Context) {
        context.startActivity(Intent(context, TeenModeContentActivity::class.java))
    }

}