package com.meli.eduardo.fonseca.pruebaTecnica

import javax.inject.Inject
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import com.meli.eduardo.fonseca.domain.usecase.Result
import com.meli.eduardo.fonseca.domain.entity.Product
import com.meli.eduardo.fonseca.domain.usecase.GetProductUseCase


@HiltViewModel
class ProductsViewModel @Inject constructor(
    val productUseCase: GetProductUseCase
) : ViewModel() {

    private val _products = MutableLiveData<Result<List<Product>>>()
    val products: LiveData<Result<List<Product>>> get() = _products

    private val _productSelected = MutableLiveData<Product?>()
    val productSelected: LiveData<Product?> get() = _productSelected
    fun selectProduct(product: Product?) = _productSelected.postValue(product)

    fun getAllProductsByCategory(category: String) {
        viewModelScope.launch {
            _products.value = Result.Loading
            val result = productUseCase(category)
            _products.value = result
        }
    }
}