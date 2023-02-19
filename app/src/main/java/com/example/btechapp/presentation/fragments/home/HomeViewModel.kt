package com.example.btechapp.presentation.fragments.home

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.btechapp.data.core.Resource
import com.example.btechapp.domain.productModel.ProductModel
import com.example.btechapp.domain.repository.Repository
import com.example.btechapp.presentation.states.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.EmptyFlow.collect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {

    private suspend fun getAllProducts(): List<ProductModel> = repository.getAllProducts()

    private val _getAllProducts = MutableStateFlow<UIState<ProductModel>>(UIState.Empty())
    val getAllProducts = _getAllProducts.asStateFlow()
    fun getAllNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllProducts().collect { res ->
                when (res) {
                    is Resource.Loading<*> -> {
                        _getAllProducts.value = UIState.Loading()
                    }
                    is Resource.Success<*> -> {
                        if (res.data != null) {
                            _getAllProducts.value = UIState.Success(res.data)
                        }
                    }
                    is Resource.Error<*> -> {
                        _getAllProducts.value = UIState.Error(res.message!!)
                    }
                }
            }
        }
    }
}
