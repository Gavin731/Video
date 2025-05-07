package com.film.television.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.film.television.model.PagedVideoQueryBody
import com.film.television.model.VideoBean
import com.film.television.repository.ContentRepository
import com.film.television.utils.Constants
import com.film.television.utils.DeviceUtil
import com.film.television.utils.TokenUtil

class PagedVideoPagingSource(
    private val category: String?,
    private val genre: String?,
    private val region: String?,
    private val yearCategory: String?
) : PagingSource<Int, VideoBean>() {
    override fun getRefreshKey(state: PagingState<Int, VideoBean>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, VideoBean> {
        return try {
            val nextPageNumber = params.key ?: 1
            val token = TokenUtil.getToken()
            if (token == null) {
                return LoadResult.Error(RuntimeException("The token in PagedVideoPagingSource load method is null."))
            }
            val body = PagedVideoQueryBody(
                Constants.PAGED_VIDEO_QUERY,
                DeviceUtil.getPackageName(),
                token,
                PagedVideoQueryBody.Params(
                    nextPageNumber,
                    Constants.PAGE_SIZE,
                    category,
                    genre,
                    region,
                    yearCategory
                )
            )
            val resp = ContentRepository.queryPagedVideo(body)
            if (resp.code == 200) {
                val nextKey = resp.data.pageNum + 1
                LoadResult.Page(
                    data = resp.data.records,
                    prevKey = null,
                    nextKey = if (nextKey <= resp.data.totalPages) nextKey else null
                )
            } else {
                LoadResult.Error(RuntimeException("Error code: ${resp.code}, and message: ${resp.message}"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}