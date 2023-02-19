package com.example.btechapp.presentation.fragments.models

import java.io.Serializable

data class CartModel(
    val image: String,
    val model: String,
    val cost: String,
    val color: String
) : Serializable

