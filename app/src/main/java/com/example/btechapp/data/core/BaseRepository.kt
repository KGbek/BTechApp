package com.example.btechapp.data.core

import kotlinx.coroutines.flow.flow
import java.io.IOException

abstract class BaseRepository {

    protected fun <ProductModel : Any> doRequest(request: suspend () -> ProductModel) = flow {
        emit(Resource.Loading(null))
        try {
            val data = request()
            emit(Resource.Success(data))
        } catch (ioException: IOException) {
            emit(Resource.Error(ioException.localizedMessage ?: "Неизвестная ошибка"))
        }
    }

}