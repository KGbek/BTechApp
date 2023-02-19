package com.example.btechapp.domain.repository

import com.example.btechapp.domain.productModel.ProductModel


interface Repository {

    suspend fun getAllProducts(): List<ProductModel>

}