package com.meli.eduardo.fonseca.pruebaTecnica.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.Fragment

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hidden() {
    this.visibility = View.GONE
}

fun View.showing(it: Boolean) {
    if (it) this.show() else this.hidden()
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun TextView.setValue(new: String) {
    this.text = this.text.toString().replace("--", new)
}