package com.example.btechapp.data.repository

import com.example.btechapp.data.core.Resource
import com.example.btechapp.data.network.Api
import com.example.btechapp.domain.productModel.ProductModel
import com.example.btechapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class RepositotyImpl(
    private val api: Api
) : Repository {
    override suspend fun getAllProducts(): List<ProductModel> {

        val response = api.getAllProducts()

        return response.results.map { responseModel ->
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