package com.example.btechapp.domain.repository

import com.example.btechapp.domain.productModel.ProductModel


interface ProductRepository {

    suspend fun getAllProducts():List<ProductModel>

}