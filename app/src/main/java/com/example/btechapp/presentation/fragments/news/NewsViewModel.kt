package com.example.btechapp.presentation.fragments.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.btechapp.domain.productModel.ProductModel
import com.example.btechapp.domain.use_case.GetAllProductsUseCase
import com.example.btechapp.presentation.states.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModel(private val useCase: GetAllProductsUseCase) : ViewModel() {

    private val _getAllProducts = MutableStateFlow<UIState<List<ProductModel>>>(UIState.Loading())
    val getAllProducts = _getAllProducts.asStateFlow()

    fun getAllProducts() {
        viewModelScope.launch {
            try {
                _getAllProducts.emit(UIState.Loading())
                val products = useCase.getAllProducts()

                _getAllProducts.emit(UIState.Success(products))

            } catch (e: Exception) {
                _getAllProducts.emit(UIState.Error(e.message.orEmpty()))
            }

        }
    }
}