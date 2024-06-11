package com.meli.eduardo.fonseca.pruebaTecnica


import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.meli.eduardo.fonseca.pruebaTecnica.base.BaseActivity
import com.meli.eduardo.fonseca.pruebaTecnica.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@Suppress("UNREACHABLE_CODE")
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)
        val host = supportFragmentManager.findFragmentById(R.id.nav) as NavHostFragment
        val navController = host.navController
        setupActionBarWithNavController(navController)
    }

}