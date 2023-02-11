package com.example.btechapp.domain.productModel

import java.io.Serializable

data class ProductModel(
    val id: Int,
    val title: String,
    val price: String,
    val image: String,
    val category: String,
    val description: String
) : Serializable