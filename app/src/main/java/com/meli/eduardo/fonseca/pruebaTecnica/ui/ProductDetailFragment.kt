package com.meli.eduardo.fonseca.pruebaTecnica.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.meli.eduardo.fonseca.pruebaTecnica.viewmodel.ProductsViewModel
import com.meli.eduardo.fonseca.pruebaTecnica.base.BaseFragment
import com.meli.eduardo.fonseca.pruebaTecnica.databinding.FragmentProductDetailBinding
import com.meli.eduardo.fonseca.pruebaTecnica.utils.setValue
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding>() {
    private val productsViewModel: ProductsViewModel by activityViewModels()
    override fun getViewBinding() = FragmentProductDetailBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productsViewModel.productSelected.observe(viewLifecycleOwner) {
            it?.let { product ->
                Glide.with(binding.root.context).load(product.image).into(binding.productImage)
                binding.productPrice.setValue(product.price.toString().take(6))
                binding.productDescription.text = product.name
                binding.productInstallments.setValue((product.price / 12.0).toString().take(6))
            }
        }
    }
}