package com.film.television.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.film.television.R
import com.film.television.adapter.VideoAdapter
import com.film.television.databinding.FragmentHotBinding
import com.film.television.databinding.ItemHotBinding
import com.film.television.model.HomepageVideoResp
import com.film.television.utils.TokenUtil
import com.film.television.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

class HotFragment : BaseFragment<FragmentHotBinding>() {
    private val homeViewModel: HomeViewModel by activityViewModels()
    private var mRespData: HomepageVideoResp.Data? = null

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHotBinding {
        return FragmentHotBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        binding.root.setColorSchemeResources(R.color.theme_color)
        binding.root.setOnRefreshListener {
            getData()
        }
    }

    override fun initData() {
        getData()
    }

    private fun getData() {
        viewLifecycleOwner.lifecycleScope.launch {
            TokenUtil.getToken()?.let {
                val resp = homeViewModel.queryHomepageVideo(it)
                if (resp.code == 200) {
                    val respData = resp.data
                    if (respData != mRespData) {
                        binding.content.removeAllViews()
                        setContent(respData)
                        mRespData = respData
                    }
                }
            }
            binding.root.isRefreshing = false
        }
    }

    private fun setContent(data: HomepageVideoResp.Data) {
        if (data.tv.isNotEmpty()) {
            val tvBinding = ItemHotBinding.inflate(layoutInflater)
            tvBinding.title.text = getString(R.string.hot_tv)
            tvBinding.more.setOnClickListener {
                homeViewModel.setHotCategory("TV")
            }
            tvBinding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
            val tvData = data.tv.takeIf { it.size <= 6 } ?: data.tv.subList(0, 6)
            tvBinding.recyclerView.adapter = VideoAdapter(null, this, null, tvData)
            binding.content.addView(tvBinding.root)
        }
        if (data.movie.isNotEmpty()) {
            val movieBinding = ItemHotBinding.inflate(layoutInflater)
            movieBinding.title.text = getString(R.string.hot_movie)
            movieBinding.more.setOnClickListener {
                homeViewModel.setHotCategory("MOVIE")
            }
            movieBinding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
            val movieData = data.movie.takeIf { it.size <= 6 } ?: data.movie.subList(0, 6)
            movieBinding.recyclerView.adapter = VideoAdapter(null, this, null, movieData)
            binding.content.addView(movieBinding.root)
        }
        if (data.variety.isNotEmpty()) {
            val varietyBinding = ItemHotBinding.inflate(layoutInflater)
            varietyBinding.title.text = getString(R.string.hot_variety)
            varietyBinding.more.setOnClickListener {
                homeViewModel.setHotCategory("VARIETY")
            }
            varietyBinding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
            val varietyData = data.variety.takeIf { it.size <= 6 } ?: data.variety.subList(0, 6)
            varietyBinding.recyclerView.adapter = VideoAdapter(null, this, null, varietyData)
            binding.content.addView(varietyBinding.root)
        }
        if (data.anime.isNotEmpty()) {
            val animeBinding = ItemHotBinding.inflate(layoutInflater)
            animeBinding.title.text = getString(R.string.hot_anime)
            animeBinding.more.setOnClickListener {
                homeViewModel.setHotCategory("ANIME")
            }
            animeBinding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
            val animeData = data.anime.takeIf { it.size <= 6 } ?: data.anime.subList(0, 6)
            animeBinding.recyclerView.adapter = VideoAdapter(null, this, null, animeData)
            binding.content.addView(animeBinding.root)
        }
    }
}