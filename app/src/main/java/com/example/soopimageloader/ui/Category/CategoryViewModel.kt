package com.example.soopimageloader.ui.Category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.soopimageloader.data.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
): ViewModel() {
    val categoryPagingData: Flow<PagingData<Category>> =
        categoryRepository.getCategories()
            .cachedIn(viewModelScope)
}