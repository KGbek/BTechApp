package com.example.btechapp.data.core

sealed class Resource<ProductModel>(
    val data: Any? = null,
    val message: String? = null,
) {
    class Loading<T>(data: T? = null) : Resource<T>(data = data)
    class Success<ProductModel>(data: Any) : Resource<ProductModel?>(data = data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data = data, message = message)
}
