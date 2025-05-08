package com.film.television.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.film.television.model.GeneralVideoInfoBody
import com.film.television.model.GeneralVideoInfoResp
import com.film.television.model.HomepageVideoBody
import com.film.television.model.HomepageVideoResp
import com.film.television.model.VideoBean
import com.film.television.paging.PagedVideoPagingSource
import com.film.television.repository.ContentRepository
import com.film.television.utils.Constants
import com.film.television.utils.DeviceUtil
import kotlinx.coroutines.flow.Flow

class HomeViewModel : ViewModel() {
    private val _hotCategory: MutableLiveData<String> = MutableLiveData()

    val hotCategory: LiveData<String> = _hotCategory.distinctUntilChanged()

    fun setHotCategory(hotCategory: String) {
        _hotCategory.value = hotCategory
    }

    suspend fun queryGeneralVideoInfo(token: String): GeneralVideoInfoResp {
        return ContentRepository.queryGeneralVideoInfo(
            GeneralVideoInfoBody(
                Constants.GENERAL_VIDEO_INFO,
                DeviceUtil.getPackageName(),
                token
            )
        )
    }

    suspend fun queryHomepageVideo(
        token: String,
        moduleType: String = "POPULAR"
    ): HomepageVideoResp {
        return ContentRepository.queryHomepageVideo(
            HomepageVideoBody(
                Constants.HOMEPAGE_VIDEO_QUERY,
                DeviceUtil.getPackageName(),
                token,
                HomepageVideoBody.Params(moduleType)
            )
        )
    }

    fun getPagedVideoFlow(
        category: String?,
        genre: String?,
        region: String?,
        yearCategory: String?
    ): Flow<PagingData<VideoBean>> {
        return Pager(PagingConfig(Constants.PAGE_SIZE)) {
            PagedVideoPagingSource(category, genre, region, yearCategory)
        }.flow
    }

}