package com.example.btechapp.data.repository

import com.example.btechapp.data.network.Api
import com.example.btechapp.domain.productModel.ProductModel
import com.example.btechapp.domain.repository.ProductRepository

class ProductsRemoteRepositoty(
    private val api: Api
) : ProductRepository{
    override suspend fun getAllProducts(): List<ProductModel> {

        val response = api.getAllProducts()

        return response.map { responseModel ->
            ProductModel(
                id = responseModel.id,
                title = responseModel.title,
                price = responseModel.price,
                image = responseModel.image,
                category = responseModel.category,
                description = responseModel.description
            )
        }
    }
}