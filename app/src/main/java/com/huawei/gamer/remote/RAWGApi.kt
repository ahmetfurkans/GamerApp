package com.hauwei.gamer.remote

import com.huawei.gamer.remote.GetDTO
import retrofit2.http.GET

interface RAWGApi {

    @GET("/api/games?key=${API_KEY}")
    suspend fun getAllGames(
    ): GetDTO

    companion object {
        const val BASE_URL = "https://api.rawg.io"
        const val API_KEY = "583c39cf9e3c48a19c3417976c6cc1f4"
    }
}