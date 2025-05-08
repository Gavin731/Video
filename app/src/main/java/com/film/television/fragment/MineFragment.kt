package com.film.television.fragment

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.film.television.activity.AboutUsActivity
import com.film.television.activity.TeenModeActivity
import com.film.television.databinding.FragmentMineBinding
import com.film.television.utils.CacheUtil
import com.film.television.utils.Formatter
import kotlinx.coroutines.launch

class MineFragment : BaseFragment<FragmentMineBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMineBinding {
        return FragmentMineBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        binding.teenMode.setOnClickListener {
            startActivity(Intent(requireContext(), TeenModeActivity::class.java))
        }
        binding.aboutUs.setOnClickListener {
            startActivity(Intent(requireContext(), AboutUsActivity::class.java))
        }
        binding.supportApp.setOnClickListener {

        }
        binding.clearCache.setOnClickListener {
            lifecycleScope.launch {
                CacheUtil.clearCache(requireContext())
                setCacheSize()
            }
        }
    }

    override fun initObservation() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                setCacheSize()
            }
        }
    }

    private suspend fun setCacheSize() {
        val cacheSizeBytes = CacheUtil.getCacheSize(requireContext())
        binding.cacheSize.text = Formatter.formatFileSize(requireContext(), cacheSizeBytes)
    }
}