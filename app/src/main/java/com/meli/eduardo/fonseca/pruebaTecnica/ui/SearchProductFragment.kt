package com.meli.eduardo.fonseca.pruebaTecnica.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.meli.eduardo.fonseca.domain.entity.Category
import com.meli.eduardo.fonseca.domain.usecase.Result
import com.meli.eduardo.fonseca.pruebaTecnica.CategoryViewModel
import com.meli.eduardo.fonseca.pruebaTecnica.ProductsViewModel
import com.meli.eduardo.fonseca.pruebaTecnica.R
import com.meli.eduardo.fonseca.pruebaTecnica.base.BaseFragment
import com.meli.eduardo.fonseca.pruebaTecnica.databinding.FragmentSearchProductBinding
import com.meli.eduardo.fonseca.pruebaTecnica.ui.adapter.ProductAdapter
import com.meli.eduardo.fonseca.pruebaTecnica.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchProductFragment : BaseFragment<FragmentSearchProductBinding>() {

    private val productsViewModel: ProductsViewModel by activityViewModels()
    private val categoryViewModel: CategoryViewModel by activityViewModels()

    private lateinit var productAdapter: ProductAdapter;
    override fun getViewBinding() = FragmentSearchProductBinding.inflate(layoutInflater)

    override fun onStart() {
        super.onStart()
        hideKeyboard()
        if (categoryViewModel.categories.value == null) {
            categoryViewModel.getAllCategories()
            categoryViewModel.categories.observe(viewLifecycleOwner) {
                if (it is Result.Success) {
                    val category = it.data.random()
                    productsViewModel.getAllProductsByCategory(category.name)
                }
                if (it is Result.Error){
                    this.showMessage("UPS!", it.exception.message.toString())
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createAdapter()
        observeViewModels()
        onSubmitResults()
    }

    private fun onSubmitResults() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isNotEmpty()) {
                    productsViewModel.getAllProductsByCategory(query)
                }
                hideKeyboard()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()){
                    if (categoryViewModel.categories.value is Result.Success && productsViewModel.productSelected.value == null) {
                        (categoryViewModel.categories.value as Result.Success<List<Category>>).data.random().let {
                            productsViewModel.getAllProductsByCategory(it.name)
                        }

                    }
                    hideKeyboard()
                }
                return true
            }

        });
    }

    private fun observeViewModels() {
        productsViewModel.products.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Error -> {
                    this.showMessage("UPS!", it.exception.message.toString())
                }
                Result.Loading -> {}
                is Result.Success -> {
                    productAdapter.setNewItems(it.data)
                }
            }
        }

    }

    private fun createAdapter() {
        binding.recyclerProducts.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        productAdapter = ProductAdapter(arrayListOf())
        binding.recyclerProducts.adapter = productAdapter
        productAdapter.onItemClick = {
            productsViewModel.selectProduct(it)
            findNavController().navigate(R.id.action_searchProductFragment_to_productDetailFragment)
        }
    }
}