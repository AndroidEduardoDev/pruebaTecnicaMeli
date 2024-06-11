package com.meli.eduardo.fonseca.pruebaTecnica.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.meli.eduardo.fonseca.pruebaTecnica.ui.dialog.MessageDialog


abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(i: LayoutInflater, vg: ViewGroup?, b: Bundle?): View? {
        _binding = getViewBinding()
        return binding.root
    }

    abstract fun getViewBinding(): VB

    fun showMessage(title:String, description:String){
        MessageDialog(title,description).show(this.childFragmentManager, "")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}