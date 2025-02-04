package com.example.soopimageloader.ui.Category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soopimageloader.data.remote.dto.CategoryDto
import com.example.soopimageloader.data.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
): ViewModel() {

    private val _categories = MutableStateFlow<List<CategoryDto>>(emptyList())
    val categories: StateFlow<List<CategoryDto>> = _categories.asStateFlow()

    fun loadCategories() {
        viewModelScope.launch {
            categoryRepository.getCategories().collect { response ->

            }
        }
    }
}