package com.meli.eduardo.fonseca.pruebaTecnica.ui


import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.meli.eduardo.fonseca.domain.usecase.Result
import com.meli.eduardo.fonseca.pruebaTecnica.R
import com.meli.eduardo.fonseca.pruebaTecnica.base.BaseActivity
import com.meli.eduardo.fonseca.pruebaTecnica.databinding.ActivityMainBinding
import com.meli.eduardo.fonseca.pruebaTecnica.utils.showing
import com.meli.eduardo.fonseca.pruebaTecnica.viewmodel.CategoryViewModel
import com.meli.eduardo.fonseca.pruebaTecnica.viewmodel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val productsViewModel: ProductsViewModel by viewModels()
    private val categoryViewModel: CategoryViewModel by viewModels()
    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val host = supportFragmentManager.findFragmentById(R.id.nav) as NavHostFragment
        val navController = host.navController
        productsViewModel.products.observe(this){
            binding.loading.showing(it is Result.Loading)
        }

        categoryViewModel.categories.observe(this){
            binding.loading.showing(it is Result.Loading)
        }
    }


}