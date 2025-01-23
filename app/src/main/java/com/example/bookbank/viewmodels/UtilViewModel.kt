package com.example.bookbank.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UtilViewModel : ViewModel() {
    private val _cartAmount = MutableStateFlow(0)
    val cartAmount: StateFlow<Int> = _cartAmount

    private val _cartDialogShowing = MutableStateFlow(false)
    val cartDialogShowing: StateFlow<Boolean> = _cartDialogShowing

    fun increaseCart() {
        _cartAmount.value++
    }

    fun decreaseCart() {
        _cartAmount.value--
    }

    fun triggerCartDialog(value:Boolean) {
        _cartDialogShowing.value = value
    }

}
