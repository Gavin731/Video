package com.film.television.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.film.television.model.GeneralVideoInfoResp

object ApplicationData {
    private val _generalVideoInfoData = MutableLiveData<GeneralVideoInfoResp.Data>()

    val generalVideoInfoData: LiveData<GeneralVideoInfoResp.Data> = _generalVideoInfoData

    fun setGeneralVideoInfoData(data: GeneralVideoInfoResp.Data) {
        _generalVideoInfoData.value = data
    }
}