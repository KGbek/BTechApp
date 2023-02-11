package com.example.btechapp.data.network

import com.google.gson.annotations.SerializedName

class ResponseList (

    @SerializedName("results")
    val results: List<ResponseModel>

)