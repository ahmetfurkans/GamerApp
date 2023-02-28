package com.huawei.gamer.remote

import com.google.gson.annotations.SerializedName

data class GetDTO(
    @SerializedName("results")
    val results: List<Game>
)
