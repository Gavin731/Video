package com.film.television.utils

import android.content.Context
import kotlin.math.roundToInt

object UIUtil {

    fun dp2px(context: Context, dp: Float): Int {
        return (context.resources.displayMetrics.density * dp).roundToInt()
    }

    fun px2dp(context: Context, px: Int): Float {
        return px / context.resources.displayMetrics.density
    }

}