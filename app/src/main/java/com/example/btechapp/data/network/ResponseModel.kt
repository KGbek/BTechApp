package com.example.btechapp.data.network

import com.google.gson.annotations.SerializedName

class ResponseModel(

    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("description")
    val description: String

)