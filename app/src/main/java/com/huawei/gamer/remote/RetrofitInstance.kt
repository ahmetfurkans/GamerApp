package com.huawei.gamer.remote

import com.hauwei.gamer.remote.RAWGApi
import com.hauwei.gamer.remote.RAWGApi.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {

        private val retrofit by lazy {
            val client = OkHttpClient.Builder()
                .build()
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val api: RAWGApi by lazy {
            retrofit.create(RAWGApi::class.java)
        }
    }
}