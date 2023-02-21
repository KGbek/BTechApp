package com.example.btechapp.data.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.btechapp.domain.productModel.ProductModel
import com.example.btechapp.presentation.states.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    fun <T> Flow<Resource<T>>.collectData(_state: MutableStateFlow<UIState<ProductModel>>) {
        viewModelScope.launch(Dispatchers.IO) {
            this@collectData.collect { res ->
                when (res) {
                    is Resource.Loading -> {
                        _state.value = UIState.Loading()
                    }
                    is Resource.Success<*> -> {
                        if (res.data != null) {
                            _state.value = UIState.Success(res.data)
                        }
                    }
                    is Resource.Error -> {
                        _state.value = UIState.Error(res.message!!)
                    }
                }
            }
        }
    }

}