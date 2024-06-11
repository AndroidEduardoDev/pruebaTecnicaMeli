package com.meli.eduardo.fonseca.pruebaTecnica.viewmodel

import javax.inject.Inject
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import com.meli.eduardo.fonseca.domain.entity.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import com.meli.eduardo.fonseca.domain.usecase.Result
import com.meli.eduardo.fonseca.domain.usecase.GetCategoryUseCase


@HiltViewModel
class CategoryViewModel @Inject constructor(
    val categoryUseCase: GetCategoryUseCase
) : ViewModel() {

    private val _categories = MutableLiveData<Result<List<Category>>>()
    val categories: LiveData<Result<List<Category>>> get() = _categories

    fun getAllCategories() {
        viewModelScope.launch {
            _categories.value = Result.Loading
            val result = categoryUseCase()
            _categories.value = result
        }
    }
}