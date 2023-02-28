package com.huawei.gamer.remote

import com.google.gson.annotations.SerializedName

data class Game(
    @SerializedName("name")
    val name: String,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("background_image")
    val backgroundImage: String
)