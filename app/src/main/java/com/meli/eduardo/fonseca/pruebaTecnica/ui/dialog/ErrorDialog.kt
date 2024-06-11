package com.meli.eduardo.fonseca.pruebaTecnica.ui.dialog

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.meli.eduardo.fonseca.pruebaTecnica.base.BaseDialogFragment
import com.meli.eduardo.fonseca.pruebaTecnica.databinding.MessageDialogBinding

class MessageDialog(private val title:String, private val description :String) : BaseDialogFragment<MessageDialogBinding>() {
    override fun getViewBinding()=  MessageDialogBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtDescription.text = description
        binding.txtTittle.text = title
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }


}