package com.film.television.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.paging.PagingDataAdapter
import com.film.television.databinding.ItemVideoBinding
import com.film.television.model.VideoBean

class PagedVideoAdapter(private val fragment: Fragment?, private val activity: FragmentActivity?) :
    PagingDataAdapter<VideoBean, VideoViewHolder>(VideoBean) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VideoViewHolder {
        return when {
            fragment != null -> {
                VideoViewHolder(
                    null,
                    fragment,
                    null,
                    ItemVideoBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            activity != null -> {
                VideoViewHolder(
                    null,
                    null,
                    activity,
                    ItemVideoBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            else -> {
                throw RuntimeException("PagedVideoAdapter's unknown host.")
            }
        }
    }

    override fun onBindViewHolder(
        holder: VideoViewHolder,
        position: Int
    ) {
        val item = getItem(position) ?: return
        holder.bind(item)
    }
}