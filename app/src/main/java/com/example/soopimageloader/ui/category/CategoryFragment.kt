package com.example.soopimageloader.ui.category

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.soopimageloader.databinding.FragmentCategoryListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CategoryViewModel by viewModels()

    private val categoryAdapter by lazy { CategoryAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setGridLayoutManager(orientation: Int = resources.configuration.orientation) {
        val spanCount = if (orientation == Configuration.ORIENTATION_LANDSCAPE) 4 else 2
        binding.rvCategories.layoutManager = GridLayoutManager(requireContext(), spanCount)
    }

    private fun initAdapter() = with(binding) {
        setGridLayoutManager()

        rvCategories.adapter = categoryAdapter

        swipeRv.setOnRefreshListener {
            categoryAdapter.refresh()

            swipeRv.isRefreshing = false
        }
    }

    private fun initCollect() = viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.categoryPagingData.collectLatest { pagingData ->
                categoryAdapter.submitData(pagingData)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        initCollect()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        setGridLayoutManager(newConfig.orientation)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}