package com.example.btechapp.domain.use_case

import com.example.btechapp.data.core.Resource
import com.example.btechapp.domain.productModel.ProductModel
import com.example.btechapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetAllProductsUseCase(private val repository: Repository) {

    suspend fun getAllProducts(): Flow<Resource<List<ProductModel>>> {
        return repository.getAllProducts()
    }
}