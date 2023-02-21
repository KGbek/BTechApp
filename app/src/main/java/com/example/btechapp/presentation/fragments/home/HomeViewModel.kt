package com.example.btechapp.presentation.fragments.home

import androidx.lifecycle.viewModelScope
import com.example.btechapp.data.core.BaseViewModel
import com.example.btechapp.domain.productModel.ProductModel
import com.example.btechapp.domain.use_case.GetAllProductsUseCase
import com.example.btechapp.presentation.states.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val useCase: GetAllProductsUseCase) : BaseViewModel() {

    private val _getAllProducts = MutableStateFlow<UIState<ProductModel>>(UIState.Empty())
    val getAllProducts = _getAllProducts.asStateFlow()

    fun getAllProducts() {
        viewModelScope.launch {
            useCase.getAllProducts().collectData(_getAllProducts)
        }
    }
}
