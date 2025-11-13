package com.example.ejercicioimagenes.model

import androidx.annotation.DrawableRes

data class ImageItem(
    val id: Int,
    val title: String,
    val description: String,
    @DrawableRes val drawableRes: Int
)
