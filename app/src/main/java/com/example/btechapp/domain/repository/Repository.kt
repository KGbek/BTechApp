package com.example.btechapp.domain.repository

import com.example.btechapp.data.core.Resource
import com.example.btechapp.domain.productModel.ProductModel
import kotlinx.coroutines.flow.Flow


interface Repository {

    suspend fun getAllProducts(): Flow<Resource<List<ProductModel>>>

}