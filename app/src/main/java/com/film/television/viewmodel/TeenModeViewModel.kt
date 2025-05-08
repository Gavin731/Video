package com.film.television.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.film.television.model.VideoBean
import com.film.television.paging.PagedVideoPagingSource
import com.film.television.utils.Constants
import kotlinx.coroutines.flow.Flow

class TeenModeViewModel : ViewModel() {

    fun getContentFlow(category: String): Flow<PagingData<VideoBean>> {
        return Pager(PagingConfig(Constants.PAGE_SIZE)) {
            PagedVideoPagingSource(category, null, null, null)
        }.flow
    }

}