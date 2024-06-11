package com.meli.eduardo.fonseca.pruebaTecnica.base

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import androidx.appcompat.app.AppCompatActivity
import com.meli.eduardo.fonseca.pruebaTecnica.ui.dialog.MessageDialog

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
    }

    abstract fun getViewBinding(): VB


}