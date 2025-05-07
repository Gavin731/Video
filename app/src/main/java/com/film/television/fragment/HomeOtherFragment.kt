package com.film.television.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.film.television.R
import com.film.television.adapter.PagedVideoAdapter
import com.film.television.databinding.LayoutListBinding
import com.film.television.utils.Constants
import com.film.television.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeOtherFragment : BaseFragment<LayoutListBinding>() {
    private var category: String = ""
    private val homeViewModel: HomeViewModel by activityViewModels()
    private val pagingAdapter: PagedVideoAdapter by lazy {
        PagedVideoAdapter(this, null).apply {
            viewLifecycleOwner.lifecycleScope.launch {
                loadStateFlow.collectLatest { loadStates ->
                    binding.root.isRefreshing = loadStates.refresh is LoadState.Loading
                }
            }
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): LayoutListBinding {
        return LayoutListBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        category = requireArguments().getString(Constants.KEY_CATEGORY)!!
        binding.root.setColorSchemeResources(R.color.theme_color)
        binding.root.setOnRefreshListener {
            pagingAdapter.refresh()
        }
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerView.adapter = pagingAdapter
    }

    override fun initData() {
        require(category.isNotEmpty())
        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.getPagedVideoFlow(category, null, null, null)
                .collectLatest { pagingData ->
                    pagingAdapter.submitData(pagingData)
                }
        }
    }

}