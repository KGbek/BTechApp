package com.example.btech.presentation.ui.models

import android.media.Image
import java.io.Serializable

data class VerticalModel(
    val image: String,
    val like: Image,
    val credit: String,
    val model: String,
    val cost: String
) : Serializable
