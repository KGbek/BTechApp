package com.example.btechapp.domain.use_case

import com.example.btechapp.domain.productModel.ProductModel
import com.example.btechapp.domain.repository.ProductRepository

class GetAllProductsUseCase(private val repository: ProductRepository) {

    suspend fun getAllProducts(): List<ProductModel> {
        return repository.getAllProducts()
    }
}